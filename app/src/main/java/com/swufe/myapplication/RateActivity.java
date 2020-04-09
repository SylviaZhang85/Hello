package com.swufe.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity implements Runnable {
    EditText rmb;
    TextView show;
    Handler handler;
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
         //开启子线程
        Thread t=new Thread(this);
        t.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if (msg.what==5){
                    String str = (String)msg.obj;
                    Log.i(TAG,"handleMessage:getMessage msg="+str);
                    show.setText(str);
                }
                super.handleMessage(msg);
            }
        };
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

    @Override
    public void run() {
        Log.i(TAG,"run:run()......");
        for(int i=1;i<6;i++){
            Log.i(TAG,"run:i="+i);
           try{ Thread.sleep(2000);}
           catch(InterruptedException e){
               e.printStackTrace();
           }
        }
        //获取msg对象用于返回主线程
        Message msg = handler.obtainMessage();
        msg.what = 5;
        msg.obj="Hello from run()";
        handler.sendMessage(msg);
        //获取网络数据
        URL url = null;
        try {
            url = new URL("http://www.usd-cny.com/icbc.htm");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
            Log.i(TAG,"run:html=" +html);
        }catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "gb2312");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;

                out.append(buffer, 0, rsz);


        }
         return out.toString();
    }
}
