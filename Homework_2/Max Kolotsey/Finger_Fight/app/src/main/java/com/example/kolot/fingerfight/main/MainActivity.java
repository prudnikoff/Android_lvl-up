package com.example.kolot.fingerfight.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.kolot.fingerfight.inBetween.SettingsActivity;
import com.example.kolot.fingerfight.inBetween.TutorialActivity;
import com.example.kolot.fingerfight.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private Animation anim, animButton;
    private MainPresenter presenter;
    private Button button, quit, settings;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();

        button = (Button) findViewById(R.id.start);
        settings = (Button) findViewById(R.id.settings);
        quit = (Button) findViewById(R.id.end);
        title = (TextView) findViewById(R.id.titleText);


        presenter.bindView(this);

        presenter.startAnim();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.quit();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendIntent();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openSettings();
            }
        });
    }

    @Override
    public void loadAnim() {
        anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
        title.startAnimation(anim);
        animButton = AnimationUtils.loadAnimation(this, R.anim.buttontrans);
        button.startAnimation(animButton);
        quit.startAnimation(animButton);
        settings.setAnimation(animButton);
    }

    @Override
    public void startGame() {
        Intent intent = new Intent(this, TutorialActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void quitGame() {
        finishAffinity();
    }

    @Override
    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
