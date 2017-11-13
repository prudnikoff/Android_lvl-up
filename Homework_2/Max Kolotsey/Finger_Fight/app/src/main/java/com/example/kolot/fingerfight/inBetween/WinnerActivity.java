package com.example.kolot.fingerfight.inBetween;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kolot.fingerfight.R;
import com.example.kolot.fingerfight.main.MainActivity;

public class WinnerActivity extends AppCompatActivity {
    private TextView win;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        win = (TextView) findViewById(R.id.win);
        goBack = (Button) findViewById(R.id.goBack);


        win.setEnabled(true);
        final Intent intent = getIntent();
        int player = intent.getIntExtra("winner", 0);
        win.setText("Player " + player + " wins!!!\nCongratulations:)");

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(WinnerActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

    }
}
