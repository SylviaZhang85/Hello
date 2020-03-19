package com.swufe.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {


    EditText inp;
    TextView tex4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inp=(EditText)findViewById(R.id.editText);
        tex4=(TextView)findViewById(R.id.textView4);
        }



    public void btn1(View btn){
      C();
    }
    public void btn2(View btn){
      F();
    }

    private void C(){
        Log.i("show","");
        String Cel = inp.getText().toString();
        double c=Double.parseDouble(Cel);
        double Fah= c*1.8 +32;
        tex4.setText(c+"Celsius is equal to "+Fah+"Fahrenheit");

    }
    private void F(){
        Log.i("show","");
        String Fah = inp.getText().toString();
        double f=Double.parseDouble(Fah);
        double Cel= (f-32)/1.8;
        tex4.setText(f+" Fahrenheit is equal to "+Cel+"Celsius");

    }

}
