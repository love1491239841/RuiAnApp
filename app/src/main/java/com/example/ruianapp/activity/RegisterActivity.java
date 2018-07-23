package com.example.ruianapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.FormatUtils;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.NewUser;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText register_user,register_pass,register_pass1,register_phone,register_yx;
    private Button register;
    private TextView register_fh;
    private String user,pass,phone,yx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        register_user = (EditText)findViewById(R.id.register_User);
        register_pass = (EditText)findViewById(R.id.register_pass);
        register_pass1 = (EditText)findViewById(R.id.register_pass1);
        register_phone= (EditText) findViewById(R.id.register_phone);
        register_yx= (EditText) findViewById(R.id.register_yx);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        register_fh = (TextView)findViewById(R.id.register_bk);
        register_fh.setOnClickListener(this);
    }
    private void initData(){
        user=register_user.getText().toString();
        pass=register_pass.getText().toString();
        phone=register_phone.getText().toString();
        yx=register_yx.getText().toString();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                initData();
                okHttp();
                break;
            case R.id.register_bk:
                finish();
                break;
        }
    }

    private void okHttp() {
        if (user.length() ==0 || pass.length()==0 || phone.length()==0 || yx.length()==0){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
        }else {
            if (!FormatUtils.isEmail(yx)){
                Toast.makeText(this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
            }else if (!FormatUtils.isMobileNO(phone)){
                Toast.makeText(this, "请输入正确的电话格式", Toast.LENGTH_SHORT).show();
            }else {
                String addTime=new SimpleDateFormat("yyyy-MM-dd ").format(new Date());
                NewUser newUser = new NewUser(user,pass,phone,yx,addTime);
                Gson gson = new Gson();
                final String jsondata = gson.toJson(newUser);
                System.out.println("hcvjhdsbhcj"+jsondata);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UtlisOkhttp.sendUserJsonOkHttpRequest(jsondata, Constants.Register_URL,new Callback(){
                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseData=response.body().string();
                                    showResponse(responseData);
                                }
                                @Override
                                public void onFailure(Call call,IOException e){
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        }


    }
    private void showResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hcvjhdsbhcj"+response);
            }
        });
    }
}
