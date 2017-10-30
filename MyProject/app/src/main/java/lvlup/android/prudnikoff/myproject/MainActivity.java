package lvlup.android.prudnikoff.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView helloTextView;
    private EditText helloEditText;
    private Button helloButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTextView = (TextView)findViewById(R.id.helloTextView);
        helloEditText = (EditText)findViewById(R.id.helloEditText);
        helloButton = (Button)findViewById(R.id.helloButton);
        helloButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == helloButton.getId()) {
            String name = helloEditText.getText().toString();
            helloTextView.setText("Hello, " + name + "!");
        }
    }
}
