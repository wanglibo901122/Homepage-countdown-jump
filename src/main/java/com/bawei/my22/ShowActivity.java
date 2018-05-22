package com.bawei.my22;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * daojishi
     */
    private TextView mTime1;
    /**
     * pass>>>>>>
     */
    int num=5;

    private SharedPreferences sp;
      private SharedPreferences.Editor edit;;
    private TextView mJusp;
    private Boolean is_=false;

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler( ){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){

                num--;
                mTime1.setText("倒计时："+num);
                if(num>0){
                    Message message = handler.obtainMessage(1);
                    handler.sendMessageDelayed(message,1000);
                }else {
                    gohome();
                }
            }
            super.handleMessage(msg);
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        sp=getSharedPreferences("user",MODE_PRIVATE);
        edit = sp.edit();
        //取出存进去的值 进行判断
        boolean spp = sp.getBoolean("spp", false);
        if(spp){
            gohome();
        }else{
            //第一次登录进行读秒跳转
            Message message = handler.obtainMessage(1);
            handler.sendMessageDelayed(message,1000);
        }


       if(!is_){
            //第一次登录  存进去一个  值
           edit.putBoolean("spp",true);
           edit.commit();
       }else{
           //第二次登录 直接进入 跳过页面
           gohome();
       }


    }

    private void initView() {
        mTime1 = (TextView) findViewById(R.id.time1);
        mJusp = (TextView) findViewById(R.id.jusp);
        mJusp.setOnClickListener(this);

    }
    private void gohome() {
        Intent intent = new Intent(ShowActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        gohome();
    }
}
