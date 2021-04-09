package com.example.w1634216.mapquizfinal;

//W1634216
//Benjamin Simon

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class GuessTheCountry extends AppCompatActivity {

    ImageView imageView;

    TextView answer, correctAns, timerGuess, scoreBoard;

    Button button;
    Button next;

    Random r;

    int seconds = 10;
    int score = 0;

    int pickedImage = 0, lastPicked=0;

    boolean countdown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);

        //All for Random Image Display
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        next = (Button) findViewById(R.id.next);
        r=new Random();

        answer = (TextView) findViewById(R.id.confirmation);
        correctAns = (TextView) findViewById(R.id.nameCountry);
        timerGuess = (TextView) findViewById(R.id.timerGuess);
        scoreBoard = (TextView) findViewById(R.id.scoreBoard);

        next.setVisibility(View.INVISIBLE);
        scoreBoard.setVisibility(View.INVISIBLE);
        timerGuess.setVisibility(View.INVISIBLE);

        //Drop down list
        Spinner mySpinner = (Spinner) findViewById(R.id.dropDown);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GuessTheCountry.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //Random Image Generator
        do {
            pickedImage = r.nextInt(new Countries().flags.length);
        } while (pickedImage == lastPicked);

        lastPicked = pickedImage;
        //display random image
        imageView.setImageResource(new Countries().flags[pickedImage]);

        System.out.println(new Countries().names[pickedImage].toString());

        //Timer
            if (MapQuiz.returnTime() == true) {

                scoreBoard.setVisibility(View.VISIBLE);
                timerGuess.setVisibility(View.VISIBLE);

                new CountDownTimer(10_000, 1_000) {
                    @Override
                    public void onTick(long l) {
                        if(countdown==true){
                            cancel();
                        }
                        else {
                            timerGuess.setText("Time: " + String.valueOf(seconds));
                            seconds--;
                        }
                    }

                    @Override
                    public void onFinish() {
                        timerGuess.setText("Time up!");
                        button.setVisibility(View.INVISIBLE);
                        next.setVisibility(View.VISIBLE);

                        Spinner mySpinner = (Spinner) findViewById(R.id.dropDown);
                        if(new Countries().names[pickedImage].equals(mySpinner.getSelectedItem().toString())){

                            score++;
                            answer.setText("CORRECT!");
                            answer.setTextColor(GREEN);
                            correctAns.setText("");
                            scoreBoard.setText("Score: " + score);
                        }
                        else{
                            answer.setText("OUT OF TIME!");
                            answer.setTextColor(RED);
                            correctAns.setText(new Countries().names[pickedImage]);
                            correctAns.setTextColor(BLUE);
                        }
                    }
                }.start();
            }

//Submit button
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //remove duplicate
            public void onClick(View v) {

                Spinner mySpinner = (Spinner) findViewById(R.id.dropDown);
                if(new Countries().names[pickedImage].equals(mySpinner.getSelectedItem().toString())){

                    answer.setText("CORRECT!");
                    answer.setTextColor(GREEN);
                    correctAns.setText("");
                    if(MapQuiz.switchState==true){
                        score++;
                        scoreBoard.setText("Score: " + score);
                    }
                }
                else{
                    answer.setText("WRONG!");
                    answer.setTextColor(RED);
                    correctAns.setText(new Countries().names[pickedImage]);
                    correctAns.setTextColor(BLUE);
                }

                countdown = true;
                seconds = 10;
                timerGuess.setText("Stopped!");
                button.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });

//Next button
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            //remove duplicate
            public void onClick(View v) {
                do {
                    pickedImage = r.nextInt(new Countries().flags.length);
                } while (pickedImage == lastPicked);

                lastPicked = pickedImage;
                //display random image
                imageView.setImageResource(new Countries().flags[pickedImage]);
                System.out.println(new Countries().names[pickedImage].toString());

                answer.setText("");
                correctAns.setText("");

                seconds = 10;
                countdown = false;
                next.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);

                    if (MapQuiz.returnTime() == true) {
                        new CountDownTimer(10_000, 1_000) {
                            @Override
                            public void onTick(long l) {
                                if(countdown==true){
                                    cancel();
                                }
                                else {
                                    timerGuess.setText(String.valueOf(seconds));
                                    seconds--;
                                }
                            }

                            @Override
                            public void onFinish() {
                                timerGuess.setText("Time up!");
                                button.setVisibility(View.INVISIBLE);
                                next.setVisibility(View.VISIBLE);

                                Spinner mySpinner = (Spinner) findViewById(R.id.dropDown);
                                if(new Countries().names[pickedImage].equals(mySpinner.getSelectedItem().toString())){

                                    answer.setText("CORRECT!");
                                    answer.setTextColor(GREEN);
                                    correctAns.setText("");
                                    scoreBoard.setText("Score: " + score);
                                }
                                else{
                                    answer.setText("OUT OF TIME!");
                                    answer.setTextColor(RED);
                                    correctAns.setText(new Countries().names[pickedImage]);
                                    correctAns.setTextColor(BLUE);
                                }
                            }
                        }.start();
                    }
            }
        });


    }
}
