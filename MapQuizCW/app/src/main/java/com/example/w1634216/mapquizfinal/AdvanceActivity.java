package com.example.w1634216.mapquizfinal;

//W1634216
//Benjamin Simon

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class AdvanceActivity extends AppCompatActivity {

    ImageView adFlag1, adFlag2, adFlag3;

    EditText input1, input2, input3;
    TextView correct1, correct2, correct3, scoreAd;

    Button submit, next;

    Random r;

    int pickedImage1 = 0, lastPicked1=0;
    int pickedImage2 = 0, lastPicked2=0;
    int pickedImage3 = 0, lastPicked3=0;
    int tries = 0;

    boolean nextLevel = false;
    boolean review1;
    boolean review2;
    boolean review3;
    boolean score1=false;
    boolean score2=false;
    boolean score3=false;


    int seconds = 10;
    int score = 0;
    boolean countdown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        adFlag1 = (ImageView) findViewById(R.id.adFlag1);
        adFlag2 = (ImageView) findViewById(R.id.adFlag2);
        adFlag3 = (ImageView) findViewById(R.id.adFlag3);

        input1 = (EditText) findViewById(R.id.flagText);
        input2 = (EditText) findViewById(R.id.flagText1);
        input3 = (EditText) findViewById(R.id.flagText2);

        correct1 = (TextView) findViewById(R.id.correctAns1);
        correct2 = (TextView) findViewById(R.id.correctAns2);
        correct3 = (TextView) findViewById(R.id.correctAns3);

        submit = (Button) findViewById(R.id.advance_submit);
        next = (Button) findViewById(R.id.advance_next);

        scoreAd = (TextView) findViewById(R.id.scoreAd);

        r=new Random();

        next.setVisibility(View.INVISIBLE);

        //Random Image Generator
        do {
            pickedImage1 = r.nextInt(new Countries().flags.length);
        } while (pickedImage1 == lastPicked1);

        lastPicked1 = pickedImage1;
        //display random image
        adFlag1.setImageResource(new Countries().flags[pickedImage1]);
        System.out.println(new Countries().names[pickedImage1].toString());

        do {
            pickedImage2 = r.nextInt(new Countries().flags.length);
        } while (pickedImage2 == lastPicked2);

        lastPicked2 = pickedImage2;
        //display random image
        adFlag2.setImageResource(new Countries().flags[pickedImage2]);
        System.out.println(new Countries().names[pickedImage2].toString());

        do {
            pickedImage3 = r.nextInt(new Countries().flags.length);
        } while (pickedImage3 == lastPicked3);

        lastPicked3 = pickedImage3;
        //display random image
        adFlag3.setImageResource(new Countries().flags[pickedImage3]);
        System.out.println(new Countries().names[pickedImage3].toString());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks that input is equal or not name of flag
                if(new Countries().names[pickedImage1].toString().equalsIgnoreCase(input1.getEditableText().toString())){
                    if(score1==false) {
                        input1.setTextColor(GREEN);
                        score++;
                    }
                    scoreAd.setText("Score: " + score);
                    nextLevel = true;
                    review1 = true;
                    score1=true;
                }
                else{
                    input1.setTextColor(RED);
                    nextLevel = false;
                    review1 = false;
                }

                if(new Countries().names[pickedImage2].toString().equalsIgnoreCase(input2.getEditableText().toString())){
                    if(score2==false) {
                        score++;
                        scoreAd.setText("Score: " + score);
                    }
                    input2.setTextColor(GREEN);
                    nextLevel = true;
                    review2 = true;
                    score2=true;
                }
                else{
                    input2.setTextColor(RED);
                    nextLevel = false;
                    review2 = false;

                }

                if(new Countries().names[pickedImage3].toString().equalsIgnoreCase(input3.getEditableText().toString())){
                    if(score3==false) {
                        score++;
                        scoreAd.setText("Score: " + score);
                    }
                    input3.setTextColor(GREEN);
                    nextLevel = true;
                    review3 = true;
                    score3=true;
                }
                else{
                    input3.setTextColor(RED);
                    nextLevel = false;
                    review3 = false;
                }

                if (nextLevel == true){
                    submit.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }

                if(nextLevel == false){
                    tries++;
                }

                if (tries == 3){
                    if(review1 == false){
                        correct1.setText(new Countries().names[pickedImage1].toString());
                        correct1.setTextColor(BLUE);
                    }
                    if(review2 == false){
                        correct2.setText(new Countries().names[pickedImage2].toString());
                        correct2.setTextColor(BLUE);
                    }
                    if(review3 == false){
                        correct3.setText(new Countries().names[pickedImage3].toString());
                        correct3.setTextColor(BLUE);
                    }
                    submit.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Random Image Generator
                do {
                    pickedImage1 = r.nextInt(new Countries().flags.length);
                } while (pickedImage1 == lastPicked1);

                lastPicked1 = pickedImage1;
                //display random image
                adFlag1.setImageResource(new Countries().flags[pickedImage1]);
                System.out.println(new Countries().names[pickedImage1].toString());

                do {
                    pickedImage2 = r.nextInt(new Countries().flags.length);
                } while (pickedImage2 == lastPicked2);

                lastPicked2 = pickedImage2;
                //display random image
                adFlag2.setImageResource(new Countries().flags[pickedImage2]);
                System.out.println(new Countries().names[pickedImage2].toString());

                do {
                    pickedImage3 = r.nextInt(new Countries().flags.length);
                } while (pickedImage3 == lastPicked3);

                lastPicked3 = pickedImage3;
                //display random image
                adFlag3.setImageResource(new Countries().flags[pickedImage3]);
                System.out.println(new Countries().names[pickedImage3].toString());

                input1.setText("");
                input1.setTextColor(BLACK);
                input2.setText("");
                input2.setTextColor(BLACK);
                input3.setText("");
                input3.setTextColor(BLACK);

                tries = 0;

                correct1.setText("");
                correct2.setText("");
                correct3.setText("");

                score1=false;
                score2=false;
                score3=false;

                submit.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
            }
        });
    }
}
