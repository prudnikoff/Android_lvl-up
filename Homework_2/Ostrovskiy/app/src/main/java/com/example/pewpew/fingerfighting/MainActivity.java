package com.example.pewpew.fingerfighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonClassicMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClassicMode = (Button)findViewById(R.id.buttonClassicMode);
        buttonClassicMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == buttonClassicMode.getId()) {
            Intent intent = new Intent(this, DefaultMode.class);
            startActivity(intent);
        }
    }
}