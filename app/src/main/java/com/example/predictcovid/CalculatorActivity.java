package com.example.predictcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity  {
    private Spinner spinner;
    private List<String> answer;
    private List<String> questions;
    private Button answerBtn;
    private TextView question;
    private EditText numAnswer;
    private int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        answer = new ArrayList<String>();
        questions = new ArrayList<String>();
        setQuestionMessages();
        question = (TextView) findViewById(R.id.questionText);
        spinner = (Spinner) findViewById(R.id.spinner);
        answerBtn = (Button) findViewById(R.id.answerBtn);
        numAnswer = (EditText) findViewById(R.id.numberTextField);
        numAnswer.setVisibility(View.INVISIBLE);
        numAnswer.setEnabled(false);
        answerBtn.setText("Następne");

        setQuestionText(counter);
        populateSpinner(counter);

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter%3 == 0){
                    if(numAnswer.getText().length() < 1) {
                        Toast.makeText(CalculatorActivity.this, numAnswer.getText(),Toast.LENGTH_SHORT).show();
                        numAnswer.setError("Proszę wprowadzić dane");
                    } else {
                        answer.add(numAnswer.getText().toString());
                        counter++;
                        setQuestionText(counter);
                        populateSpinner(counter);
                    }
                } else if(counter > questions.size()) {
                    String s = "";
                    for(int i = 0; i < answer.size(); i++)
                        s += questions.get(i) + " " + answer.get(i) + "# ";
                    Intent intent = new Intent(getApplicationContext(), CalculatorSummary.class);
                    intent.putExtra("result", s);

                    startActivity(intent);
                } else {
                    answer.add(spinner.getSelectedItem().toString());
                    if(counter%3 == 1 && spinner.getSelectedItem().toString().equals("nie")) {
                        answer.add("brak");
                        answer.add("brak");
                        counter += 3;
                    } else {
                        counter++;
                    }
                    setQuestionText(counter);
                    populateSpinner(counter);
                }
            }
        });
    }

    private void populateSpinner(int counter) {
       // Toast.makeText(CalculatorActivity.this, Integer.toString(counter),Toast.LENGTH_SHORT).show();
        if(counter-1 < questions.size()) {
            if (counter % 3 == 0) {
                spinner.setVisibility(View.INVISIBLE);
                spinner.setEnabled(false);
                numAnswer.setEnabled(true);
                numAnswer.setVisibility(View.VISIBLE);
            } else {
                spinner.setEnabled(true);
                spinner.setVisibility(View.VISIBLE);
                numAnswer.setVisibility(View.INVISIBLE);
                numAnswer.setEnabled(false);
                ArrayAdapter<String> answers = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.first_answer));
                answers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(answers);
            }
        }
    }

    private void setQuestionText(int counter) {
        if(counter-1 < questions.size()) {
            String temp = questions.get(counter - 1);
            question.setText(temp);
        }
    }

    private void setQuestionMessages() {

        questions.add("Czy korzystałeś(aś) z komunikacji miejskiej dzisiaj?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w komunikacji miejskiej?");

        questions.add("Czy byłeś(aś) w szkole/uczelni?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w szkole?");

        questions.add("Czy byłeś(aś) w biurze?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w biurze?");

        questions.add("Czy byłeś(aś) w kościele?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w kościele?");

        questions.add("Czy byłeś(aś) w sklepie?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w sklepie?");

        questions.add("Czy byłeś(aś) w kawiarni?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w kawiarni/barze?");

        questions.add("Czy byłeś(aś) w restauracji?");
        questions.add("Czy nosiłeś(aś) wtedy maskę?");
        questions.add("Ile spędziałeś(aś) wtedy godzin w restauracji?");
    }

}