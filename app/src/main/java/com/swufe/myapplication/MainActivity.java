package com.swufe.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv= (TextView)findViewById(R.id.showText);
        tv.setText("swufe");

        EditText inp=(EditText)findViewById(R.id.inpext);
        String str = inp.getText().toString();

        Log.i("main","input="+str);
    }
}
