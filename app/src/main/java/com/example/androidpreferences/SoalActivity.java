package com.example.androidpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SoalActivity extends AppCompatActivity {

    Button btnTrue, btnFalse;
    TextView lblQuestion, lblCurQuestion;
    ProgressBar pgbar;
    int progress = 0, currQuestion = 0, reqQuestion = 4, corrAnswer = 0;
    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, false),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true)
    };
    int min = 0;
    int max = 3;//exclusive -- artinya tidak termasuk
    int randomNumber = new Random().nextInt(max - min) + min;
    //int randomNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        lblQuestion = (TextView) findViewById(R.id.lblSoal);
        lblCurQuestion = (TextView) findViewById(R.id.lblCurrQuestion);
        pgbar = (ProgressBar) findViewById(R.id.progressBar3);

        lblQuestion.setText(questionBank[randomNumber].getQuestionID());

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                    if(randomNumber >= questionBank.length-1)
                        randomNumber = 0;
                    else
                        randomNumber+=1;

                    lblQuestion.setText(questionBank[randomNumber].getQuestionID());

                lblQuestion.setText(questionBank[randomNumber].getQuestionID());
                System.out.println(randomNumber);

            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                    if(randomNumber >= questionBank.length-1)
                        randomNumber = 0;
                    else
                        randomNumber+=1;

                    lblQuestion.setText(questionBank[randomNumber].getQuestionID());

                lblQuestion.setText(questionBank[randomNumber].getQuestionID());
                System.out.println(randomNumber);

            }
        });
    }

    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = questionBank[randomNumber].isAnswer();
        if(userSelection == correctAnswer){
            Toast.makeText(getApplicationContext(), R.string.correct, Toast.LENGTH_SHORT).show();
            corrAnswer += 1;
            updateScore();
        }else {
            Toast.makeText(getApplicationContext(), R.string.incorrect, Toast.LENGTH_SHORT).show();
            updateScore();
        }
    }

    private void updateScore(){
        progress += 25;
        currQuestion += 1;
        if(progress == 100){
            AlertDialog.Builder builder = new AlertDialog.Builder(SoalActivity.this);
            builder.setCancelable(false);
            if(corrAnswer <= (reqQuestion/2)){
                builder.setTitle("Try Harder");
                builder.setMessage("You've only answered "+corrAnswer+" of "+reqQuestion+" correctly");
            }else if(corrAnswer > (reqQuestion/2)){
                builder.setTitle("Good Job");
                builder.setMessage("You've answered "+corrAnswer+" of "+reqQuestion+" correctly");
            }
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            });
            builder.show();
        }
        lblCurQuestion.setText(""+currQuestion);
        pgbar.setProgress(progress);
    }
}
