package com.example.pewpew.fingerfighting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DefaultMode extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2;
    private TextView text1, text2;
    private int count1 = 0;
    private int count2 = 0;
    private int pointToWin = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_mode);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button1.getId()) {
            count1++;
            button1.setText("" + count1);
            if(count1 == pointToWin)
                resultOfMatch(button1, button2);
        }
        if(v.getId() == button2.getId()) {
            count2++;
            button2.setText("" + count2);
            if(count2 == pointToWin)
                resultOfMatch(button2, button1);
        }
    }

    private void resultOfMatch(Button winButton, Button loseButton){
        winButton.setText("Winner");
        winButton.setEnabled(false);
        loseButton.setText("Loser");
        loseButton.setEnabled(false);
        text1.setText(count1 + " - " + count2);
        text2.setText(count2 + " - " + count1);
    }
}