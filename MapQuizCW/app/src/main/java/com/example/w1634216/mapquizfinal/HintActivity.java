package com.example.w1634216.mapquizfinal;

//W1634216
//Benjamin Simon

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class HintActivity extends AppCompatActivity {

    ImageView imageView;

    Button submit, next;

    TextView answer, result, nameHint, scoreHint, timerHint;

    EditText check;

    Random r;

    boolean resultTrue = false;

    int pickedImage = 0, lastPicked = 0;
    int x;
    int attempts = 0;

    int seconds = 10;
    int score = 0;
    boolean countdown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        imageView = (ImageView) findViewById(R.id.flag);
        submit = (Button) findViewById(R.id.hintSubmit);
        next = (Button) findViewById(R.id.nextButton);
        answer = (TextView) findViewById(R.id.answer);
        result = (TextView) findViewById(R.id.textView);
        nameHint = (TextView) findViewById(R.id.resultView);
        check = (EditText) findViewById(R.id.userInput);
        scoreHint = (TextView) findViewById(R.id.scoreHint);
        timerHint = (TextView) findViewById(R.id.timerHint);
        r = new Random();

        next.setVisibility(View.INVISIBLE);
        scoreHint.setVisibility((View.INVISIBLE));
        timerHint.setVisibility(View.INVISIBLE);

        //Random Image Generator
        do {
            pickedImage = r.nextInt(new Countries().flags.length);
        } while (pickedImage == lastPicked);

        lastPicked = pickedImage;
        //display random image
        imageView.setImageResource(new Countries().flags[pickedImage]);

        System.out.println(new Countries().names[pickedImage].toString());

        answer.setText("");

        //Draws lines depending of length of name
        for (x = 0; x < (new Countries().names[pickedImage].length()); x++) {
            if (new Countries().names[pickedImage].charAt(x) == ' ') {
                answer.setText(answer.getText() + " ");
            } else {
                answer.setText(answer.getText() + "-");
            }
        }

        //Timer
        if (MapQuiz.returnTime() == true) {

            scoreHint.setVisibility(View.VISIBLE);
            timerHint.setVisibility(View.VISIBLE);

            new CountDownTimer(10_000, 1_000) {
                @Override
                public void onTick(long l) {
                    if(countdown == true){
                        cancel();
                    }
                    else {
                        timerHint.setText("Time: " + String.valueOf(seconds));
                        seconds--;
                    }
                }

                @Override
                public void onFinish() {
                    timerHint.setText("Time up!");
                }
            }.start();
        }

//Next button
        next.setOnClickListener(new View.OnClickListener() {
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

                for (int x = 0; x < (new Countries().names[pickedImage].length()); x++) {
                    if (new Countries().names[pickedImage].charAt(x) == ' ') {
                        answer.setText(answer.getText() + " ");
                    } else {
                        answer.setText(answer.getText() + "-");
                    }
                }

                next.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
                nameHint.setText("");
                result.setText("");
                seconds = 10;
                countdown = false;

            }
        });

//Submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTrue = false;
                countdown = true;
                //Checks if user has inputed
                if(check.getText().toString().equals("")){
                    result.setText("No input.");
                    result.setTextColor(RED);
                }
                //Checks user input
                else {
                    for (int i = 0; i < (new Countries().names[pickedImage].length()); i++) {
                        String input = ((TextView) findViewById(R.id.userInput)).getText().toString().trim();
                        char a = input.charAt(0);
                        char c = new Countries().names[pickedImage].charAt(i);
                        char upperA = Character.toUpperCase(a);

                        if (a == c) {
                            result.setText("Correct");
                            result.setTextColor(GREEN);
                            answer.setText(answer.getText().toString().substring(0,i) + check.getText().toString() + ((String) answer.getText()).substring(i+1));
                            resultTrue = true;
                        } else if (upperA == c) {
                            result.setText("Correct");
                            result.setTextColor(GREEN);
                            answer.setText(answer.getText().toString().substring(0,i) + check.getText().toString() + ((String) answer.getText()).substring(i+1));
                            resultTrue = true;
                        }
                    }
                    if (resultTrue == false) {
                        result.setText("Incorrect");
                        result.setTextColor(RED);
                        attempts++;
                    }

                    if (answer.getText().toString().equalsIgnoreCase(new Countries().names[pickedImage].toString())) {
                        result.setText("Word Correct");
                        result.setTextColor(GREEN);
                        next.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.INVISIBLE);
                        attempts = 0;
                    }

                    if (attempts == 3) {
                        next.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.INVISIBLE);
                        nameHint.setText(new Countries().names[pickedImage].toString());
                        nameHint.setTextColor(BLUE);
                        attempts = 0;
                    }
                }
            }
        });
    }

}