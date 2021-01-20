package com.example.predictcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CalculatorSummary extends AppCompatActivity {
    private static final String TAG = "TAG";

    private ArrayList<AnswerStruct> answerStructs;
    private ArrayList<Float> probabilities;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_summary);

        TextView result = (TextView) findViewById(R.id.result);
        TextView answers = (TextView) findViewById(R.id.answers);
        Intent intent = getIntent();

        String s = intent.getStringExtra("result");
        String[] str = s.split("#");
        String text = "";

        ArrayList<String> ans = new ArrayList<>();
        int spliter = 0;
        for (String value : str) {
            if(spliter == str.length-1)
                text += value;
            else
                text += value + "\n\n\n";
            String[] temp = value.split("\\?");
            Collections.addAll(ans, temp);
            spliter++;
        }

        answerStructs = new ArrayList<>();
        for(int i=0, k=0; i < ans.size() / 6; i++, k+=4) {
            String[] temp = {ans.get(2*i + 1 + k), ans.get(2*i + 3 + k), ans.get(2*i + 5 + k)};
            answerStructs.add(new AnswerStruct(temp, i));
        }

        String resultProb = String.format("%.2f", GetProb());
        result.setText(resultProb + " %");
        answers.setText(text);

        Button shareBtn = (Button) findViewById(R.id.share_button);
        Button saveBtn = (Button) findViewById(R.id.save_button);

        databaseHelper = new DatabaseHelper(this);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Szansa zakażenia: " + resultProb + " %";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share"));
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String result = resultProb + " % " + formattedDate;
               // resultText += " " + formattedDate;
                AddData(result);
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }

    private float GetProb() {
        probabilities = new ArrayList<>();
        for(int i=0; i<answerStructs.size();i++)
            probabilities.add(100 - (answerStructs.get(i).CalculateProbability()*100.f));

        float helper = 1;
        for(int i=0; i<probabilities.size(); i++)
            helper *= (probabilities.get(i)/100.0f) ;


        return (1 - helper) * 100.f;
    }

    public void AddData(String newEntry) {
        boolean insertData = databaseHelper.addData(newEntry);

        if(insertData) {
           // Toast.makeText(this,"Data success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Błąd podczas zapisu danych", Toast.LENGTH_SHORT).show();
        }
    }

    private static class AnswerStruct {
        private String[] str;
        private int id;
        private ArrayList<String> answers;
        private int Volume;
        private static final float e = 2.718281828459045f;
        private static final String TAG = "myTag";

        public AnswerStruct(String[] str, int id) {
            this.str = str;
            this.id = id;

            answers = new ArrayList<>();
            Collections.addAll(answers, str);
            SetVolume();
        }

        private void SetVolume() {
            switch(id) {
                case 0:
                    Volume = 700;
                    break;
                case 1:
                case 2:
                    Volume = 5250;
                    break;
                case 3:
                    Volume = 9600;
                    break;
                case 4:
                    Volume = 3200;
                    break;
                case 5:
                    Volume = 4200;
                    break;
                case 6:
                    Volume = 4950;
                    break;
            }
        }

        public float CalculateProbability() {
            float P = 0;

            if(answers.get(0).equals(" tak")) {
                float nI = 0, nK = 0;
                if(answers.get(1).equals(" tak")) {
                    nI = 0.5f; nK = 0.5f;
                }
                float t = Float.parseFloat(answers.get(2));  //time
                float c = ((-1)*0.3f*32.f*t*(1-nI)*(1-nK))/(Volume*0.6f); //counter
                P = (float) (1 - Math.pow(e,c)); //Prob
            }
            return P;
        }
    }

}