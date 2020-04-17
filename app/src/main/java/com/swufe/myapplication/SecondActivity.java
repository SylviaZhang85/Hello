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
        score = (TextView)findViewById(R.id.score);
        scorea = (TextView)findViewById(R.id.scorea);
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String score1=((TextView)findViewById(R.id.score)).getText().toString();
        String score2=((TextView)findViewById(R.id.scorea)).getText().toString();;
        outState.putString("teama_score",score1);
        outState.putString("teamb_score",score2);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String score1= savedInstanceState.getString("teama_score");
        String score2= savedInstanceState.getString("teamb_score");
        Log.i(TAG,"onRestoreInstanceState");
        ((TextView)findViewById(R.id.score)).setText(score1);
        ((TextView)findViewById(R.id.scorea)).setText(score2);

    }

    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart:");
    }
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume:");
    }
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"onRestart:");
    }
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause:");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"onDestroy:");
    }
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop:");
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