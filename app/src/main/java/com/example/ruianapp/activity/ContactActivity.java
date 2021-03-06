package com.example.ruianapp.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.GetImageView;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.Gclx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText co_gcmc,co_gcdz,co_fbdw,co_sgdw,co_dgdw,co_zmr,co_problem;
    private Button co_qfrq,co_sjrq,co_sjrq2;
    private String bh,gcmc,gcdz,fbdw,sgdw,dgdw,zmr,problem,qfr,qfdw,sjr,sjr2,update_ids;
    private String qfrq,sjrq,sjrq2,add_time;
    private int user_id;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Gclx gclx;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;
    private ImageView co_qfr,co_sjr,co_sjr2;
    private LocationClient mLocationClient;
    private double latitude;
    private double longitude;
    private int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLicationListener());

        gclx = (Gclx) getIntent().getSerializableExtra("lx");
        if (gclx != null){
            if (gclx.isSubmited()){
                initobserve();
            }else {
                bh=gclx.getBh();
                requestLocation();
                id=gclx.getId();
                initSaved();

            }
        }else {
            bh="";
            id=0;
            requestLocation();
            initView();

        }

    }
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    private void initView(){
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        co_gcmc=(EditText)findViewById(R.id.co_name);
        co_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = co_gcmc.getText().toString();
//                List<EnterprisesList>enterprisesLists = DataSupport
//                        .where("name like ?", "%" + name + "%")
//                        .limit(5)
//                        .find(EnterprisesList.class);
                Cursor cursor = DataSupport.findBySQL("SELECT * FROM enterpriseslist WHERE NAME LIKE '%"+name+"%' limit 5");
                List<EnterprisesList>enterprisesLists = new ArrayList<>();
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) != null){
                        name1 = cursor.getString(cursor.getColumnIndex("name"));
                    }else {
                        name1 ="";
                    }
                    if (cursor.getString(cursor.getColumnIndex("legal")) != null){
                        legal = cursor.getString(cursor.getColumnIndex("legal"));
                    }else {
                        legal = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("address")) != null){
                        address = cursor.getString(cursor.getColumnIndex("address"));
                    }else {
                        address = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("phone")) != null){
                        phone = cursor.getString(cursor.getColumnIndex("phone"));
                    }else {
                        phone = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("email")) != null){
                        email = cursor.getString(cursor.getColumnIndex("email"));
                    }else {
                        email = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("jd")) != null){
                        jd = cursor.getString(cursor.getColumnIndex("jd"));
                    }else {
                        jd = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("wd")) != null){
                        wd = cursor.getString(cursor.getColumnIndex("wd"));
                    }else {
                        wd = "";
                    }
                    EnterprisesList enterprisesList = new EnterprisesList(name1,legal,address,phone,email,jd,wd);
                    enterprisesLists.add(enterprisesList);
                }
                initSearch(enterprisesLists);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        co_gcdz=(EditText)findViewById(R.id.co_add);
        co_fbdw=(EditText)findViewById(R.id.co_fbdw);
        co_sgdw=(EditText)findViewById(R.id.co_sgdw);
        co_dgdw=(EditText)findViewById(R.id.co_jddw);
        co_zmr=(EditText)findViewById(R.id.co_zhi);
        co_problem=(EditText)findViewById(R.id.co_note);
        co_qfrq=(Button)findViewById(R.id.co_time);
        co_sjrq=(Button)findViewById(R.id.co_time1);
        co_sjrq2=(Button)findViewById(R.id.co_time2);
        co_qfrq.setOnClickListener(this);
        co_sjrq.setOnClickListener(this);
        co_sjrq2.setOnClickListener(this);
        co_qfr = (ImageView) findViewById(R.id.co_qfr);
        co_sjr= (ImageView) findViewById(R.id.co_sjr);
        co_sjr2= (ImageView) findViewById(R.id.co_sjr2);
        co_qfr.setOnClickListener(this);
        co_sjr.setOnClickListener(this);
        co_sjr2.setOnClickListener(this);

    }
    private void initData(){
        gcmc=co_gcmc.getText().toString();
        gcdz=co_gcdz.getText().toString();
        fbdw=co_fbdw.getText().toString();
        sgdw=co_sgdw.getText().toString();
        dgdw=co_dgdw.getText().toString();
        zmr=co_zmr.getText().toString();
        problem=co_problem.getText().toString();
        qfr="";
        sjr="";
        sjr2="";
        qfdw="江苏瑞安安全科技发展有限公司";
        qfrq=co_qfrq.getText().toString();
        sjrq=co_sjrq.getText().toString();
        sjrq2=co_sjrq2.getText().toString();
        add_time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        user_id=preferences.getInt("id",0);
        update_ids=preferences.getInt("id",0)+"";
    }
    private void okHttp(boolean saved){
        Gson gson = new Gson();
        Gclx gcfk = new Gclx(id,gcmc,bh,gcdz,fbdw,sgdw,dgdw,zmr,problem,qfr,qfdw,qfrq,sjr,sjrq,sjr2,sjrq2,add_time,user_id,update_ids,latitude+"",longitude+"",saved,"","");
        final String jsonText=gson.toJson(gcfk);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendUserJsonOkHttpRequest(jsonText, Constants.GCLX_URL,new okhttp3.Callback(){
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
                if (JsonGet.loginMessage(response).getMsg().equals("操作成功")){
                    finish();
                }
            }
        });
    }
    private void okhttpimage(final boolean saved){
        final List<File> files = new ArrayList<>();
        files.add(new File(Constants.path+"qfr.png"));
        files.add(new File(Constants.path+"sjr.png"));
        files.add(new File(Constants.path+"sjr2.png"));
        final List<String> keys = new ArrayList<>();
        keys.add("qfr");
        keys.add("sjr");
        keys.add("sjr2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendImagesOkHttpRequest(keys,files, Constants.GCLXIMAGE_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showimgResponse(responseData,saved);
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
    private void showimgResponse(final String response,boolean saved) {
        //在子线程中更新UI
        System.out.println("asdfghjkl2"+response);
        try {
            JSONArray jsonArray = JsonGet.getpath(response);
            qfr=jsonArray.getString(0);
            sjr=jsonArray.getString(1);
            sjr2=jsonArray.getString(2);
            okHttp(saved);
        }catch (Exception e){
            e.printStackTrace();
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finesave_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示：");
                builder.setMessage("您确定退出？");

                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                //设置取消按钮
                builder.setNegativeButton("取消",null);
                //显示提示框
                builder.show();
                break;
            case R.id.finesave_menu_bc:
                if (gclx==null){
                    new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("是否保存，保存后可以修改" )
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    okhttpimage(false);
                                    if (co_qfr.getDrawable()==null||co_sjr.getDrawable()==null||co_sjr2.getDrawable()==null){
                                        Toast.makeText(ContactActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                    }else {
                                        if (co_qfrq.getText().length()==0 ||
                                                co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                            Toast.makeText(ContactActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                        }else {
                                            initData();
                                            if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || problem.length()==0 || co_qfrq.getText().length()==0 ||
                                                    co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                                Toast.makeText(ContactActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                                            }else {
                                                okhttpimage(false);
                                            }
                                        }
                                    }
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }else {
                    if (gclx.isSubmited()){
                        Toast.makeText(this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
                    }else {
                        new AlertDialog.Builder(this)
                                .setTitle("提示")
                                .setMessage("是否保存，保存后可以修改" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        okhttpimage(false);
                                        if (co_qfr.getDrawable()==null||co_sjr.getDrawable()==null||co_sjr2.getDrawable()==null){
                                            Toast.makeText(ContactActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                        }else {
                                            if (co_qfrq.getText().length()==0 ||
                                                    co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                                Toast.makeText(ContactActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                            }else {
                                                initData();
                                                if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || problem.length()==0 || co_qfrq.getText().length()==0 ||
                                                        co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                                    Toast.makeText(ContactActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                                                }else {
                                                    okhttpimage(false);
                                                }
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }
                }

                break;
            case R.id.finesave_menu_tj:
                if (gclx==null){
                    new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("是否提交，提交后无法修改" )
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (co_qfr.getDrawable()==null||co_sjr.getDrawable()==null||co_sjr2.getDrawable()==null){
                                        Toast.makeText(ContactActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                    }else {
                                        if (co_qfrq.getText().length()==0 ||
                                                co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                            Toast.makeText(ContactActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                        }else {
                                            initData();
                                            if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || problem.length()==0 || co_qfrq.getText().length()==0 ||
                                                    co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                                Toast.makeText(ContactActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                                            }else {
                                                okhttpimage(true);
                                            }
                                        }
                                    }
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }else {
                    if (gclx.isSubmited()){
                        Toast.makeText(this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
                    }else {
                        new AlertDialog.Builder(this)
                                .setTitle("提示")
                                .setMessage("是否提交，提交后无法修改" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (co_qfr.getDrawable()==null||co_sjr.getDrawable()==null||co_sjr2.getDrawable()==null){
                                            Toast.makeText(ContactActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                        }else {
                                            if (co_qfrq.getText().length()==0 ||
                                                    co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                                Toast.makeText(ContactActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                            }else {
                                                initData();
                                                if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || problem.length()==0 || co_qfrq.getText().length()==0 ||
                                                        co_sjrq.getText().length()==0 || co_sjrq2.getText().length()==0){
                                                    Toast.makeText(ContactActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                                                }else {
                                                    okhttpimage(true);
                                                }
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }

                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.co_time:
                DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                        co_qfrq.setText(date);
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(ContactActivity.this, 0,listener1,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();
                break;
            case R.id.co_time1:
                DatePickerDialog.OnDateSetListener listener2=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                        co_sjrq.setText(date);
                    }
                };
                DatePickerDialog dialog2=new DatePickerDialog(ContactActivity.this, 0,listener2,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog2.show();
                break;
            case R.id.co_time2:
                DatePickerDialog.OnDateSetListener listener3=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                        co_sjrq2.setText(date);
                    }
                };
                DatePickerDialog dialog3=new DatePickerDialog(ContactActivity.this, 0,listener3,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog3.show();
                break;
            case R.id.co_qfr:
                Intent intent = new Intent(ContactActivity.this, SignatureActivity.class);
                intent.putExtra("name","qfr.png");
                intent.putExtra("code",101);
                startActivityForResult(intent, 1);
                break;
            case R.id.co_sjr:
                Intent intent1 = new Intent(ContactActivity.this, SignatureActivity.class);
                intent1.putExtra("name","sjr.png");
                intent1.putExtra("code",102);
                startActivityForResult(intent1, 1);
                break;
            case R.id.co_sjr2:
                Intent intent2 = new Intent(ContactActivity.this, SignatureActivity.class);
                intent2.putExtra("name","sjr2.png");
                intent2.putExtra("code",103);
                startActivityForResult(intent2, 1);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("您确定退出？");

            //设置确定按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            //设置取消按钮
            builder.setNegativeButton("取消",null);
            //显示提示框
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    private void initobserve(){

        co_gcmc=(EditText)findViewById(R.id.co_name);
        co_gcmc.setText(gclx.getGcmc());
        co_gcmc.setEnabled(false);
        co_gcmc.setTextColor(Color.parseColor("#000000"));
        co_gcmc.setTextSize(18);
        co_gcdz=(EditText)findViewById(R.id.co_add);
        co_gcdz.setText(gclx.getGcdz());
        co_gcdz.setEnabled(false);
        co_gcdz.setTextColor(Color.parseColor("#000000"));
        co_gcdz.setTextSize(18);
        co_fbdw=(EditText)findViewById(R.id.co_fbdw);
        co_fbdw.setText(gclx.getFbdw());
        co_fbdw.setEnabled(false);
        co_fbdw.setTextColor(Color.parseColor("#000000"));
        co_fbdw.setTextSize(18);
        co_sgdw=(EditText)findViewById(R.id.co_sgdw);
        co_sgdw.setText(gclx.getSgdw());
        co_sgdw.setEnabled(false);
        co_sgdw.setTextColor(Color.parseColor("#000000"));
        co_sgdw.setTextSize(18);
        co_dgdw=(EditText)findViewById(R.id.co_jddw);
        co_dgdw.setText(gclx.getDgdw());
        co_dgdw.setEnabled(false);
        co_dgdw.setTextColor(Color.parseColor("#000000"));
        co_dgdw.setTextSize(18);
        co_zmr=(EditText)findViewById(R.id.co_zhi);
        co_zmr.setText(gclx.getZmr());
        co_zmr.setEnabled(false);
        co_zmr.setTextColor(Color.parseColor("#000000"));
        co_zmr.setTextSize(18);
        co_problem=(EditText)findViewById(R.id.co_note);
        co_problem.setText(gclx.getProblem());
        co_problem.setEnabled(false);
        co_problem.setTextColor(Color.parseColor("#000000"));
        co_problem.setTextSize(18);
        co_qfrq=(Button)findViewById(R.id.co_time);
        co_qfrq.setText(gclx.getQfrq());
        co_qfrq.setEnabled(false);
        co_qfrq.setTextColor(Color.parseColor("#000000"));
        co_qfrq.setTextSize(18);
        co_sjrq=(Button)findViewById(R.id.co_time1);
        co_sjrq.setText(gclx.getSjrq());
        co_sjrq.setEnabled(false);
        co_sjrq.setTextColor(Color.parseColor("#000000"));
        co_sjrq.setTextSize(18);
        co_sjrq2=(Button)findViewById(R.id.co_time2);
        co_sjrq2.setText(gclx.getSjrq2());
        co_sjrq2.setEnabled(false);
        co_sjrq2.setTextColor(Color.parseColor("#000000"));
        co_sjrq2.setTextSize(18);
        co_qfr = (ImageView) findViewById(R.id.co_qfr);
        co_sjr= (ImageView) findViewById(R.id.co_sjr);
        co_sjr2= (ImageView) findViewById(R.id.co_sjr2);
        Glide.with(this).load((Constants.IMGURL+gclx.getQfr()).replace("\\","/")).into(co_qfr);
        Glide.with(this).load((Constants.IMGURL+gclx.getSjr()).replace("\\","/")).into(co_sjr);
        Glide.with(this).load((Constants.IMGURL+gclx.getSjr2()).replace("\\","/")).into(co_sjr2);
    }
    //未提交初始化
    private void initSaved(){
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        co_gcmc=(EditText)findViewById(R.id.co_name);
        co_gcmc.setText(gclx.getGcmc());
        co_gcmc.setTextColor(Color.parseColor("#000000"));
        co_gcmc.setTextSize(18);
        co_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = co_gcmc.getText().toString();
                Cursor cursor = DataSupport.findBySQL("SELECT * FROM enterpriseslist WHERE NAME LIKE '%"+name+"%' limit 5");
                List<EnterprisesList>enterprisesLists = new ArrayList<>();
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) != null){
                        name1 = cursor.getString(cursor.getColumnIndex("name"));
                    }else {
                        name1 ="";
                    }
                    if (cursor.getString(cursor.getColumnIndex("legal")) != null){
                        legal = cursor.getString(cursor.getColumnIndex("legal"));
                    }else {
                        legal = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("address")) != null){
                        address = cursor.getString(cursor.getColumnIndex("address"));
                    }else {
                        address = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("phone")) != null){
                        phone = cursor.getString(cursor.getColumnIndex("phone"));
                    }else {
                        phone = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("email")) != null){
                        email = cursor.getString(cursor.getColumnIndex("email"));
                    }else {
                        email = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("jd")) != null){
                        jd = cursor.getString(cursor.getColumnIndex("jd"));
                    }else {
                        jd = "";
                    }
                    if (cursor.getString(cursor.getColumnIndex("wd")) != null){
                        wd = cursor.getString(cursor.getColumnIndex("wd"));
                    }else {
                        wd = "";
                    }
                    EnterprisesList enterprisesList = new EnterprisesList(name1,legal,address,phone,email,jd,wd);
                    enterprisesLists.add(enterprisesList);
                }
                initSearch(enterprisesLists);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        co_gcdz=(EditText)findViewById(R.id.co_add);
        co_gcdz.setText(gclx.getGcdz());
        co_gcdz.setTextColor(Color.parseColor("#000000"));
        co_gcdz.setTextSize(18);
        co_fbdw=(EditText)findViewById(R.id.co_fbdw);
        co_fbdw.setText(gclx.getFbdw());
        co_fbdw.setTextColor(Color.parseColor("#000000"));
        co_fbdw.setTextSize(18);
        co_sgdw=(EditText)findViewById(R.id.co_sgdw);
        co_sgdw.setText(gclx.getSgdw());
        co_sgdw.setTextColor(Color.parseColor("#000000"));
        co_sgdw.setTextSize(18);
        co_dgdw=(EditText)findViewById(R.id.co_jddw);
        co_dgdw.setText(gclx.getDgdw());
        co_dgdw.setTextColor(Color.parseColor("#000000"));
        co_dgdw.setTextSize(18);
        co_zmr=(EditText)findViewById(R.id.co_zhi);
        co_zmr.setText(gclx.getZmr());
        co_zmr.setTextColor(Color.parseColor("#000000"));
        co_zmr.setTextSize(18);
        co_problem=(EditText)findViewById(R.id.co_note);
        co_problem.setText(gclx.getProblem());
        co_problem.setTextColor(Color.parseColor("#000000"));
        co_qfrq=(Button)findViewById(R.id.co_time);
        co_qfrq.setText(gclx.getQfrq());
        co_qfrq.setTextColor(Color.parseColor("#000000"));
        co_sjrq=(Button)findViewById(R.id.co_time1);
        co_sjrq.setText(gclx.getSjrq());
        co_sjrq.setTextColor(Color.parseColor("#000000"));
        co_sjrq.setTextSize(18);
        co_sjrq2=(Button)findViewById(R.id.co_time2);
        co_sjrq2.setText(gclx.getSjrq2());
        co_sjrq2.setTextColor(Color.parseColor("#000000"));
        co_sjrq2.setTextSize(18);
        co_qfrq.setOnClickListener(this);
        co_sjrq.setOnClickListener(this);
        co_sjrq2.setOnClickListener(this);
        co_qfr = (ImageView) findViewById(R.id.co_qfr);
        co_sjr= (ImageView) findViewById(R.id.co_sjr);
        co_sjr2= (ImageView) findViewById(R.id.co_sjr2);
        co_qfr.setOnClickListener(this);
        co_sjr.setOnClickListener(this);
        co_sjr2.setOnClickListener(this);
        Glide
                .with(this)
                .load((Constants.IMGURL+gclx.getQfr()).replace("\\","/"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        co_qfr.setImageBitmap(resource);
                        GetImageView.saveImage(resource,"qfr.png");
                    }
                });
        Glide.with(this).load((Constants.IMGURL+gclx.getSjr()).replace("\\","/"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        co_sjr.setImageBitmap(resource);
                        GetImageView.saveImage(resource,"sjr.png");
                    }
                });
        Glide.with(this).load((Constants.IMGURL+gclx.getSjr2()).replace("\\","/"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        co_sjr2.setImageBitmap(resource);
                        GetImageView.saveImage(resource,"sjr2.png");
                    }
                });

    }
    private void initSearch(final List<EnterprisesList> enterprisesLists){
        String[] data=new String[enterprisesLists.size()];
        for (int i =0;i<enterprisesLists.size();i++){
            data[i]=enterprisesLists.get(i).getName();
        }
        popupView = getLayoutInflater().inflate(R.layout.finepopupwindow_item,null,false);
        ListView listView = (ListView) popupView.findViewById(R.id.popup_item_listview);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                co_gcmc.setText(enterprisesLists.get(position).getName());
                co_fbdw.setText(enterprisesLists.get(position).getName());
                popupWindow.dismiss();
            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(co_gcmc);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    backgroundAlpha(1.0f);
                    popupWindow.dismiss();
                }
                return false;
            }
        });
    }
    private void backgroundAlpha(float f){
        WindowManager.LayoutParams lp=this.getWindow().getAttributes();
        lp.alpha=f;
        this.getWindow().setAttributes(lp);
    }

    public class MyLicationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    latitude = bdLocation.getLatitude();
                    longitude = bdLocation.getLongitude();
                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 101) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"qfr.png", options);
            co_qfr.setImageBitmap(bm);
//            Glide.with(this).load(path + ".sign").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        }else if (resultCode==102){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"sjr.png", options);
            co_sjr.setImageBitmap(bm);
        }else if (resultCode==103){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"sjr2.png", options);
            co_sjr2.setImageBitmap(bm);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
