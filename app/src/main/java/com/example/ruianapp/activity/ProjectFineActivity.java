package com.example.ruianapp.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.Gcfk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

public class ProjectFineActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
    private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
    private static final double MAX_VALUE = 9999999999999.99D;
    private ImageView projectfine_fh;
    private TextView projectfine_titlename;
    private LinearLayout projectfine_tj,projectfine_bc;
    private EditText prfi_gcmc,prfi_gcdz,prfi_fbdw,prfi_sgdw,prfi_dgdw,prfi_zmr,prfi_problem,prfi_xx;
    private Button prfi_fssj,prfi_qfrq,prfi_sjrq,prfi_sjrq2;
    private String gcmc,gcdz,fbdw,sgdw,dgdw,zmr,fssj,problem,dx,qfr,qfdw,sjr,sjr2,update_ids;
    private String qfrq,sjrq,sjrq2,add_time;
    private double xx;
    private int user_id;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Gcfk gcfk;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;
    private ImageView prfi_qfr,prfi_sjr,prfi_sjr2;
    private LocationClient mLocationClient;
    private double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_fine);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLicationListener());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x555);
            }
        }
        gcfk = (Gcfk) getIntent().getSerializableExtra("gcfk");
        if (gcfk !=null){
            if (gcfk.isSaved()){
                initobserve();
            }else {
                initSaved();
                requestLocation();
            }


        }else {
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
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        projectfine_fh=(ImageView)findViewById(R.id.projectfine_fh);
        projectfine_tj= (LinearLayout) findViewById(R.id.projectfine_tj);
        projectfine_bc= (LinearLayout) findViewById(R.id.projectfine_bc);
        projectfine_fh.setOnClickListener(this);
        projectfine_tj.setOnClickListener(this);
        projectfine_bc.setOnClickListener(this);
        projectfine_titlename=(TextView)findViewById(R.id.projectfine_titlename);
        projectfine_titlename.setText("工程罚款通知单");
        prfi_gcmc=(EditText)findViewById(R.id.pr_fi_name);
        prfi_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("123456776543");
                String name = prfi_gcmc.getText().toString();
                System.out.println("123456776543");
                List<EnterprisesList>enterprisesLists = new ArrayList<>();
                Cursor cursor = DataSupport.findBySQL("SELECT * FROM enterpriseslist WHERE NAME LIKE '%"+name+"%' limit 5");
                if (cursor!=null){
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

                }
                initSearch(enterprisesLists);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        prfi_gcdz=(EditText)findViewById(R.id.pr_fi_add);
        prfi_fbdw=(EditText)findViewById(R.id.pr_fi_fbdw);
        prfi_sgdw=(EditText)findViewById(R.id.pr_fi_sgdw);
        prfi_dgdw=(EditText)findViewById(R.id.pr_fi_ggdw);
        prfi_zmr=(EditText)findViewById(R.id.pr_fi_zhi);
        prfi_problem=(EditText)findViewById(R.id.pr_fi_problem);
        prfi_xx=(EditText)findViewById(R.id.pr_fi_xx);
        prfi_xx.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        prfi_qfrq=(Button)findViewById(R.id.pr_fi_qfrq);
        prfi_sjrq=(Button)findViewById(R.id.pr_fi_sjrq);
        prfi_sjrq2=(Button)findViewById(R.id.pr_fi_sjrq2);
        prfi_fssj= (Button) findViewById(R.id.pr_fi_time);
        prfi_fssj.setOnClickListener(this);
        prfi_qfrq.setOnClickListener(this);
        prfi_sjrq.setOnClickListener(this);
        prfi_sjrq2.setOnClickListener(this);
        prfi_qfr= (ImageView) findViewById(R.id.pr_fi_qfr);
        prfi_qfr.setOnClickListener(this);
        prfi_sjr = (ImageView) findViewById(R.id.pr_fi_sjr);
        prfi_sjr2= (ImageView) findViewById(R.id.pr_fi_sjr2);
        prfi_sjr.setOnClickListener(this);
        prfi_sjr2.setOnClickListener(this);
    }
    private void initData(){
        gcmc=prfi_gcmc.getText().toString();
        gcdz=prfi_gcdz.getText().toString();
        fbdw=prfi_fbdw.getText().toString();
        sgdw=prfi_sgdw.getText().toString();
        dgdw=prfi_dgdw.getText().toString();
        zmr=prfi_zmr.getText().toString();
        fssj=prfi_fssj.getText().toString();
        problem=prfi_problem.getText().toString();
        xx=Double.parseDouble(prfi_xx.getText().toString());
        dx=change(xx);
        qfr="江苏瑞安安全科技发展有限公司";
        qfdw="江苏瑞安安全科技发展有限公司";
        qfrq=prfi_qfrq.getText().toString();
        sjrq=prfi_sjrq.getText().toString();
        sjrq2=prfi_sjrq2.getText().toString();
        add_time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        user_id=preferences.getInt("id",0);
        update_ids=preferences.getInt("id",0)+"";
    }
    /*
    *
    * 下面进行网络请求
    *
    * */
    private void okHttp(boolean saved){
        if (gcmc.length()==0 || gcdz.length()==0 || fbdw.length()==0 || sgdw.length()==0 || dgdw.length()==0 || zmr.length()==0 || fssj.length()==0 ||
                fssj.length()==0 || problem.length()==0 || dx.length()==0){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
        }else {
            Gson gson = new Gson();
            Gcfk gcfk = new Gcfk(0,gcmc,"",gcdz,fbdw,sgdw,dgdw,zmr,fssj,problem,dx,xx,qfr,qfdw,qfrq,"dd",sjrq,"dd",sjrq2,add_time,user_id,update_ids,latitude+"",longitude+"",saved);
            final String json=gson.toJson(gcfk);
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("userId", user_id);
//            map.put("obj", gcfk);
//            Gson gson2 = new GsonBuilder().enableComplexMapKeySerialization().create();
//            final String jsonText = gson2.toJson(map);
//            System.out.println("12345678941233"+jsonText);
            System.out.println("asdfghjklddd"+json);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UtlisOkhttp.sendUserJsonOkHttpRequest(json, Constants.GCFK_URL,new okhttp3.Callback(){
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
    private void showResponse(final String response) {
        //在子线程中更新UI
        System.out.println("asdfghjkl1"+response);
        if (JsonGet.loginMessage(response).getMsg().equals("操作成功")){
            finish();
        }
    }

    /*
    * 图片请求
    * */
    private void okhttpimage(){
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
            case R.id.projectfine_fh:
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
            case R.id.projectfine_tj:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("是否提交，提交后无法修改" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (prfi_qfr.getDrawable()==null){
                                    Toast.makeText(ProjectFineActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                }else {
                                    if ((xx+"").length()==0 || prfi_qfrq.getText().length()==0 ||
                                            prfi_sjrq.getText().length()==0 || prfi_sjrq2.getText().length()==0){
                                        Toast.makeText(ProjectFineActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                    }else {
//                                        initData();
                                        okhttpimage();
//                                        okHttp(true);
                                    }
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.projectfine_bc:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("是否保存，保存后可以修改" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if ((xx+"").length()==0 || prfi_qfrq.getText().length()==0 ||
                                        prfi_sjrq.getText().length()==0 || prfi_sjrq2.getText().length()==0){
                                    Toast.makeText(ProjectFineActivity.this, "日期和罚款金额不能为空", Toast.LENGTH_SHORT).show();
                                }else {
                                    initData();
                                    okHttp(false);
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.pr_fi_qfrq:
                DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(ProjectFineActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);
                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                prfi_qfrq.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(ProjectFineActivity.this, 0,listener1,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();
                break;
            case R.id.pr_fi_sjrq:
                DatePickerDialog.OnDateSetListener listener2=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(ProjectFineActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                prfi_sjrq.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog2=new DatePickerDialog(ProjectFineActivity.this, 0,listener2,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog2.show();
                break;
            case R.id.pr_fi_sjrq2:
                DatePickerDialog.OnDateSetListener listener3=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(ProjectFineActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                prfi_sjrq2.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog3=new DatePickerDialog(ProjectFineActivity.this, 0,listener3,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog3.show();
                break;
            case R.id.pr_fi_time:
                DatePickerDialog.OnDateSetListener listener4=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        final Calendar startcal = Calendar.getInstance();
                        startcal.set(Calendar.YEAR,year);
                        startcal.set(Calendar.MONTH,monthOfYear);
                        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        TimePickerDialog dialog1 = new TimePickerDialog(ProjectFineActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startcal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                startcal.set(Calendar.MINUTE, minute);

                                String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startcal.getTimeInMillis()));
                                prfi_fssj.setText(date);

                            }
                        },0,0,false);
                        dialog1.show();
                    }
                };
                DatePickerDialog dialog4=new DatePickerDialog(ProjectFineActivity.this, 0,listener4,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog4.show();
                break;
            case R.id.pr_fi_qfr:
                Intent intent = new Intent(ProjectFineActivity.this, SignatureActivity.class);
                intent.putExtra("name","qfr.png");
                intent.putExtra("code",101);
                startActivityForResult(intent, 1);
                break;
            case R.id.pr_fi_sjr:
                Intent intent1 = new Intent(ProjectFineActivity.this, SignatureActivity.class);
                intent1.putExtra("name","sjr.png");
                intent1.putExtra("code",102);
                startActivityForResult(intent1, 1);
                break;
            case R.id.pr_fi_sjr2:
                Intent intent2 = new Intent(ProjectFineActivity.this, SignatureActivity.class);
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
    public static String change(double v) {
        if (v < 0 || v > MAX_VALUE) {
            return "参数非法!";
        }
        long l = Math.round(v * 100);
        if (l == 0) {
            return "零元整";
        }
        String strValue = l + "";
        // i用来控制数
        int i = 0;
        // j用来控制单位
        int j = UNIT.length() - strValue.length();
        String rs = "";
        boolean isZero = false;
        for (; i < strValue.length(); i++, j++) {
            char ch = strValue.charAt(i);
            if (ch == '0') {
                isZero = true;
                if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万'
                        || UNIT.charAt(j) == '元') {
                    rs = rs + UNIT.charAt(j);
                    isZero = false;
                }
            } else {
                if (isZero) {
                    rs = rs + "零";
                    isZero = false;
                }
                rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
            }
        }
//      if (!rs.endsWith("分")) {
//          rs = rs + "整";
//      }
        rs = rs.replaceAll("亿万", "亿");
        return rs;
    }
    private void initobserve(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        projectfine_fh=(ImageView)findViewById(R.id.projectfine_fh);
        projectfine_tj= (LinearLayout) findViewById(R.id.projectfine_tj);
        projectfine_bc= (LinearLayout) findViewById(R.id.projectfine_bc);
        projectfine_fh.setOnClickListener(this);

        projectfine_tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectFineActivity.this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
            }
        });
        projectfine_bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectFineActivity.this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
            }
        });
        projectfine_titlename=(TextView)findViewById(R.id.projectfine_titlename);
        projectfine_titlename.setText(gcfk.getGcmc());
        prfi_gcmc=(EditText)findViewById(R.id.pr_fi_name);
        prfi_gcmc.setText(gcfk.getGcmc());
        prfi_gcmc.setTextColor(Color.parseColor("#000000"));
        prfi_gcmc.setTextSize(18);
        prfi_gcmc.setEnabled(false);
        prfi_gcdz=(EditText)findViewById(R.id.pr_fi_add);
        prfi_gcdz.setText(gcfk.getGcdz());
        prfi_gcdz.setEnabled(false);
        prfi_gcdz.setTextColor(Color.parseColor("#000000"));
        prfi_gcdz.setTextSize(18);
        prfi_fbdw=(EditText)findViewById(R.id.pr_fi_fbdw);
        prfi_fbdw.setText(gcfk.getFbdw());
        prfi_fbdw.setEnabled(false);
        prfi_fbdw.setTextColor(Color.parseColor("#000000"));
        prfi_fbdw.setTextSize(18);
        prfi_sgdw=(EditText)findViewById(R.id.pr_fi_sgdw);
        prfi_sgdw.setText(gcfk.getSgdw());
        prfi_sgdw.setEnabled(false);
        prfi_sgdw.setTextColor(Color.parseColor("#000000"));
        prfi_sgdw.setTextSize(18);
        prfi_dgdw=(EditText)findViewById(R.id.pr_fi_ggdw);
        prfi_dgdw.setText(gcfk.getDgdw());
        prfi_dgdw.setEnabled(false);
        prfi_dgdw.setTextColor(Color.parseColor("#000000"));
        prfi_dgdw.setTextSize(18);
        prfi_zmr=(EditText)findViewById(R.id.pr_fi_zhi);
        prfi_zmr.setText(gcfk.getZmr());
        prfi_zmr.setEnabled(false);
        prfi_zmr.setTextColor(Color.parseColor("#000000"));
        prfi_zmr.setTextSize(18);
        prfi_fssj= (Button) findViewById(R.id.pr_fi_time);
        prfi_fssj.setText(gcfk.getFssj());
        prfi_fssj.setEnabled(false);
        prfi_fssj.setTextColor(Color.parseColor("#000000"));
        prfi_fssj.setTextSize(18);
        prfi_problem=(EditText)findViewById(R.id.pr_fi_problem);
        prfi_problem.setText(gcfk.getProblem());
        prfi_problem.setEnabled(false);
        prfi_problem.setTextColor(Color.parseColor("#000000"));
        prfi_problem.setTextSize(18);
        prfi_xx=(EditText)findViewById(R.id.pr_fi_xx);
        prfi_xx.setText(gcfk.getXx()+"");
        prfi_xx.setEnabled(false);
        prfi_xx.setTextColor(Color.parseColor("#000000"));
        prfi_xx.setTextSize(18);
        prfi_xx.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        prfi_qfrq=(Button)findViewById(R.id.pr_fi_qfrq);
        prfi_qfrq.setText(gcfk.getQfrq());
        prfi_qfrq.setEnabled(false);
        prfi_qfrq.setTextColor(Color.parseColor("#000000"));
        prfi_qfrq.setTextSize(18);
        prfi_sjrq=(Button)findViewById(R.id.pr_fi_sjrq);
        prfi_sjrq.setText(gcfk.getSjrq());
        prfi_sjrq.setEnabled(false);
        prfi_sjrq.setTextColor(Color.parseColor("#000000"));
        prfi_sjrq.setTextSize(18);
        prfi_sjrq2=(Button)findViewById(R.id.pr_fi_sjrq2);
        prfi_sjrq2.setText(gcfk.getSjrq2());
        prfi_sjrq2.setEnabled(false);
        prfi_sjrq2.setTextColor(Color.parseColor("#000000"));
        prfi_sjrq2.setTextSize(18);
    }
    private void initSaved(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        projectfine_fh=(ImageView)findViewById(R.id.projectfine_fh);
        projectfine_tj= (LinearLayout) findViewById(R.id.projectfine_tj);
        projectfine_bc= (LinearLayout) findViewById(R.id.projectfine_bc);
        projectfine_fh.setOnClickListener(this);

        projectfine_tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectFineActivity.this, "提交过后不能修改", Toast.LENGTH_SHORT).show();
            }
        });
        projectfine_bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectFineActivity.this, "不能重复保存", Toast.LENGTH_SHORT).show();
            }
        });
        projectfine_titlename=(TextView)findViewById(R.id.projectfine_titlename);
        projectfine_titlename.setText(gcfk.getGcmc());
        prfi_gcmc=(EditText)findViewById(R.id.pr_fi_name);
        prfi_gcmc.setText(gcfk.getGcmc());
        prfi_gcmc.setTextColor(Color.parseColor("#000000"));
        prfi_gcmc.setTextSize(18);
        prfi_gcmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("123456776543");
                String name = prfi_gcmc.getText().toString();
                System.out.println("123456776543");
                List<EnterprisesList>enterprisesLists = new ArrayList<>();
                Cursor cursor = DataSupport.findBySQL("SELECT * FROM enterpriseslist WHERE NAME LIKE '%"+name+"%' limit 5");
                if (cursor!=null){
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

                }
                initSearch(enterprisesLists);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        prfi_gcdz=(EditText)findViewById(R.id.pr_fi_add);
        prfi_gcdz.setText(gcfk.getGcdz());
        prfi_gcdz.setTextColor(Color.parseColor("#000000"));
        prfi_gcdz.setTextSize(18);
        prfi_fbdw=(EditText)findViewById(R.id.pr_fi_fbdw);
        prfi_fbdw.setText(gcfk.getFbdw());
        prfi_fbdw.setTextColor(Color.parseColor("#000000"));
        prfi_fbdw.setTextSize(18);
        prfi_sgdw=(EditText)findViewById(R.id.pr_fi_sgdw);
        prfi_sgdw.setText(gcfk.getSgdw());
        prfi_sgdw.setTextColor(Color.parseColor("#000000"));
        prfi_sgdw.setTextSize(18);
        prfi_dgdw=(EditText)findViewById(R.id.pr_fi_ggdw);
        prfi_dgdw.setText(gcfk.getDgdw());
        prfi_dgdw.setTextColor(Color.parseColor("#000000"));
        prfi_dgdw.setTextSize(18);
        prfi_zmr=(EditText)findViewById(R.id.pr_fi_zhi);
        prfi_zmr.setText(gcfk.getZmr());
        prfi_zmr.setTextColor(Color.parseColor("#000000"));
        prfi_zmr.setTextSize(18);
        prfi_fssj= (Button) findViewById(R.id.pr_fi_time);
        prfi_fssj.setText(gcfk.getFssj());
        prfi_fssj.setTextColor(Color.parseColor("#000000"));
        prfi_fssj.setTextSize(18);
        prfi_problem=(EditText)findViewById(R.id.pr_fi_problem);
        prfi_problem.setText(gcfk.getProblem());
        prfi_problem.setTextColor(Color.parseColor("#000000"));
        prfi_problem.setTextSize(18);
        prfi_xx=(EditText)findViewById(R.id.pr_fi_xx);
        prfi_xx.setText(gcfk.getXx()+"");
        prfi_xx.setTextColor(Color.parseColor("#000000"));
        prfi_xx.setTextSize(18);
        prfi_xx.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        prfi_qfrq=(Button)findViewById(R.id.pr_fi_qfrq);
        prfi_qfrq.setText(gcfk.getQfrq());
        prfi_qfrq.setTextColor(Color.parseColor("#000000"));
        prfi_qfrq.setTextSize(18);
        prfi_sjrq=(Button)findViewById(R.id.pr_fi_sjrq);
        prfi_sjrq.setText(gcfk.getSjrq());
        prfi_sjrq.setTextColor(Color.parseColor("#000000"));
        prfi_sjrq.setTextSize(18);
        prfi_sjrq2=(Button)findViewById(R.id.pr_fi_sjrq2);
        prfi_sjrq2.setText(gcfk.getSjrq2());
        prfi_sjrq2.setTextColor(Color.parseColor("#000000"));
        prfi_sjrq2.setTextSize(18);
        prfi_fssj.setOnClickListener(this);
        prfi_qfrq.setOnClickListener(this);
        prfi_sjrq.setOnClickListener(this);
        prfi_sjrq2.setOnClickListener(this);
        prfi_qfr= (ImageView) findViewById(R.id.pr_fi_qfr);
        prfi_qfr.setOnClickListener(this);


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
                prfi_gcmc.setText(enterprisesLists.get(position).getName());
                prfi_fbdw.setText(enterprisesLists.get(position).getName());
                popupWindow.setFocusable(true);
                popupWindow.dismiss();
            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(prfi_gcmc);
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
    /*
    *
    * 以下是处理签名的方法
    *
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 101) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"qfr.png", options);
            prfi_qfr.setImageBitmap(bm);
//            Glide.with(this).load(path + ".sign").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        }else if (resultCode==102){
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"sjr.png", options);
            prfi_sjr.setImageBitmap(bm);
        }else if (resultCode==103){
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path+"sjr2.png", options);
            prfi_sjr2.setImageBitmap(bm);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "您拒绝了读写存储权限！", Toast.LENGTH_LONG).show();
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
