package com.example.ruianapp.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.Common;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.Gcfk;
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

public class CommonPracticeActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView co_fh,co_tj;
    private TextView co_titlename;
    private EditText copr_name,copr_fr,copr_qyadd,copr_phone,copr_fwjg,copr_phone1,copr_jczj,copr_number,copr_yj;
    private String qymc,frdb,qydz,qylxdh,fwjg,fwjglxdh,cyjcry,zjjcyj;
    private ImageView copr_img;
    private Button co_qm,copr_date;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Enterprises enterprises;
    private Date jcrq,addTime;
    private int taskId,jcry;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_practice);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x555);
            }
        }
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        initView();
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        co_fh=(ImageView)findViewById(R.id.co_fh);
        co_tj=(ImageView)findViewById(R.id.co_tj);
        co_fh.setOnClickListener(this);
        co_tj.setOnClickListener(this);
        co_qm= (Button) findViewById(R.id.copr_qm);
        co_qm.setOnClickListener(this);
        copr_img= (ImageView) findViewById(R.id.copr_img);
        co_titlename=(TextView)findViewById(R.id.co_titlename);
        enterprises = (Enterprises) getIntent().getSerializableExtra("enterprises");;
        co_titlename.setText(enterprises.getName());
        copr_date=(Button)findViewById(R.id.copr_date);
        copr_date.setOnClickListener(this);
        copr_name=(EditText)findViewById(R.id.copr_name);
        copr_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = copr_name.getText().toString();
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
        copr_fr=(EditText)findViewById(R.id.copr_fr);
        copr_qyadd=(EditText)findViewById(R.id.copr_qyadd);
        copr_phone=(EditText)findViewById(R.id.copr_phone);
        copr_fwjg=(EditText)findViewById(R.id.copr_fwjg);
        copr_phone1=(EditText)findViewById(R.id.copr_phone1);
        copr_jczj=(EditText)findViewById(R.id.copr_jczj);
        copr_jczj.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        copr_number=(EditText)findViewById(R.id.copr_number);
        copr_yj=(EditText)findViewById(R.id.copr_yj);
    }
    private void initData(){
        taskId=enterprises.getId();
        qymc = copr_name.getText().toString();
        frdb=copr_fr.getText().toString();
        qydz=copr_qyadd.getText().toString();
        qylxdh=copr_phone.getText().toString();
        fwjg=copr_fwjg.getText().toString();
        fwjglxdh=copr_phone1.getText().toString();
        jcry= Integer.parseInt(copr_jczj.getText().toString());
        jcrq=stringToDate(copr_date.getText().toString(),"yy-mm-dd");
        cyjcry=copr_number.getText().toString();
        zjjcyj=copr_yj.getText().toString();
        addTime=new Date();
    }
    private void okHttp(){
        if (qymc.length()==0 || frdb.length()==0 || qydz.length()==0 || qylxdh.length()==0 || fwjg.length()==0 || fwjglxdh.length()==0  ||
                cyjcry.length()==0 || zjjcyj.length()==0){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();

        }else {
            Gson gson = new Gson();
            Common common = new Common(taskId,qymc,frdb,qydz,qylxdh,fwjg,fwjglxdh,jcry,jcrq,cyjcry,zjjcyj,addTime);
            final String jsonText = gson.toJson(common);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UtlisOkhttp.sendUserJsonOkHttpRequest(jsonText, Constants.CGJC_URL,new okhttp3.Callback(){
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("123456"+response);
            }
        });
    }
    private void okImgHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendImageOkHttpRequest(enterprises.getName()+".png", Constants.CGJCIMG_URL,new okhttp3.Callback(){
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.co_fh:
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
            case R.id.co_tj:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("是否提交，提交后无法修改" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (copr_img.getDrawable() == null){
                                    Toast.makeText(CommonPracticeActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                    return;
                                }else {
                                    if (copr_date.getText().length()==0 || copr_jczj.getText().toString().length()==0){
                                        Toast.makeText(CommonPracticeActivity.this, "日期与检查人数不能为空", Toast.LENGTH_SHORT).show();
                                    }else {
                                        initData();
                                        okHttp();
                                        okImgHttp();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.copr_qm:
                Intent intent = new Intent(CommonPracticeActivity.this, SignatureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.copr_date:
                DatePickerDialog.OnDateSetListener listener1= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        copr_date.setText(getString(R.string.picked_date_format,year,month+1, dayOfMonth));
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(CommonPracticeActivity.this, 0,listener1,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 101) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path, options);
            copr_img.setImageBitmap(bm);
//            Glide.with(this).load(path + ".sign").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "您拒绝了读写存储权限！", Toast.LENGTH_LONG).show();
        }
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
                copr_name.setText(enterprisesLists.get(position).getName());
                copr_fr.setText(enterprisesLists.get(position).getLegal());
                copr_qyadd.setText(enterprisesLists.get(position).getAddress());
                copr_phone.setText(enterprisesLists.get(position).getPhone());
                popupWindow.dismiss();
            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(copr_name);
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
}
