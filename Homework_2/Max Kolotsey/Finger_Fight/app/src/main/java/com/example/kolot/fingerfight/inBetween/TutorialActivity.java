package com.example.kolot.fingerfight.inBetween;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kolot.fingerfight.R;
import com.example.kolot.fingerfight.battleField.BattleFieldActivity;

public class TutorialActivity extends AppCompatActivity {

    private TextView tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        tap = (TextView) findViewById(R.id.continueTap);
        tap.setEnabled(true);

        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, BattleFieldActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}
