package com.example.kolot.fingerfight.battleField;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.kolot.fingerfight.R;
import com.example.kolot.fingerfight.inBetween.SettingsActivity;
import com.example.kolot.fingerfight.inBetween.WinnerActivity;

import java.util.Random;

import static android.view.View.ROTATION;

public class BattleFieldActivity extends AppCompatActivity implements BattleFieldView {

    private Button player1, player2;
    private final int BTTN_1 = 4;
    private final int BTTN_2 = 5;
    private final int START = 6;
    private int counter_1, counter_2;
    private BattleFieldPresenter presenter;
    Handler handler;
    private int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        presenter = new BattleFieldPresenter();
        player1 = (Button) findViewById(R.id.player1);
        player2 = (Button) findViewById(R.id.player2);
        counter_1 = counter_2 = 0;
        presenter.bindView(this);

        ROTATION.set(player1, 180f);

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                Random random = new Random();
                int delay;

                switch (msg.what) {

                    case BTTN_1:
                        player1.setEnabled(false);
                        player2.setEnabled(false);
                        handler.postDelayed(paint, 150);
                        delay = random.nextInt(5000);
                        presenter.chooseWinner();
                        handler.postDelayed(changeColor, delay);
                        break;
                    case BTTN_2:
                        player1.setEnabled(false);
                        player2.setEnabled(false);
                        handler.postDelayed(paint, 150);
                        presenter.chooseWinner();
                        delay = random.nextInt(5000);
                        handler.postDelayed(changeColor, delay);
                        break;
                    case START:
                        player1.setEnabled(false);
                        player2.setEnabled(false);
                        presenter.setTimer();
                        handler.postDelayed(start_0, 4000);
                        handler.postDelayed(changeColor, 4000);
                        break;

                }
            }
        };
        handler.sendEmptyMessage(START);


        player1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        player2.setEnabled(false);
                        presenter.player_1_click();
                        break;
                }
                return false;
            }
        });

        player2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        player1.setEnabled(false);
                        presenter.player_2_click();
                        break;
                }
                return false;
            }
        });
    }

    Runnable changeColor = new Runnable() {
        @Override
        public void run() {
            player1.setEnabled(true);
            player1.setBackgroundColor(getResources().getColor(R.color.bothFields));
            player2.setEnabled(true);
            player2.setBackgroundColor(getResources().getColor(R.color.bothFields));
        }
    };

    Runnable start_0 = new Runnable() {
        @Override
        public void run() {
            player1.setText("0");
            player2.setText("0");
        }
    };

    Runnable paint = new Runnable() {
        @Override
        public void run() {
            player1.setBackgroundColor(getResources().getColor(R.color.paint));
            player2.setBackgroundColor(getResources().getColor(R.color.paint));
        }
    };

    @Override
    public void player_1_click() {
        handler.sendEmptyMessage(BTTN_1);
        counter_1++;
        player1.setText(String.valueOf(counter_1));
        player1.setBackgroundColor(getResources().getColor(R.color.border));
    }

    @Override
    public void player_2_click() {
        handler.sendEmptyMessage(BTTN_2);
        counter_2++;
        player2.setText(String.valueOf(counter_2));
        player2.setBackgroundColor(getResources().getColor(R.color.border));
    }

    public void chooseWinner() {
        if (counter_1 >= SettingsActivity.score) {
            Intent intent = new Intent(this, WinnerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("winner", 1);
            startActivity(intent);
        } else if (counter_2 >= SettingsActivity.score) {


            Intent intent = new Intent(this, WinnerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("winner", 2);
            startActivity(intent);
        }
    }

    public void setTimer (){
        new CountDownTimer(3200, 800) {

            public void onTick(long millisUntilFinished) {
                player1.setText(String.valueOf(time));
                player2.setText(String.valueOf(time));
                --time;
            }

            public void onFinish() {
                player1.setText("Go!");
                player2.setText("Go!");

            }

        }.start();

    }

}


