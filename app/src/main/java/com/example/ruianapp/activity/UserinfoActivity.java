package com.example.ruianapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.Userinfo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class UserinfoActivity extends AppCompatActivity {
    private ImageView info_usericon;
    private TextView  info_loginname,info_realname,info_Status,info_RoleId,info_phone,info_Email,info_comments;
    private Userinfo userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    public void initView(){
        info_usericon=(ImageView) findViewById(R.id.user_icon);
        info_loginname=(TextView) findViewById(R.id.text_loginname);
        info_realname=(TextView) findViewById(R.id.text_realname);
        info_Status=(TextView) findViewById(R.id.text_status);
        info_RoleId=(TextView) findViewById(R.id.text_roleId);
        info_phone=(TextView) findViewById(R.id.text_tel);
        info_Email=(TextView) findViewById(R.id.text_email);
        info_comments=(TextView) findViewById(R.id.text_comments);
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        sendRequestWithOkHttp(preferences.getString("name",null));

    }
    private void sendRequestWithOkHttp(final String name) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendNameOkHttpRequest(name, Constants.User_info_URL,new okhttp3.Callback(){
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
                try {
                    userinfo = JsonGet.Userinfo(response);
                    info_loginname.setText(userinfo.getLoginName());
                    info_realname.setText(userinfo.getRealName());
                    if (userinfo.isStatus()){
                        info_Status.setText("在线");
                    }else {
                        info_Status.setText("离线");
                    }
                    info_RoleId.setText(userinfo.getRoleId()+"");
                    info_phone.setText(userinfo.getTelephone());
                    info_Email.setText(userinfo.getEmail());
                    info_comments.setText(userinfo.getComments());


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_menu_xg:
                Intent intent = new Intent(UserinfoActivity.this,UserXgActivity.class);
                intent.putExtra("userinfo",userinfo);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        sendRequestWithOkHttp(preferences.getString("name",null));
    }

}
