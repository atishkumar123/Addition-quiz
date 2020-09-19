package com.atish.additionquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView sumTextView;
    TextView scoreTextView;
    TextView resultTextView;

    Button goButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;

    ConstraintLayout gameLayout;

    boolean activePlayer=true;
    int score=0;
    int noOfQuestion=0;
    int locationOfCorrectAnswer;
    ArrayList<Integer>Answer=new ArrayList<>();


    public void playAgain(View view){
        activePlayer=true;
        score=0;
        noOfQuestion=0;
        timerTextView.setText("30s");
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));
        resultTextView.setText("");

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                activePlayer=false;
            }
        }.start();

    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }



    public void choseAnswer (View view) {
        if (activePlayer) {


            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                score++;
                scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestion));
            } else {
                resultTextView.setText("Wrong :(");
            }
            noOfQuestion++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestion));
            newQuestion();
        }
    }

    public void newQuestion(){
        Answer.clear();
        Random random=new Random();
        int a=random.nextInt( 21);
        int b=random.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer=random.nextInt(4);
        for( int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                Answer.add(a+b);
            }
            int wrongAnswer=random.nextInt( 41);
            while(wrongAnswer==a+b){
                wrongAnswer=random.nextInt( 41);
            }
            Answer.add(wrongAnswer);
        }
        button1.setText(Integer.toString(Answer.get(0)));
        button2.setText(Integer.toString(Answer.get(1)));
        button3.setText(Integer.toString(Answer.get(2)));
        button4.setText(Integer.toString(Answer.get(3)));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView=findViewById(R.id.timerTextView);
        sumTextView=findViewById(R.id.sumTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        resultTextView=findViewById(R.id.answerTextView);

        goButton=findViewById(R.id.goButton);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        playAgainButton=findViewById(R.id.playAgainButton);

        gameLayout=findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);











    }
}