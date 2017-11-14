package com.example.yurakishkar.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class Choose extends AppCompatActivity implements View.OnClickListener {
    private Button Speed;
    private Button Versus;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Speed = (Button) findViewById(R.id.Speed);
        Versus = (Button) findViewById(R.id.Versus);
        Speed.setOnClickListener(this);
        Versus.setOnClickListener(this);
        intent = new Intent(this, play.class);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == Speed.getId()) {
            startActivity(intent);
        }
        else {
            intent.putExtra("versus",true);
            startActivity(intent);
        }

    }
}
