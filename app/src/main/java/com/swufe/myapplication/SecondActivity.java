package com.swufe.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView score;
    TextView scorea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        score = (TextView)findViewById(R.id.score);
        scorea = (TextView)findViewById(R.id.scorea);
    }
    public void btnAdd1(View btn){
        if(btn.getId()==R.id.btn_1){
        showScore(3);}
        else{
            showScorea(3);
        }
    }
    public void btnAdd2(View btn){
        if(btn.getId()==R.id.btn_2){
            showScore(2);}
        else{
            showScorea(2);
        }
    }
    public void btnAdd3(View btn){
        if(btn.getId()==R.id.btn_3){
            showScore(1);}
        else{
            showScorea(1);
        }
    }
    public void btnReset(View btn){
        score.setText("0");
        scorea.setText("0");
    }
    private void showScore(int inc){
        Log.i("show","inc="+inc);
        String oldScore = (String)score.getText();
        int newScore=Integer.parseInt(oldScore)+inc ;
        score.setText(""+ newScore);}
    private void showScorea(int inc){
        Log.i("show","inc="+inc);
        String oldScore = (String)scorea.getText();
        int newScore=Integer.parseInt(oldScore)+inc ;
        scorea.setText(""+ newScore);}
}