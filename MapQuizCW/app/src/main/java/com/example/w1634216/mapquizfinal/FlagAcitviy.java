package com.example.w1634216.mapquizfinal;

//W1634216
//Benjamin Simon

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class FlagAcitviy extends AppCompatActivity {

    ImageView flag1, flag2, flag3;

    TextView flagName, resultFlag, timerFlag, scoreFLag;

    Button button;

    Random r1;

    int pickedImage1 = 0, lastPicked1=0;
    int pickedImage2 = 0, lastPicked2=0;
    int pickedImage3 = 0, lastPicked3=0;

    String answer;

    int n;
    int min = 1;
    int max =3;

    int seconds = 10;
    int score = 0;
    boolean countdown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_acitviy);

        flag1 = (ImageView) findViewById(R.id.flag1);
        flag2 = (ImageView) findViewById(R.id.flag2);
        flag3 = (ImageView) findViewById(R.id.flag3);
        flagName = (TextView) findViewById(R.id.flagName);
        resultFlag = (TextView) findViewById(R.id.resultFlag);
        button = (Button) findViewById(R.id.button2);
        timerFlag = (TextView) findViewById(R.id.timerFlag);
        scoreFLag = (TextView) findViewById(R.id.scoreFlag);
        r1=new Random();

        scoreFLag.setVisibility(View.INVISIBLE);
        timerFlag.setVisibility(View.INVISIBLE);

        //Random Image Generator
        do {
            pickedImage1 = r1.nextInt(new Countries().flags.length);
        } while (pickedImage1 == lastPicked1);

        lastPicked1 = pickedImage1;
        //display random image
        flag1.setImageResource(new Countries().flags[pickedImage1]);

        do {
            pickedImage2 = r1.nextInt(new Countries().flags.length);
        } while (pickedImage2 == lastPicked2);

        lastPicked2 = pickedImage2;
        //display random image
        flag2.setImageResource(new Countries().flags[pickedImage2]);

        do {
            pickedImage3 = r1.nextInt(new Countries().flags.length);
        } while (pickedImage3 == lastPicked3);

        lastPicked3 = pickedImage3;
        //display random image
        flag3.setImageResource(new Countries().flags[pickedImage3]);

        //randomly chooses an image name to display
        n = ThreadLocalRandom.current().nextInt(min, max+1);

        if (n == 1)
        {
            flagName.setText(new Countries().names[pickedImage1]);
        }
        if (n == 2)
        {
            flagName.setText(new Countries().names[pickedImage2]);
        }
        if (n == 3)
        {
            flagName.setText(new Countries().names[pickedImage3]);
        }

        //Timer
        if (MapQuiz.returnTime() == true) {

            scoreFLag.setVisibility(View.VISIBLE);
            timerFlag.setVisibility(View.VISIBLE);

            new CountDownTimer(10_000, 1_000) {
                @Override
                public void onTick(long l) {
                    if(countdown == true){
                        cancel();
                    }
                    else {
                        timerFlag.setText("Time: " + String.valueOf(seconds));
                        seconds--;
                    }
                }

                @Override
                public void onFinish() {
                    timerFlag.setText("Time up!");
                    countdown = true;
                    resultFlag.setText("Wrong");
                    resultFlag.setTextColor(RED);
                }
            }.start();
        }

        //Image OnClick
        flag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = (new Countries().names[pickedImage1]);
                if(flagName.getText() == answer)
                {
                    resultFlag.setText("Correct");
                    resultFlag.setTextColor(GREEN);
                    score++;
                    scoreFLag.setText("Score:" + score);
                    countdown = true;
                }
                else{
                    resultFlag.setText("Wrong");
                    resultFlag.setTextColor(RED);
                }
            }
        });

        //Image OnClick
        flag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = (new Countries().names[pickedImage2]);
                if(flagName.getText() == answer)
                {
                    resultFlag.setText("Correct");
                    resultFlag.setTextColor(GREEN);
                    score++;
                    scoreFLag.setText("Score:" + score);
                    countdown = true;
                }
                else{
                    resultFlag.setText("Wrong");
                    resultFlag.setTextColor(RED);
                }
            }
        });

        //Image OnClick
        flag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = (new Countries().names[pickedImage3]);
                if(flagName.getText() == answer)
                {
                    resultFlag.setText("Correct");
                    resultFlag.setTextColor(GREEN);
                    score++;
                    scoreFLag.setText("Score:" + score);
                    countdown = true;
                }
                else{
                    resultFlag.setText("Wrong");
                    resultFlag.setTextColor(RED);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //remove duplicate
            public void onClick(View v) {
                do {
                    pickedImage1 = r1.nextInt(new Countries().flags.length);
                } while (pickedImage1 == lastPicked1);

                lastPicked1 = pickedImage1;
                //display random image
                flag1.setImageResource(new Countries().flags[pickedImage1]);

                do {
                    pickedImage2 = r1.nextInt(new Countries().flags.length);
                } while (pickedImage2 == lastPicked2);

                lastPicked2 = pickedImage2;
                //display random image
                flag2.setImageResource(new Countries().flags[pickedImage2]);

                do {
                    pickedImage3 = r1.nextInt(new Countries().flags.length);
                } while (pickedImage3 == lastPicked3);

                lastPicked3 = pickedImage3;
                //display random image
                flag3.setImageResource(new Countries().flags[pickedImage3]);


                //Randomly Choose number between 1-3
                n = ThreadLocalRandom.current().nextInt(min, max+1);

                if (n == 1)
                {
                    flagName.setText(new Countries().names[pickedImage1]);
                }
                if (n == 2)
                {
                    flagName.setText(new Countries().names[pickedImage2]);
                }
                if (n == 3)
                {
                    flagName.setText(new Countries().names[pickedImage3]);
                }

                resultFlag.setText("");

                //Timer
                seconds = 10;
                countdown = false;
                if (MapQuiz.returnTime() == true) {

                    scoreFLag.setVisibility(View.VISIBLE);

                    new CountDownTimer(10_000, 1_000) {
                        @Override
                        public void onTick(long l) {
                            if(countdown == true){
                                cancel();
                            }
                            else {
                                timerFlag.setText("Time: " + String.valueOf(seconds));
                                seconds--;
                            }
                        }

                        @Override
                        public void onFinish() {
                            timerFlag.setText("Time up!");
                            countdown = true;
                            resultFlag.setText("Wrong");
                            resultFlag.setTextColor(RED);
                        }
                    }.start();
                }
            }
        });
    }
}
