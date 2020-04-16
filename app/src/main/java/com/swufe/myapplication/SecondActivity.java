package com.swufe.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
private final String TAG="second";
 TextView score;
 TextView scorea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
Log.i(TAG,"onCreate:");


    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String scorea=((TextView)findViewById(R.id.score)).getText().toString();
        String scoreb=((TextView)findViewById(R.id.scorea)).getText().toString();;
        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scorea= savedInstanceState.getString("teama_score");
        String scoreb= savedInstanceState.getString("teamb_score");
        Log.i(TAG,"onRestoreInstanceState");
        ((TextView)findViewById(R.id.score)).setText(scorea);
        ((TextView)findViewById(R.id.scorea)).setText(scoreb);

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