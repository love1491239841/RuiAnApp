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
import com.example.ruianapp.bean.Gctg;
import com.example.ruianapp.bean.Zgtz;
import com.google.gson.Gson;

import org.json.JSONArray;
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

public class SettleActivity extends AppCompatActivity implements View.OnClickListener{
//    private ImageView settle_fh;
//    private TextView settle_titlename;
//    private LinearLayout settle_tj,settle_bc;
    private EditText se_gcmc,se_gcdz,se_fbdw,se_sgdw,se_dgdw,se_zmr,se_problem,se_other,se_qsyj,se_fcyj,se_cs;
    private Button se_cljzsj,se_qfrq,se_qsrq,se_fcrq,se_fssj;
    private String bh,gcmc,gcdz,fbdw,sgdw,dgdw,zmr,fssj,problem,other,qfr,qfdw,qsyj,qsr,fcyj,fcr,cs,update_ids;
    private String cljzsj,qfrq,qsrq,fcrq,add_time;
    private int user_id;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Zgtz zgtz;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;
    private LocationClient mLocationClient;
    private double latitude;
    private double longitude;
    private ImageView se_qfr,se_qsr,se_fcr;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLicationListener());
        zgtz = (Zgtz) getIntent().getSerializableExtra("zgtz");
        if (zgtz !=null){
            if (zgtz.isSubmited()){
                initobserve();
            }else {
                bh=zgtz.getBh();
                requestLocation();
                id=zgtz.getId();
                initSeavd();
            }
        }else {
            bh="";
            id=0;
            initView();
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
    private void initView(){
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        se_gcmc=(EditText)findViewById(R.id.se_name);
        se_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = se_gcmc.getText().toString();
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
        se_gcdz=(EditText)findViewById(R.id.se_add);
        se_fbdw=(EditText)findViewById(R.id.se_fbdw);
        se_sgdw=(EditText)findViewById(R.id.se_sgdw);
        se_dgdw=(EditText)findViewById(R.id.se_jgdw);
        se_zmr=(EditText)findViewById(R.id.se_zhi);
        se_fssj= (Button) findViewById(R.id.se_time);
        se_fssj.setOnClickListener(this);
        se_problem=(EditText)findViewById(R.id.se_problem);
        se_other=(EditText)findViewById(R.id.se_other);
        se_qsyj=(EditText)findViewById(R.id.se_qsyj);
        se_fcyj=(EditText)findViewById(R.id.se_fcyj);
        se_cs=(EditText)findViewById(R.id.se_cs);
        se_cljzsj=(Button)findViewById(R.id.se_cljzsj);
        se_qfrq=(Button)findViewById(R.id.se_qfrq);
        se_qsrq=(Button)findViewById(R.id.se_qsrq);
        se_fcrq=(Button)findViewById(R.id.se_fcrq);
        se_cljzsj.setOnClickListener(this);
        se_qfrq.setOnClickListener(this);
        se_qsrq.setOnClickListener(this);
        se_fcrq.setOnClickListener(this);
        se_qsr= (ImageView) findViewById(R.id.se_qsr);
        se_fcr= (ImageView) findViewById(R.id.se_fcr);
        se_qfr= (ImageView) findViewById(R.id.se_qfr);
        se_qfr.setOnClickListener(this);
        se_fcr.setOnClickListener(this);
        se_qsr.setOnClickListener(this);

    }
    private void initData(){
        gcmc=se_gcmc.getText().toString();
        gcdz=se_gcdz.getText().toString();
        fbdw=se_fbdw.getText().toString();
        sgdw=se_sgdw.getText().toString();
        dgdw=se_dgdw.getText().toString();
        zmr=se_zmr.getText().toString();
        fssj=se_fssj.getText().toString();
        problem=se_problem.getText().toString();
        cljzsj=se_cljzsj.getText().toString();
        other=se_other.getText().toString();
        qfr="";
        qsr="";
        fcr="";
        qfdw="江苏瑞安安全科技发展有限公司";
        qfrq=se_qfrq.getText().toString();
        qsyj=se_qsyj.getText().toString();
        qsrq=se_qsrq.getText().toString();
        fcyj=se_fcyj.getText().toString();
        fcrq=se_fcrq.getText().toString();
        cs=se_cs.getText().toString();
        add_time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        user_id=preferences.getInt("id",0);
        update_ids=preferences.getInt("id",0)+"";
    }
    private void okHttp(boolean saved){
        Gson gson = new Gson();
        Zgtz gcfk = new Zgtz(id,gcmc,bh, gcdz, fbdw, sgdw, dgdw, zmr, fssj, problem, cljzsj, other, qfr, qfdw, qfrq, qsyj, qsr, qsrq, fcyj, fcr, fcrq, cs, add_time, user_id, update_ids,latitude+"",longitude+"",saved,"","");
        final String jsonText = gson.toJson(gcfk);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendUserJsonOkHttpRequest(jsonText, Constants.ZGTZ_URL, new okhttp3.Callback() {
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
    private void showResponse(final String response) {
        //在子线程中更新UI
        System.out.println("asdfghjkl2"+response);
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
                    UtlisOkhttp.sendImagesOkHttpRequest(keys,files, Constants.GCZGIMAGE_URL,new okhttp3.Callback(){
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
            qsr=jsonArray.getString(1);
            fcr=jsonArray.getString(2);
            okHttp(saved);
        }catch (Exception e){
            e.printStackTrace();
        }
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
                if (zgtz==null){
                    new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("是否保存，保存后可以修改" )
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (se_qfr.getDrawable()==null || se_qsr.getDrawable()==null || se_fcr.getDrawable()==null){
                                        Toast.makeText(SettleActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                    }else {
                                        if (se_cljzsj.getText().length()==0 ||
                                                se_qfrq.getText().length()==0 || se_qsrq.getText().length()==0 || se_fcrq.getText().length()==0){
                                            Toast.makeText(SettleActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                        }else {
                                            initData();
                                            if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || fssj.length()==0 ||
                                                    fssj.length()==0 || problem.length()==0 || other.length()==0 || qsyj.length()==0 || fcyj.length()==0|| cs.length()==0){
                                                Toast.makeText(SettleActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
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
                    if (zgtz.isSubmited()){
                        Toast.makeText(this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
                    }else {
                        new AlertDialog.Builder(this)
                                .setTitle("提示")
                                .setMessage("是否保存，保存后可以修改" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (se_qfr.getDrawable()==null || se_qsr.getDrawable()==null || se_fcr.getDrawable()==null){
                                            Toast.makeText(SettleActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                        }else {
                                            if (se_cljzsj.getText().length()==0 ||
                                                    se_qfrq.getText().length()==0 || se_qsrq.getText().length()==0 || se_fcrq.getText().length()==0){
                                                Toast.makeText(SettleActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                            }else {
                                                initData();
                                                if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || fssj.length()==0 ||
                                                        fssj.length()==0 || problem.length()==0 || other.length()==0 || qsyj.length()==0 || fcyj.length()==0|| cs.length()==0){
                                                    Toast.makeText(SettleActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
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
                if (zgtz==null){
                    new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("是否提交，提交后无法修改" )
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (se_qfr.getDrawable()==null || se_qsr.getDrawable()==null || se_fcr.getDrawable()==null){
                                        Toast.makeText(SettleActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                    }else {
                                        if (se_cljzsj.getText().length()==0 ||
                                                se_qfrq.getText().length()==0 || se_qsrq.getText().length()==0 || se_fcrq.getText().length()==0){
                                            Toast.makeText(SettleActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                        }else {
                                            initData();
                                            if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || fssj.length()==0 ||
                                                    fssj.length()==0 || problem.length()==0 || other.length()==0 || qsyj.length()==0 || fcyj.length()==0|| cs.length()==0){
                                                Toast.makeText(SettleActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
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
                    if (zgtz.isSubmited()){
                        Toast.makeText(this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
                    }else {
                        new AlertDialog.Builder(this)
                                .setTitle("提示")
                                .setMessage("是否提交，提交后无法修改" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (se_qfr.getDrawable()==null || se_qsr.getDrawable()==null || se_fcr.getDrawable()==null){
                                            Toast.makeText(SettleActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                        }else {
                                            if (se_cljzsj.getText().length()==0 ||
                                                    se_qfrq.getText().length()==0 || se_qsrq.getText().length()==0 || se_fcrq.getText().length()==0){
                                                Toast.makeText(SettleActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                            }else {
                                                initData();
                                                if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || fssj.length()==0 ||
                                                        fssj.length()==0 || problem.length()==0 || other.length()==0 || qsyj.length()==0 || fcyj.length()==0|| cs.length()==0){
                                                    Toast.makeText(SettleActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
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
            case R.id.se_cljzsj:
                DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(SettleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                se_cljzsj.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(SettleActivity.this, 0,listener1,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();
                break;
            case R.id.se_qfrq:
                DatePickerDialog.OnDateSetListener listener2=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                        se_qfrq.setText(date);
                    }
                };
                DatePickerDialog dialog2=new DatePickerDialog(SettleActivity.this, 0,listener2,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog2.show();
                break;
            case R.id.se_qsrq:
                DatePickerDialog.OnDateSetListener listener3=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                        se_qsrq.setText(date);
                    }
                };
                DatePickerDialog dialog3=new DatePickerDialog(SettleActivity.this, 0,listener3,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog3.show();
                break;
            case R.id.se_fcrq:
                DatePickerDialog.OnDateSetListener listener4=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                        se_fcrq.setText(date);
                    }
                };
                DatePickerDialog dialog4=new DatePickerDialog(SettleActivity.this, 0,listener4,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog4.show();
                break;

            case R.id.se_time:
                DatePickerDialog.OnDateSetListener listener5=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(SettleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                se_fssj.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog5=new DatePickerDialog(SettleActivity.this, 0,listener5,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog5.show();
                break;
            case R.id.se_qfr:
                Intent intent = new Intent(SettleActivity.this, SignatureActivity.class);
                intent.putExtra("name","qfr.png");
                intent.putExtra("code",101);
                startActivityForResult(intent, 1);
                break;
            case R.id.se_qsr:
                Intent intent1 = new Intent(SettleActivity.this, SignatureActivity.class);
                intent1.putExtra("name","qsr.png");
                intent1.putExtra("code",102);
                startActivityForResult(intent1, 1);
                break;
            case R.id.se_fcr:
                Intent intent2 = new Intent(SettleActivity.this, SignatureActivity.class);
                intent2.putExtra("name","fcr.png");
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
        se_gcmc=(EditText)findViewById(R.id.se_name);
        se_gcmc.setText(zgtz.getGcmc());
        se_gcmc.setTextColor(Color.parseColor("#000000"));
        se_gcmc.setTextSize(18);
        se_gcmc.setEnabled(false);
        se_gcdz=(EditText)findViewById(R.id.se_add);
        se_gcdz.setText(zgtz.getGcdz());
        se_gcdz.setTextColor(Color.parseColor("#000000"));
        se_gcdz.setTextSize(18);
        se_gcdz.setEnabled(false);
        se_fbdw=(EditText)findViewById(R.id.se_fbdw);
        se_fbdw.setText(zgtz.getFbdw());
        se_fbdw.setTextColor(Color.parseColor("#000000"));
        se_fbdw.setTextSize(18);
        se_fbdw.setEnabled(false);
        se_sgdw=(EditText)findViewById(R.id.se_sgdw);
        se_sgdw.setText(zgtz.getSgdw());
        se_sgdw.setTextColor(Color.parseColor("#000000"));
        se_sgdw.setTextSize(18);
        se_sgdw.setEnabled(false);
        se_dgdw=(EditText)findViewById(R.id.se_jgdw);
        se_dgdw.setText(zgtz.getDgdw());
        se_dgdw.setTextColor(Color.parseColor("#000000"));
        se_dgdw.setTextSize(18);
        se_dgdw.setEnabled(false);
        se_zmr=(EditText)findViewById(R.id.se_zhi);
        se_zmr.setText(zgtz.getZmr());
        se_zmr.setTextColor(Color.parseColor("#000000"));
        se_zmr.setTextSize(18);
        se_zmr.setEnabled(false);
        se_fssj= (Button) findViewById(R.id.se_time);
        se_fssj.setText(zgtz.getFssj());
        se_fssj.setTextColor(Color.parseColor("#000000"));
        se_fssj.setTextSize(18);
        se_fssj.setEnabled(false);
        se_problem=(EditText)findViewById(R.id.se_problem);
        se_problem.setText(zgtz.getProblem());
        se_problem.setTextColor(Color.parseColor("#000000"));
        se_problem.setTextSize(18);
        se_problem.setEnabled(false);
        se_other=(EditText)findViewById(R.id.se_other);
        se_other.setText(zgtz.getOther());
        se_other.setTextColor(Color.parseColor("#000000"));
        se_other.setTextSize(18);
        se_other.setEnabled(false);
        se_qsyj=(EditText)findViewById(R.id.se_qsyj);
        se_qsyj.setText(zgtz.getQsyj());
        se_qsyj.setTextColor(Color.parseColor("#000000"));
        se_qsyj.setTextSize(18);
        se_qsyj.setEnabled(false);
        se_fcyj=(EditText)findViewById(R.id.se_fcyj);
        se_fcyj.setText(zgtz.getFcyj());
        se_fcyj.setTextColor(Color.parseColor("#000000"));
        se_fcyj.setTextSize(18);
        se_fcyj.setEnabled(false);
        se_cs=(EditText)findViewById(R.id.se_cs);
        se_cs.setText(zgtz.getCs());
        se_cs.setTextColor(Color.parseColor("#000000"));
        se_cs.setTextSize(18);
        se_cs.setEnabled(false);
        se_cljzsj=(Button)findViewById(R.id.se_cljzsj);
        se_cljzsj.setText(zgtz.getCljzsj());
        se_cljzsj.setTextColor(Color.parseColor("#000000"));
        se_cljzsj.setTextSize(18);
        se_cljzsj.setEnabled(false);
        se_qfrq=(Button)findViewById(R.id.se_qfrq);
        se_qfrq.setText(zgtz.getQfrq());
        se_qfrq.setTextColor(Color.parseColor("#000000"));
        se_qfrq.setTextSize(18);
        se_qfrq.setEnabled(false);
        se_qsrq=(Button)findViewById(R.id.se_qsrq);
        se_qsrq.setText(zgtz.getQsrq());
        se_qsrq.setTextColor(Color.parseColor("#000000"));
        se_qsrq.setTextSize(18);
        se_qsrq.setEnabled(false);
        se_fcrq=(Button)findViewById(R.id.se_fcrq);
        se_fcrq.setText(zgtz.getFcrq());
        se_fcrq.setTextColor(Color.parseColor("#000000"));
        se_fcrq.setTextSize(18);
        se_fcrq.setEnabled(false);
        se_qsr= (ImageView) findViewById(R.id.se_qsr);
        se_fcr= (ImageView) findViewById(R.id.se_fcr);
        se_qfr= (ImageView) findViewById(R.id.se_qfr);
        Glide.with(this).load((Constants.IMGURL+zgtz.getQfr()).replace("\\","/")).into(se_qsr);
        Glide.with(this).load((Constants.IMGURL+zgtz.getQsr()).replace("\\","/")).into(se_qfr);
        Glide.with(this).load((Constants.IMGURL+zgtz.getFcr()).replace("\\","/")).into(se_fcr);
    }
    private void initSeavd(){
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        se_gcmc=(EditText)findViewById(R.id.se_name);
        se_gcmc.setText(zgtz.getGcmc());
        se_gcmc.setTextColor(Color.parseColor("#000000"));
        se_gcmc.setTextSize(18);
        se_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = se_gcmc.getText().toString();
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
        se_gcdz=(EditText)findViewById(R.id.se_add);
        se_gcdz.setText(zgtz.getGcdz());
        se_gcdz.setTextColor(Color.parseColor("#000000"));
        se_gcdz.setTextSize(18);
        se_fbdw=(EditText)findViewById(R.id.se_fbdw);
        se_fbdw.setText(zgtz.getFbdw());
        se_fbdw.setTextColor(Color.parseColor("#000000"));
        se_fbdw.setTextSize(18);
        se_sgdw=(EditText)findViewById(R.id.se_sgdw);
        se_sgdw.setText(zgtz.getSgdw());
        se_sgdw.setTextColor(Color.parseColor("#000000"));
        se_sgdw.setTextSize(18);
        se_dgdw=(EditText)findViewById(R.id.se_jgdw);
        se_dgdw.setText(zgtz.getDgdw());
        se_dgdw.setTextColor(Color.parseColor("#000000"));
        se_dgdw.setTextSize(18);
        se_zmr=(EditText)findViewById(R.id.se_zhi);
        se_zmr.setText(zgtz.getZmr());
        se_zmr.setTextColor(Color.parseColor("#000000"));
        se_zmr.setTextSize(18);
        se_fssj= (Button) findViewById(R.id.se_time);
        se_fssj.setText(zgtz.getFssj());
        se_fssj.setTextColor(Color.parseColor("#000000"));
        se_fssj.setTextSize(18);
        se_problem=(EditText)findViewById(R.id.se_problem);
        se_problem.setText(zgtz.getProblem());
        se_problem.setTextColor(Color.parseColor("#000000"));
        se_problem.setTextSize(18);
        se_other=(EditText)findViewById(R.id.se_other);
        se_other.setText(zgtz.getOther());
        se_other.setTextColor(Color.parseColor("#000000"));
        se_other.setTextSize(18);
        se_qsyj=(EditText)findViewById(R.id.se_qsyj);
        se_qsyj.setText(zgtz.getQsyj());
        se_qsyj.setTextColor(Color.parseColor("#000000"));
        se_qsyj.setTextSize(18);
        se_fcyj=(EditText)findViewById(R.id.se_fcyj);
        se_fcyj.setText(zgtz.getFcyj());
        se_fcyj.setTextColor(Color.parseColor("#000000"));
        se_fcyj.setTextSize(18);
        se_cs=(EditText)findViewById(R.id.se_cs);
        se_cs.setText(zgtz.getCs());
        se_cs.setTextColor(Color.parseColor("#000000"));
        se_cs.setTextSize(18);
        se_cljzsj=(Button)findViewById(R.id.se_cljzsj);
        se_cljzsj.setText(zgtz.getCljzsj());
        se_cljzsj.setTextColor(Color.parseColor("#000000"));
        se_cljzsj.setTextSize(18);
        se_qfrq=(Button)findViewById(R.id.se_qfrq);
        se_qfrq.setText(zgtz.getQfrq());
        se_qfrq.setTextColor(Color.parseColor("#000000"));
        se_qfrq.setTextSize(18);
        se_qsrq=(Button)findViewById(R.id.se_qsrq);
        se_qsrq.setText(zgtz.getQsrq());
        se_qsrq.setTextColor(Color.parseColor("#000000"));
        se_qsrq.setTextSize(18);
        se_fcrq=(Button)findViewById(R.id.se_fcrq);
        se_fcrq.setText(zgtz.getFcrq());
        se_fcrq.setTextColor(Color.parseColor("#000000"));
        se_fcrq.setTextSize(18);
        se_cljzsj.setOnClickListener(this);
        se_qfrq.setOnClickListener(this);
        se_qsrq.setOnClickListener(this);
        se_fcrq.setOnClickListener(this);
        se_qsr= (ImageView) findViewById(R.id.se_qsr);
        se_fcr= (ImageView) findViewById(R.id.se_fcr);
        se_qfr= (ImageView) findViewById(R.id.se_qfr);
        se_qfr.setOnClickListener(this);
        se_fcr.setOnClickListener(this);
        se_qsr.setOnClickListener(this);
        GetImageView getImageView = new GetImageView();
        Glide
                .with(this)
                .load((Constants.IMGURL+zgtz.getQfr()).replace("\\","/"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        se_qfr.setImageBitmap(resource);
                        GetImageView.saveImage(resource,"qfr.png");
                    }
                });
        Glide.with(this).load((Constants.IMGURL+zgtz.getQsr()).replace("\\","/"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        se_qsr.setImageBitmap(resource);
                        GetImageView.saveImage(resource,"qsr.png");
                    }
                });
        Glide.with(this).load((Constants.IMGURL+zgtz.getFcr()).replace("\\","/"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        se_fcr.setImageBitmap(resource);
                        GetImageView.saveImage(resource,"fcr.png");
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
                se_gcmc.setText(enterprisesLists.get(position).getName());
                se_fbdw.setText(enterprisesLists.get(position).getName());
                popupWindow.dismiss();
            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(se_gcmc);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 101) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"qfr.png", options);
            se_qfr.setImageBitmap(bm);
        }else if (resultCode==102){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"qsr.png", options);
            se_qsr.setImageBitmap(bm);
        }else if (resultCode==103){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"fcr.png", options);
            se_fcr.setImageBitmap(bm);
        }
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
