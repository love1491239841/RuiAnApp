package com.example.ruianapp.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.Signin;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Response;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView fh;
    private Button signin_sb, signin_xb;
    private TextView signin_sbsj, signin_xbsj, signin_sj1, signin_sj2,signin_dz;
    private static String mWay;
    private LocationClient mLocationClient;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLicationListener());
        setContentView(R.layout.activity_signin);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initView();
        confi();

    }
    private void confi(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(SigninActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(SigninActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(SigninActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissons = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(SigninActivity.this,permissons,1);
        }else {
            requestLocation();
        }
    }
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
//        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    private void initView() {
        fh = (ImageView) findViewById(R.id.signin_fh);
        fh.setOnClickListener(this);
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        signin_sj1 = (TextView) findViewById(R.id.signin_time1);
        signin_sj2 = (TextView) findViewById(R.id.signin_time2);
        signin_dz = (TextView) findViewById(R.id.signin_dz);
        signin_sj1.setText(dateStr);
        signin_sj2.setText(StringData());
        signin_sb = (Button) findViewById(R.id.signin_sb);
        signin_xb = (Button) findViewById(R.id.signin_xb);
        signin_sb.setOnClickListener(this);
        signin_xb.setOnClickListener(this);
        signin_sbsj = (TextView) findViewById(R.id.signin_sbsj);
        signin_xbsj = (TextView) findViewById(R.id.signin_xbsj);
        SharedPreferences preferences = getSharedPreferences("Signin", MODE_PRIVATE);
        String sb = preferences.getString("sb", "");
        String xb = preferences.getString("xb", "");
        String sbTime = preferences.getString("sbTime", "");
        String xbTime = preferences.getString("xbTime", "");
        if (sb.equals(dateStr) && xb.equals(dateStr)) {
            signin_sb.setEnabled(false);
            signin_sb.setBackgroundResource(R.drawable.sign_shapefalse);
            signin_sbsj.setText(sbTime + "  " + "上班打卡成功");
            signin_xb.setEnabled(false);
            signin_xb.setBackgroundResource(R.drawable.sign_shapefalse);
            signin_xbsj.setText(xbTime + "  " + "下班打卡成功");
        } else {
            signin_xb.setEnabled(true);
            signin_sb.setEnabled(true);
            signin_sbsj.setText("未打卡");
            signin_xbsj.setText("未打卡");
        }
    }

    private void initData(final String JsonText, final String ifs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendUserJsonOkHttpRequest(JsonText, Constants.MySignin_URL, new okhttp3.Callback() {
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            showResponse(responseData, ifs);
                        }

                        @Override
                        public void onFailure(Call call, IOException e) {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void showResponse(final String response, final String ifs) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("jcsjhvsjc"+response);
                    if (ifs.equals("on")) {
                        if (JsonGet.loginMessage(response).getMsg().equals("操作成功")) {
                            SharedPreferences.Editor editor1 = getSharedPreferences("Signin", MODE_PRIVATE).edit();
                            Date date1 = new Date();
                            String dateStr1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date1);
                            Date date2 = new Date();
                            String dateStr2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
                            editor1.putString("sbTime", dateStr1);
                            editor1.putString("sb", dateStr2);
                            editor1.apply();
                            signin_sb.setEnabled(false);
                            signin_sb.setBackgroundResource(R.drawable.sign_shapefalse);
                            signin_sbsj.setText(dateStr1 + "  " + "上班打卡成功");
                        } else {
                            Toast.makeText(SigninActivity.this, "打卡失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (JsonGet.loginMessage(response).getMsg().equals("操作成功")) {
                            SharedPreferences.Editor editor2 = getSharedPreferences("Signin", MODE_PRIVATE).edit();
                            Date date3 = new Date();
                            String dateStr3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date3);
                            Date date4 = new Date();
                            String dateStr4 = new SimpleDateFormat("yyyy-MM-dd").format(date4);
                            editor2.putString("xbTime", dateStr3);
                            editor2.putString("xb", dateStr4);
                            editor2.apply();
                            signin_xb.setEnabled(false);
                            signin_xb.setBackgroundResource(R.drawable.sign_shapefalse);
                            signin_xbsj.setText(dateStr3 + "  " + "下班打卡成功");
                        } else {
                            Toast.makeText(SigninActivity.this, "打卡失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_sb:
                Date date1 = new Date();
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                String addTime1 = new SimpleDateFormat("yyyy-MM-dd ").format(date1);
                System.out.println("jcsjhvsjc"+addTime1);//
                Signin signin = new Signin(0,preferences.getInt("id",0),addTime1,"on",longitude+"",latitude+"");
                Gson gson = new Gson();
                String jsonText = gson.toJson(signin);
                System.out.println("jcsjhvsjc"+jsonText);
                initData(jsonText,"on");
                break;
            case R.id.signin_xb:
                Date date3 = new Date();
                SharedPreferences preferences1 = getSharedPreferences("data", MODE_PRIVATE);
                String addTime3 = new SimpleDateFormat("yyyy-MM-dd ").format(date3);
                Signin signin1 = new Signin(0,preferences1.getInt("id", 0), addTime3, "off", longitude+"",latitude+"");
                Gson gson1 = new Gson();
                String jsonText1 = gson1.toJson(signin1);
                initData(jsonText1, "off");
                break;
            case R.id.signin_fh:
                finish();
                break;
        }
    }

    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        Date date = new Date();
        String dateStr = new SimpleDateFormat("hh:mm").format(date);
        return dateStr + " " + "/星期" + mWay;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result :grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this, "发生未知的错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
    public class MyLicationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    latitude = bdLocation.getLatitude();
                    longitude = bdLocation.getLongitude();
                    signin_dz.setText("当前位置："+bdLocation.getCity()+"-"+bdLocation.getDistrict()+"-"+bdLocation.getStreet());
                }
            });
        }
    }
}
