package com.example.ruianapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class PasswordSzActivity extends AppCompatActivity {
    private EditText passwordsz_psd1,passwordsz_psd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_sz);
        initView();
    }
    private void initView(){
        passwordsz_psd1=(EditText)findViewById(R.id.passwordsz_psw1);
        passwordsz_psd2=(EditText)findViewById(R.id.passwordsz_psw2);
    }
    private void initData(final String name){
        System.out.println("123456777"+name+"oooooo"+passwordsz_psd1.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendnewPasswordOkHttpRequest(name,passwordsz_psd1.getText().toString(), Constants.PassWord_URL,new okhttp3.Callback(){
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
    private void showResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("123456777"+response);
            }
        });
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.passwordsz_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pass_wc:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示：");
                builder.setMessage("您确定修改密码？");
                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (passwordsz_psd1.getText().toString().length() ==0 || passwordsz_psd2.getText().toString().length()==0){
                            Toast.makeText(PasswordSzActivity.this, "两次密码不能为空", Toast.LENGTH_SHORT).show();
                        }{
                            if (passwordsz_psd1.getText().toString().equals(passwordsz_psd2.getText().toString())){
                                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                                initData(preferences.getString("name",null));
                            }else {
                                Toast.makeText(PasswordSzActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                //设置取消按钮
                builder.setNegativeButton("取消",null);
                //显示提示框
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
