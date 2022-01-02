package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String Extra = "com.example.quizapp.extra";

    TextView showQuestion;
    RadioButton option1, option2, option3, option4;
    Button next, prev, submit, finish;
    RadioGroup radioGroup;
    protected String[] chkAns;
    private List<String> checkedAns = new ArrayList<>() ;

    private String radioValue;
    //int score=0;

    private String[] questions=

            {"How many days do we have in a week?", "How many days are there in a normal year?", "How many colors are there in a rainbow?",
            "Which animal is known as the ‘Ship of the Desert?’", "How many letters are there in the English alphabet?", "How many consonants are there in the English alphabet?", "How many sides are there in a triangle?",
            "Which month of the year has the least number of days?", "Which are the vowels in the English alphabet series?", "Which animal is called King of Jungle?"};

    private final String[] answers =
            {"Seven", "365", "7", "Camel", "26", "21", "Three", "February", "A, E, I, O, U", "Lion"};

    private String[][] options =
            {
            {"Three", "Seven", "Eight", "Six"}, {"365", "300", "360", "370"}, {"7", "8", "6", "5"}, {"Elephant", "Giraffe", "Camel", "Lion"}, {"24", "23", "26", "25"}, {"23", "25", "22", "21"},
            {"Four", "Two", "Three", "Five"}, {"January", "February", "March", "December"}, {"A, B, C, D, U", "D, G, I, H, J", "O, I, U, F, L", "A, E, I, O, U"}, {"Elephant", "Tiger", "Jaguar", "Lion"}
    };

    private int index = 0;
    private int number = 1;
    private int flag;
    private int prevBtn = 0;
    private String[] checkedOption;
    //private List<Integer> selectedId = new ArrayList<>();
    public int[] selectedId = new int[questions.length];
    private List<RadioButton> selectedRadiobutton = new ArrayList<>();
    private int score;


    public void showScore(View view){

        int temp = radioGroup.getCheckedRadioButtonId();
        selectedId[index] = temp;
        if (temp!=-1)
            radioValue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        else
            radioValue = "";

        checkedAns.add(index, radioValue);
        Toast.makeText(MainActivity.this, ""+radioValue+temp, Toast.LENGTH_SHORT).show();

        for (int i=0;i<=index;i++) {
            if (checkedAns.get(i).equals(answers[i])) {
                score++;
                Toast.makeText(this, " " + radioValue + answers[index], Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, " " + radioValue + answers[index], Toast.LENGTH_SHORT);
        }

        Intent intent = new Intent(this, Score.class);
        String sc = new StringBuilder().append(String.valueOf(score)).append("/").append(String.valueOf(questions.length)).toString();

        intent.putExtra(Extra, sc);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(this,Score.class);

        showQuestion = findViewById(R.id.textView);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        submit = findViewById(R.id.submit);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.radioButton1);
        option2 = findViewById(R.id.radioButton2);
        option3 = findViewById(R.id.radioButton3);
        option4 = findViewById(R.id.radioButton4);

        showQuestion.setText("Q"+(number)+" "+questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);

        if (option1.isChecked()){
            chkAns[0] = option1.getText().toString();
        } else if (option2.isChecked()){
            chkAns[0] = option2.getText().toString();
        } else if (option3.isChecked()){
            chkAns[0] = option3.getText().toString();
        } else if (option4.isChecked()){
            chkAns[0] = option4.getText().toString();
        }

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int temp = radioGroup.getCheckedRadioButtonId();
                selectedId[index] = temp;
                if (temp!=-1)
                    radioValue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                else
                    radioValue = "";

                checkedAns.add(index, radioValue);
                Toast.makeText(MainActivity.this, ""+radioValue+temp, Toast.LENGTH_SHORT).show();


                if(index>=0 && index<(questions.length-1)) {
                    flag = index;

                    showQuestion.setText("Q" + (++number) + " " + questions[++index]);
                   // optIndex=index;
                    radioGroup.clearCheck();

                    option1.setText(options[index][0]);
                    option2.setText(options[index][1]);
                    option3.setText(options[index][2]);
                    option4.setText(options[index][3]);

                    if (prevBtn>0 && selectedId[index] != '\0') {
                        prevBtn--;
                        if (selectedId[index] != -1) {
                            RadioButton btn = (RadioButton) findViewById(selectedId[index]);
                            btn.setChecked(true);
                        }
                    }


                }
                else if (index==(questions.length-1)) {
                    showQuestion.setText("Q"+(questions.length)+" "+questions[questions.length-1]);
                    Toast.makeText(MainActivity.this, "Last Question!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int temp = radioGroup.getCheckedRadioButtonId();
                selectedId[index] = temp;
                if (temp!=-1)
                    radioValue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                else
                    radioValue = "";

                checkedAns.add(index, radioValue);
                Toast.makeText(MainActivity.this, ""+radioValue+temp, Toast.LENGTH_SHORT).show();

                prevBtn++;

                if(index>0 && index<(questions.length)) {
                    flag=index;
                    number=index;


                    showQuestion.setText(getString(R.string.Q) + number + " " + questions[--index]);
                    option1.setText(options[index][0]);
                    option2.setText(options[index][1]);
                    option3.setText(options[index][2]);
                    option4.setText(options[index][3]);

                    if (selectedId[index]!=-1) {
                        RadioButton btn = (RadioButton) findViewById(selectedId[index]);
                        btn.setChecked(true);
                    }
                }
                else if(index==0){
                    showQuestion.setText(getString(R.string.Q) + 1 + " " + questions[0]);
                    Toast.makeText(MainActivity.this, "First Question!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}