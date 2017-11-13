package com.example.yurakishkar.homework2;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    private Button button;

    private TextView textview;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MainActivity.this,Start.class));
            finish();
        }
    },2000);

    }

}


