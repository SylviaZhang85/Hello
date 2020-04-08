package com.swufe.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {
    EditText rmb;
    TextView show;
    private final String TAG="Rate";
    private float dollarRate=0.0f;
    private float euroRate=0.0f;
    private float wonRate=0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb=(EditText)findViewById(R.id.rmb);
        show=(TextView)findViewById(R.id.showOut);

        //获取SP里保存的数据

        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //也可用此获取数据：SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);

        dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate = sharedPreferences.getFloat("won_rate",0.0f);
        Log.i(TAG,"omnCreate:sp dollarRate="+ dollarRate) ;
        Log.i(TAG,"omnCreate:sp euroRate="+ euroRate) ;
        Log.i(TAG,"omnCreate:sp wonRate="+ wonRate) ;

    }
    public void onClick(View btn){
        String str=rmb.getText().toString();
        float r=0;
        if(str.length()>0){
        r =Float.parseFloat(str);}else
        {
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }
        if(btn.getId()==R.id.btn_dollar){

            show.setText(String.format("%.2f",r*dollarRate));
        }
        else if(btn.getId()==R.id.btn_euro){

            show.setText(String.format("%.2f",r*euroRate));
        }else{

           show.setText(String.format("%.2f",r*wonRate));
        }


    }

  public void openOne(View btn){
    Log.i("open;","openOne;");
      Intent config= new Intent(this,ConfigActivity.class);
      config.putExtra("dollar_rate_key",dollarRate);
      config.putExtra("euro_rate_key",euroRate);
      config.putExtra("won_rate_key",wonRate);

      Log.i(TAG,"openOne:dollarRate="+dollarRate);
      Log.i(TAG,"openOne:euroRate="+euroRate);
      Log.i(TAG,"openOne:wonRate="+wonRate);

      startActivity(config);
      startActivityForResult(config,1);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.menu_set){
            openConfig();

        }
        return super.onOptionsItemSelected(item);
    }

    private void openConfig() {
        Intent config= new Intent(this, ConfigActivity.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);

        Log.i(TAG,"openOne:dollarRate="+dollarRate);
        Log.i(TAG,"openOne:euroRate="+euroRate);
        Log.i(TAG,"openOne:wonRate="+wonRate);

        startActivityForResult(config,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==2){
           /* bdl.putFloat("key_dollar",newDollar);
            bdl.putFloat("key_euro",newDollar);
            bdl.putFloat("key_won",newDollar);*/
           Bundle bundle = data.getExtras();
           dollarRate= bundle.getFloat("key_dollar",0.1f);
           euroRate= bundle.getFloat("key_euro",0.1f);
           wonRate= bundle.getFloat("key_won",0.1f);

            Log.i(TAG,"onActivityResult:dollar="+dollarRate);
            Log.i(TAG,"onActivityResult:euro="+euroRate);
            Log.i(TAG,"onActivityResult:won="+wonRate);

            //将新设置的汇率写到SP里
            SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.commit();
            Log.i(TAG,"onActivityResult:数据已保存到sharedPreferences");

        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
