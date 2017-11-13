package com.example.yurakishkar.homework2;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class play extends AppCompatActivity implements View.OnTouchListener {
    Intent intent;
    private Button Redbutton;
    private Button Bluebutton;
    private int Redcounter = 0;
    private int Bluecounter = 0;
    boolean Redclick = false;
    boolean Blueclick = false;
    private int counter = 3;
    boolean Versus = false;
    private String counter1 = "TAP!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Redbutton = (Button) findViewById(R.id.button);
        Bluebutton = (Button) findViewById(R.id.button1);
        Redbutton.setOnTouchListener(this);
        Bluebutton.setOnTouchListener(this);
        intent = getIntent();
        Versus = intent.getBooleanExtra("versus", false);
        Count();

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == Redbutton.getId()) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClickDisabled();
                Redclick = true;
                tap();
                time();
            }

        } else if (view.getId() == Bluebutton.getId()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClickDisabled();
                Blueclick = true;
                tap();
                time();
            }
        }
        return false;
    }


    public void tap() {


        Redbutton.setText(Integer.toString(Redcounter) + "/5");
        Bluebutton.setText(Integer.toString(Bluecounter) + "/5");
        if (Redclick) {
            ++Redcounter;
            if (Versus) {
                if (Bluecounter != 0)
                    --Bluecounter;
                Bluebutton.setText(Integer.toString(Bluecounter) + "/5");
            }
            Redbutton.setText(Integer.toString(Redcounter) + "/5");
            Bluebutton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonCommon));
            Redclick = false;
        } else if (Blueclick) {
            ++Bluecounter;
            if (Versus) {
                if (Redcounter != 0) {
                    --Redcounter;
                    Redbutton.setText(Integer.toString(Redcounter) + "/5");
                }
            }
            Bluebutton.setText(Integer.toString(Bluecounter) + "/5");
            Redbutton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonCommon));
            Blueclick = false;
        }
        if (Bluecounter == 5) {
            ClickDisabled();
            Bluebutton.setText("won\n" + Bluecounter + "/5");
            Redbutton.setText("lost\n" + Redcounter + "/5");
        } else if (Redcounter == 5) {
            ClickDisabled();
            Bluebutton.setText("lost\n" + Bluecounter + "/5");
            Redbutton.setText("won\n" + Redcounter + "/5");
        }

    }

    private void time() {
        final int rand = 5000 + (int) (Math.random() * 10000);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bluebutton.setBackgroundColor(ContextCompat.getColor(play.this, R.color.colorBlueButton));
                Redbutton.setBackgroundColor(ContextCompat.getColor(play.this, R.color.colorRedButton));
                ClickEnabled();
                if (Bluecounter == 5 || Redcounter == 5) {
                    ClickDisabled();
                    return;
                }

                handler.postDelayed(this, rand);

            }
        }, rand);

    }



    private void Count() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                ClickDisabled();
                String counterString = Integer.toString(counter);
                Redbutton.setText(counterString);
                Bluebutton.setText(counterString);

                if (counter == 0) {
                    Redbutton.setText(counter1);
                    Bluebutton.setText(counter1);
                    ClickEnabled();
                    return;
                }
                counter--;
                handler.postDelayed(this, 1000);
            }
        });


    }

    private void ClickEnabled() {

        Redbutton.setEnabled(true);
        Bluebutton.setEnabled(true);
    }

    private void ClickDisabled() {
        Redbutton.setEnabled(false);
        Bluebutton.setEnabled(false);

    }


}
