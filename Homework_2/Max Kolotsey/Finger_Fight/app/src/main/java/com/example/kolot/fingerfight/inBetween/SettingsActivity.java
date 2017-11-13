package com.example.kolot.fingerfight.inBetween;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kolot.fingerfight.R;
import com.example.kolot.fingerfight.main.MainActivity;

public class SettingsActivity extends AppCompatActivity {

    private TextView max_score;
    private Button increase, reduce, apply;
    public static int score = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        max_score = (TextView) findViewById(R.id.max_score);
        increase = (Button) findViewById(R.id.increase);
        reduce = (Button) findViewById(R.id.reduce);
        apply = (Button) findViewById(R.id.apply);


        max_score.setText(String.valueOf(score));

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    score++;
                    max_score.setText(String.valueOf(score));
            }
        });

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score > 1) {
                    score--;
                    max_score.setText(String.valueOf(score));
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
