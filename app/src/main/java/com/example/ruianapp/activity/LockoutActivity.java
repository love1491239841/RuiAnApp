package com.example.ruianapp.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.Gcfk;
import com.example.ruianapp.bean.Gclx;
import com.example.ruianapp.bean.Gctg;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class LockoutActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView lockout_fh;
    private TextView lockout_titlename;
    private ImageView lockout_tj;
    private EditText lo_gcmc,lo_gcdz,lo_fbdw,lo_sgdw,lo_dgdw,lo_zmr,lo_problem,lo_other,lo_qsyj,lo_fcyj,lo_cs;
    private Button lo_cljzsj,lo_qfrq,lo_qsrq,lo_fcrq,lo_fssj;
    String gcmc,gcdz,fbdw,sgdw,dgdw,zmr,fssj,problem,other,qfr,qfdw,qsyj,qsr,fcyj,fcr,cs,update_ids;
    private String cljzsj,qfrq,qsrq,fcrq,add_time;
    private int user_id;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Gctg gctg;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;
    private ImageView lo_qfr,lo_qsr,lo_fcr;
    private LocationClient mLocationClient;
    private double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockout);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLicationListener());
        gctg = (Gctg) getIntent().getSerializableExtra("tg");
        if (gctg!=null){
            initbserve();
        }else {
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
//        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        lockout_fh=(ImageView)findViewById(R.id.lockout_fh);
        lockout_tj=(ImageView)findViewById(R.id.lockout_tj);
        lockout_fh.setOnClickListener(this);
        lockout_tj.setOnClickListener(this);
        lockout_titlename=(TextView)findViewById(R.id.lockout_titlename);
        lockout_titlename.setText("工程停工通知单");
        lo_gcmc=(EditText)findViewById(R.id.lo_name);
        lo_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = lo_gcmc.getText().toString();
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
        lo_gcdz=(EditText)findViewById(R.id.lo_add);
        lo_fbdw=(EditText)findViewById(R.id.lo_fbdw);
        lo_sgdw=(EditText)findViewById(R.id.lo_sgdw);
        lo_dgdw=(EditText)findViewById(R.id.lo_ggdw);
        lo_zmr=(EditText)findViewById(R.id.lo_zhi);
        lo_fssj= (Button) findViewById(R.id.lo_time);
        lo_fssj.setOnClickListener(this);
        lo_problem=(EditText)findViewById(R.id.lo_problem);
        lo_other=(EditText)findViewById(R.id.lo_other);
        lo_qsyj=(EditText)findViewById(R.id.lo_qsyj);
        lo_fcyj=(EditText)findViewById(R.id.lo_fcyj);
        lo_cs=(EditText)findViewById(R.id.lo_cs);
        lo_cljzsj=(Button)findViewById(R.id.lo_cljzsj);
        lo_qfrq=(Button)findViewById(R.id.lo_qfrq);
        lo_qsrq=(Button)findViewById(R.id.lo_qsrq);
        lo_fcrq=(Button)findViewById(R.id.lo_fcrq);
        lo_cljzsj.setOnClickListener(this);
        lo_qfrq.setOnClickListener(this);
        lo_qsrq.setOnClickListener(this);
        lo_fcrq.setOnClickListener(this);
        lo_qfr= (ImageView) findViewById(R.id.lo_qfr);
        lo_qsr= (ImageView) findViewById(R.id.lo_qsr);
        lo_fcr= (ImageView) findViewById(R.id.lo_fcr);

    }
    private void initData(){
        gcmc=lo_gcmc.getText().toString();
        gcdz=lo_gcdz.getText().toString();
        fbdw=lo_fbdw.getText().toString();
        sgdw=lo_sgdw.getText().toString();
        dgdw=lo_dgdw.getText().toString();
        zmr=lo_zmr.getText().toString();
        fssj=lo_fssj.getText().toString();
        problem=lo_problem.getText().toString();
        cljzsj=lo_cljzsj.getText().toString();
        other=lo_other.getText().toString();
        qfr="江苏瑞安安全科技发展有限公司";
        qfdw="江苏瑞安安全科技发展有限公司";
        qfrq=lo_qfrq.getText().toString();
        qsyj=lo_qsyj.getText().toString();
//        qsr=lo_qsr.getText().toString();
        qsrq=lo_qsrq.getText().toString();
        fcyj=lo_fcyj.getText().toString();
//        fcr=lo_fcr.getText().toString();
        fcrq=lo_fcrq.getText().toString();
        cs=lo_cs.getText().toString();
        add_time=new SimpleDateFormat("yyyy-MM-dd ").format(new Date());
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        user_id=preferences.getInt("id",0);
        update_ids=preferences.getInt("id",0)+"";
    }
    private void okHttp(boolean saved){
        if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || fssj.length()==0 ||
                fssj.length()==0 || problem.length()==0 || other.length()==0 || qsyj.length()==0 || fcyj.length()==0 || cs.length()==0){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
        }else {
            Gson gson = new Gson();
            Gctg gcfk = new Gctg(0,gcmc,"", gcdz, fbdw, sgdw, dgdw, zmr, fssj, problem, cljzsj, other, qfr, qfdw, qfrq, qsyj, "", qsrq, fcyj, "", fcrq, cs, add_time, user_id, update_ids,latitude+"",longitude+"",saved);
            final String jsonText = gson.toJson(gcfk);
            System.out.println("123456" + jsonText);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UtlisOkhttp.sendUserJsonOkHttpRequest(jsonText, Constants.GCTG_URL, new okhttp3.Callback() {
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String responseData = response.body().string();
                                showResponse(responseData);
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
    private void okhttpimage(){
        final List<File> files = new ArrayList<>();
        files.add(new File(Constants.path+"qfr.png"));
        files.add(new File(Constants.path+"qsr.png"));
        files.add(new File(Constants.path+"fcr.png"));
        final List<String> keys = new ArrayList<>();
        keys.add("qfr");
        keys.add("qsr");
        keys.add("fcr");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendImagesOkHttpRequest(keys,files, Constants.GCFKIMAGE_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showimgResponse(responseData);
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
    private void showimgResponse(final String response) {
        //在子线程中更新UI
        System.out.println("asdfghjkl2"+response);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lockout_fh:
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
            case R.id.lockout_tj:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("是否提交，提交后无法修改" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (lo_cljzsj.getText().length()==0 ||
                                        lo_qfrq.getText().length()==0 || lo_qsrq.getText().length()==0 || lo_fcrq.getText().length()==0){
                                    Toast.makeText(LockoutActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                }else {
                                    okhttpimage();
                                    initData();
                                    okHttp(true);

                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.lo_cljzsj:
                DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(LockoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                lo_cljzsj.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(LockoutActivity.this, 0,listener1,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();
                break;
            case R.id.lo_qfrq:
                DatePickerDialog.OnDateSetListener listener2=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(LockoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                lo_qfrq.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog2=new DatePickerDialog(LockoutActivity.this, 0,listener2,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog2.show();
                break;
            case R.id.lo_qsrq:
                DatePickerDialog.OnDateSetListener listener3=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(LockoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                lo_qsrq.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog3=new DatePickerDialog(LockoutActivity.this, 0,listener3,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog3.show();
                break;
            case R.id.lo_fcrq:
                DatePickerDialog.OnDateSetListener listener4=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(LockoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                lo_fcrq.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog4=new DatePickerDialog(LockoutActivity.this, 0,listener4,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog4.show();
                break;
            case R.id.lo_time:
                DatePickerDialog.OnDateSetListener listener5=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(LockoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                lo_fssj.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog5=new DatePickerDialog(LockoutActivity.this, 0,listener5,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog5.show();
                break;
            case R.id.lo_qfr:
                Intent intent = new Intent(LockoutActivity.this, SignatureActivity.class);
                intent.putExtra("name","qfr.png");
                intent.putExtra("code",101);
                startActivityForResult(intent, 1);
                break;
            case R.id.lo_qsr:
                Intent intent1 = new Intent(LockoutActivity.this, SignatureActivity.class);
                intent1.putExtra("name","qsr.png");
                intent1.putExtra("code",102);
                startActivityForResult(intent1, 1);
                break;
            case R.id.lo_fcr:
                Intent intent2 = new Intent(LockoutActivity.this, SignatureActivity.class);
                intent2.putExtra("name","fcr.png");
                intent2.putExtra("code",103);
                startActivityForResult(intent2, 1);
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 101) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"qfr.png", options);
            lo_qfr.setImageBitmap(bm);
//            Glide.with(this).load(path + ".sign").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        }else if (resultCode==102){
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"sjr.png", options);
            lo_qsr.setImageBitmap(bm);
        }else if (resultCode==103){
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"sjr2.png", options);
            lo_fcr.setImageBitmap(bm);
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

    public static Date stringToDate(String source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (Exception e) {
        }
        return date;
    }
    private void initbserve(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        lockout_fh=(ImageView)findViewById(R.id.lockout_fh);
        lockout_tj=(ImageView)findViewById(R.id.lockout_tj);
        lockout_fh.setOnClickListener(this);
        lockout_tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LockoutActivity.this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
            }
        });
        lockout_titlename=(TextView)findViewById(R.id.lockout_titlename);
        lockout_titlename.setText(gctg.getGcmc());
        lo_gcmc=(EditText)findViewById(R.id.lo_name);
        lo_gcmc.setText(gctg.getGcmc());
        lo_gcmc.setTextColor(Color.parseColor("#000000"));
        lo_gcmc.setTextSize(18);
        lo_gcmc.setEnabled(false);
        lo_gcdz=(EditText)findViewById(R.id.lo_add);
        lo_gcdz.setText(gctg.getGcdz());
        lo_gcdz.setTextColor(Color.parseColor("#000000"));
        lo_gcdz.setTextSize(18);
        lo_gcdz.setEnabled(false);
        lo_fbdw=(EditText)findViewById(R.id.lo_fbdw);
        lo_fbdw.setText(gctg.getFbdw());
        lo_fbdw.setTextColor(Color.parseColor("#000000"));
        lo_fbdw.setTextSize(18);
        lo_fbdw.setEnabled(false);
        lo_sgdw=(EditText)findViewById(R.id.lo_sgdw);
        lo_sgdw.setText(gctg.getSgdw());
        lo_sgdw.setTextColor(Color.parseColor("#000000"));
        lo_sgdw.setTextSize(18);
        lo_sgdw.setEnabled(false);
        lo_dgdw=(EditText)findViewById(R.id.lo_ggdw);
        lo_dgdw.setText(gctg.getDgdw());
        lo_dgdw.setTextColor(Color.parseColor("#000000"));
        lo_dgdw.setTextSize(18);
        lo_dgdw.setEnabled(false);
        lo_zmr=(EditText)findViewById(R.id.lo_zhi);
        lo_zmr.setText(gctg.getZmr());
        lo_zmr.setTextColor(Color.parseColor("#000000"));
        lo_zmr.setTextSize(18);
        lo_zmr.setEnabled(false);
        lo_fssj= (Button) findViewById(R.id.lo_time);
        lo_fssj.setText(gctg.getFssj());
        lo_fssj.setTextColor(Color.parseColor("#000000"));
        lo_fssj.setTextSize(18);
        lo_fssj.setEnabled(false);
        lo_problem=(EditText)findViewById(R.id.lo_problem);
        lo_problem.setText(gctg.getProblem());
        lo_problem.setTextColor(Color.parseColor("#000000"));
        lo_problem.setTextSize(18);
        lo_problem.setEnabled(false);
        lo_other=(EditText)findViewById(R.id.lo_other);
        lo_other.setText(gctg.getOther());
        lo_other.setTextColor(Color.parseColor("#000000"));
        lo_other.setTextSize(18);
        lo_other.setEnabled(false);
        lo_qsyj=(EditText)findViewById(R.id.lo_qsyj);
        lo_qsyj.setText(gctg.getQsyj());
        lo_qsyj.setTextColor(Color.parseColor("#000000"));
        lo_qsyj.setTextSize(18);
        lo_qsyj.setEnabled(false);
//        lo_qsr=(EditText)findViewById(R.id.lo_qsr);
//        lo_qsr.setText(gctg.getQsr());
//        lo_qsr.setTextColor(Color.parseColor("#000000"));
//        lo_qsr.setTextSize(18);
//        lo_qsr.setEnabled(false);
        lo_fcyj=(EditText)findViewById(R.id.lo_fcyj);
        lo_fcyj.setText(gctg.getFcyj());
        lo_fcyj.setTextColor(Color.parseColor("#000000"));
        lo_fcyj.setTextSize(18);
        lo_fcyj.setEnabled(false);
//        lo_fcr=(EditText)findViewById(R.id.lo_fcr);
//        lo_fcr.setText(gctg.getFcr());
//        lo_fcr.setTextColor(Color.parseColor("#000000"));
//        lo_fcr.setTextSize(18);
//        lo_fcr.setEnabled(false);
        lo_cs=(EditText)findViewById(R.id.lo_cs);
        lo_cs.setText(gctg.getCs());
        lo_cs.setTextColor(Color.parseColor("#000000"));
        lo_cs.setTextSize(18);
        lo_cs.setEnabled(false);
        lo_cljzsj=(Button)findViewById(R.id.lo_cljzsj);
        lo_cljzsj.setText(gctg.getCljzsj());
        lo_cljzsj.setTextColor(Color.parseColor("#000000"));
        lo_cljzsj.setTextSize(18);
        lo_cljzsj.setEnabled(false);
        lo_qfrq=(Button)findViewById(R.id.lo_qfrq);
        lo_qfrq.setText(gctg.getQfrq());
        lo_qfrq.setTextColor(Color.parseColor("#000000"));
        lo_qfrq.setTextSize(18);
        lo_qfrq.setEnabled(false);
        lo_qsrq=(Button)findViewById(R.id.lo_qsrq);
        lo_qsrq.setText(gctg.getQsrq());
        lo_qsrq.setTextColor(Color.parseColor("#000000"));
        lo_qsrq.setTextSize(18);
        lo_qsrq.setEnabled(false);
        lo_fcrq=(Button)findViewById(R.id.lo_fcrq);
        lo_fcrq.setText(gctg.getFcrq());
        lo_fcrq.setTextColor(Color.parseColor("#000000"));
        lo_fcrq.setTextSize(18);
        lo_fcrq.setEnabled(false);
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
                lo_gcmc.setText(enterprisesLists.get(position).getName());
                lo_fbdw.setText(enterprisesLists.get(position).getName());
                popupWindow.dismiss();

            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(lo_gcmc);
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
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
