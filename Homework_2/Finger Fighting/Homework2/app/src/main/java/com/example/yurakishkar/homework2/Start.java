package com.example.yurakishkar.homework2;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Start extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(this);
        intent = new Intent(this, Choose.class);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button.getId()) {
            startActivity(intent);

        }


    }
}
