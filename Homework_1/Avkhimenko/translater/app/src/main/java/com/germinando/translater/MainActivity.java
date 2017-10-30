package com.germinando.translater;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Path;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed2;
    TextView tv;
    Button button;
    ArrayList<String> list;
    ArrayList<String> engList;
    int found = 0;

    Charset cs = Charset.forName("windows-1251");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        engList = new ArrayList<>();
        ed2 = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView3);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        try {
            getStringFromAssetFile(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < list.size(); i+=2){
            engList.add(list.get(i));
        }


    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE); //keyboard hide
        imm.hideSoftInputFromWindow(ed2.getWindowToken(), 0);                                     //
        String request = ed2.getText().toString();
        int start = 0, end = engList.size();

        int catchedWord = binarySearch(request, engList, start, end);
        if (catchedWord == 0 && !list.get(catchedWord).equals("a little bit"))  tv.setText("There is no such word in the dictionary, pleasy try something else.");
        tv.setText(list.get(catchedWord * 2 + 1));
    }

    int binarySearch(String request, ArrayList engList, int start, int end){

        int mid;
        if(end < start) return -1;
        else mid = (start+end)/2;
        if(request.compareTo(engList.get(mid).toString()) == 0) found = mid ;
        else if (request.compareTo(engList.get(mid).toString()) > 0) binarySearch(request, engList, mid+1, end);
        else if (request.compareTo(engList.get(mid).toString()) < 0) binarySearch(request, engList, start, mid-1 );
        else found = 0;
        return found;

    }

    void getStringFromAssetFile(Activity activity) throws IOException {

        AssetManager am = MainActivity.this.getAssets();
        InputStreamReader is = new InputStreamReader(am.open("EnRu.txt"), cs);
        String word;
        BufferedReader bufferedReader = new BufferedReader(is);
        while((word =bufferedReader.readLine())!=null)
        {
            list.add(word);
        }
        is.close();
        bufferedReader.close();


    }
}


