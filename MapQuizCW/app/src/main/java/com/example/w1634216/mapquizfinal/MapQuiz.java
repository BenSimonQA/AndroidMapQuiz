package com.example.w1634216.mapquizfinal;

//W1634216
//Benjamin Simon

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MapQuiz extends AppCompatActivity {
    private Button button;
    private Button hint;
    private Button flag;
    private Button advance;
    public static Switch timer;
    public static boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_quiz);

        button = (Button) findViewById(R.id.guess_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGuessTheCountry();
            }
        });

        hint = (Button) findViewById(R.id.hints_button);
        hint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHintsActivity();
            }
        });

        flag = (Button) findViewById(R.id.flag_button);
        flag.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openFlagActivity();
            }
        });

        advance = (Button) findViewById(R.id.advance_button);
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvanceActivity();
            }
        });

        timer = (Switch) findViewById(R.id.timer);
    }

    public void openGuessTheCountry(){
        Intent intent = new Intent(this, GuessTheCountry.class);
        startActivity(intent);
    }

    public void openHintsActivity(){
        Intent intent2 = new Intent(this, HintActivity.class);
        startActivity(intent2);
    }

    public void openFlagActivity(){
        Intent intent3 = new Intent(this, FlagAcitviy.class);
        startActivity(intent3);
    }

    public void openAdvanceActivity(){
        Intent intent4 = new Intent(this, AdvanceActivity.class);
        startActivity(intent4);
    }

    public static boolean returnTime(){
        switchState = timer.isChecked();
        return switchState;
    }
}
