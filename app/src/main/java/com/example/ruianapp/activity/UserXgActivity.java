package com.example.ruianapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.FormatUtils;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.Userinfo;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Response;

public class UserXgActivity extends AppCompatActivity {
    private EditText userxg_name,userxg_phone,userxg_email,userxg_comments;
    private String jsonText;
    private String name,phone,email,comments;
    private Userinfo userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_xg);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        userxg_name=(EditText)findViewById(R.id.userxg_name);
        userxg_phone=(EditText)findViewById(R.id.userxg_phone);
        userxg_email=(EditText)findViewById(R.id.userxg_email);
        userxg_comments=(EditText)findViewById(R.id.userxg_comments);
        userinfo = (Userinfo) getIntent().getSerializableExtra("userinfo");
        userxg_name.setText(userinfo.getRealName());
        userxg_phone.setText(userinfo.getTelephone());
        userxg_email.setText(userinfo.getEmail());
        userxg_comments.setText(userinfo.getComments());
    }
    private void initData(){
        name =userxg_name.getText().toString();
        phone =userxg_phone.getText().toString();
        email =userxg_email.getText().toString();
        comments =userxg_comments.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userxg_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.userxg_menu_qr:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示：");
                builder.setMessage("您确定修改吗？");

                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int hich) {
                        initData();
                        if (name.length()==0 || phone.length()==0 || email.length()==0 ||comments.length()==0 ){
                            Toast.makeText(UserXgActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                        }else if (!FormatUtils.isEmail(email)){
                            Toast.makeText(UserXgActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();

                        }else if (!FormatUtils.isMobileNO(phone)){
                            Toast.makeText(UserXgActivity.this, "电话格式不正确", Toast.LENGTH_SHORT).show();
                        }else {
                            userinfo = (Userinfo) getIntent().getSerializableExtra("userinfo");
                            userinfo.setRealName(name);
                            userinfo.setTelephone(phone);
                            userinfo.setEmail(email);
                            userinfo.setComments(comments);
                            Gson gson =new Gson();
                            jsonText=gson.toJson(userinfo);
                            sendRequestWithOkHttp(jsonText);
                            finish();
                        }

                    }
                });
                //设置取消按钮
                builder.setNegativeButton("取消",null);
                //显示提示框
                builder.show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void sendRequestWithOkHttp(final String jsondata) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendUserJsonOkHttpRequest(jsondata, Constants.UserXg_URL,new okhttp3.Callback(){
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
                Toast.makeText(UserXgActivity.this,JsonGet.loginMessage(response).getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
