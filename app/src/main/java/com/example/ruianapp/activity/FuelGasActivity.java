package com.example.ruianapp.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.adapter.FuelGasAdapter;
import com.example.ruianapp.bean.Common;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.FuelGas;
import com.example.ruianapp.bean.Restaurant;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FuelGasActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView fu_fh,fu_tj;
    private TextView fu_titlename;
    private RecyclerView recyclerView;
    private FuelGasAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<FuelGas>fuelGases;
    private Enterprises enterprises;
    private int taskId,gpsl50,gpsll5,gpsll0,gpsl5,addPerson;
    private String dw,dwdz,fzr,lxdh,ssjd,rqlb,rqyt,cjcyrs,sfqdgsyyzz,sfqdgsyyzzbz,sfqdcyxkz,sfqdcyxkzbz,dxshbdxs,dxshbdxsbz,
            sfdy80,sfdy80bz,czrqsjgf,czrqsjgfbz,azkrqtldbjzz,azkrqtldbjzzbz,sfpbgfmhqdxfqc,sfpbgfmhqdxfqcbz,fsyfhaqyq,fsyfhaqyqbz
            ,sfsygqhbfgp,sfsygqhbfgpbz,jsbsczbs,jsbsczbsbz,rqgyqysfqdrqjyxkz,rqgyqysfqdrqjyxkzbz,yhsyqsflzkfq,yhsyqsflzkfqbz,gqht
            ,gqhtbz,sflcgqpz,sflcgqpzbz,gptxmbh,gptxmbhbz,cpzzz,cpzzzbz,dczyfjn,dczyfjnbz,dyrj,dyrjbz,syztswdm,syztswdmbz,cdkzdfhq
            ,cdkzdfhqbz,pbgfmhq,pbgfmhqbz,xysmddljz,xysmddljzbz,bdy22,bdy22bz,krqtsdbjzz,krqtsdbjzzbz,lqgdljqtdxjzw,lqgdljqtdxjzwbz
            ,aqjsbz,aqjsbzbz,dqktszzsw,dqktszzswbz,zpzy,zpzybz,fhqgk,fhqgkbz,symh,symhbz,qtrl,qtrlbz,cg15n,cg15nbz,mfqcq3n,mfqcq3nbz
            ,jyqsbgd,jyqsbgdbz,ykgjg,ykgjgbz,myjk,myjkbz,xjrg2nmygh,xjrg2nmyghbz,nhfsdwt,nhfsdwtbz,cyqbchhm,cyqbchhmbz,qtczwt,sign;
    private Date addTime;
    private EditText fu_ga_name,fu_ga_fzr,fu_ga_dz,fu_ga_phone,fu_ga_ssjd,fu_ga_rqyt,fu_ga_50,fu_ga_15,fu_ga_10,fu_ga_5,fu_ga_cycyrs,fu_ga_qtczwt;
    private RadioButton gd,pz,lz;
    private Button fu_ga_jctime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Button fu_ga_qm;
    private ImageView fu_ga_img;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_gas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x555);
            }
        }
        initView();
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
        fu_fh=(ImageView)findViewById(R.id.fu_fh);
        fu_tj=(ImageView)findViewById(R.id.fu_tj);
        fu_fh.setOnClickListener(this);
        fu_tj.setOnClickListener(this);
        fu_ga_qm= (Button) findViewById(R.id.fuel_qm);
        fu_ga_img= (ImageView) findViewById(R.id.fuel_img);

        fu_ga_qm.setOnClickListener(this);
        fu_titlename=(TextView)findViewById(R.id.fu_titlename);
        enterprises = (Enterprises) getIntent().getSerializableExtra("enterprises");
        fu_titlename.setText(enterprises.getName());
        fuelGases=new ArrayList<>();
        recyclerView= (RecyclerView) findViewById(R.id.fuel_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new FuelGasAdapter(initData(),this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        fu_ga_name=(EditText)findViewById(R.id.fu_ga_name);
        fu_ga_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = fu_ga_name.getText().toString();
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
        fu_ga_dz= (EditText) findViewById(R.id.fu_ga_dd);
        fu_ga_fzr=(EditText)findViewById(R.id.fu_ga_fzr);
        fu_ga_phone=(EditText)findViewById(R.id.fu_ga_phone);
        fu_ga_ssjd=(EditText)findViewById(R.id.fu_ga_ssjd);
        fu_ga_rqyt=(EditText)findViewById(R.id.fu_ga_rqyt);
        gd= (RadioButton) findViewById(R.id.fuel_gd);
        pz= (RadioButton) findViewById(R.id.fuel_pz);
        lz= (RadioButton) findViewById(R.id.fuel_lz);
        gd.setChecked(true);
        fu_ga_50=(EditText)findViewById(R.id.fu_ga_50);
        fu_ga_50.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        fu_ga_15=(EditText)findViewById(R.id.fu_ga_15);
        fu_ga_15.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        fu_ga_10=(EditText)findViewById(R.id.fu_ga_10);
        fu_ga_10.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        fu_ga_5=(EditText)findViewById(R.id.fu_ga_5);
        fu_ga_5.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        fu_ga_cycyrs=(EditText)findViewById(R.id.fu_ga_cycyrs);
        fu_ga_qtczwt= (EditText) findViewById(R.id.fu_ga_otherq);
        fu_ga_jctime= (Button) findViewById(R.id.fu_ga_jctime);
        fu_ga_jctime.setOnClickListener(this);

    }
    private void initDatas(){
        taskId=enterprises.getId();
        dw=fu_ga_name.getText().toString();
        dwdz=fu_ga_dz.getText().toString();
        fzr=fu_ga_fzr.getText().toString();
        lxdh=fu_ga_phone.getText().toString();
        ssjd=fu_ga_ssjd.getText().toString();
        if (gd.isChecked()){
            rqlb=gd.getText().toString();
        }
        if (pz.isChecked()){
            rqlb=pz.getText().toString();
        }
        if (lz.isChecked()){
            rqlb=lz.getText().toString();
        }
        rqyt=fu_ga_rqyt.getText().toString();
        gpsl50=Integer.parseInt(fu_ga_50.getText().toString());
        gpsll5=Integer.parseInt(fu_ga_15.getText().toString());
        gpsll0=Integer.parseInt(fu_ga_10.getText().toString());
        gpsl5=Integer.parseInt(fu_ga_5.getText().toString());
        cjcyrs=fu_ga_cycyrs.getText().toString();
        sfqdgsyyzz=fuelGases.get(0).getRadio();
        sfqdgsyyzzbz=fuelGases.get(0).getBz();
        sfqdcyxkz=fuelGases.get(1).getRadio();
        sfqdcyxkzbz=fuelGases.get(1).getBz();
        dxshbdxs=fuelGases.get(2).getRadio();
        dxshbdxsbz=fuelGases.get(2).getBz();
        sfdy80=fuelGases.get(3).getRadio();
        sfdy80bz=fuelGases.get(3).getBz();
        czrqsjgf=fuelGases.get(4).getRadio();
        czrqsjgfbz=fuelGases.get(4).getBz();
        azkrqtldbjzz=fuelGases.get(5).getRadio();
        azkrqtldbjzzbz=fuelGases.get(5).getBz();
        sfpbgfmhqdxfqc=fuelGases.get(6).getRadio();
        sfpbgfmhqdxfqcbz=fuelGases.get(6).getBz();
        fsyfhaqyq=fuelGases.get(7).getRadio();
        fsyfhaqyqbz=fuelGases.get(7).getBz();
        sfsygqhbfgp=fuelGases.get(8).getRadio();
        sfsygqhbfgpbz=fuelGases.get(8).getBz();
        jsbsczbs=fuelGases.get(9).getRadio();
        jsbsczbsbz=fuelGases.get(9).getBz();
        rqgyqysfqdrqjyxkz=fuelGases.get(10).getRadio();
        rqgyqysfqdrqjyxkzbz=fuelGases.get(10).getBz();
        yhsyqsflzkfq=fuelGases.get(11).getRadio();
        yhsyqsflzkfqbz=fuelGases.get(11).getBz();
        gqht=fuelGases.get(12).getRadio();
        gqhtbz=fuelGases.get(12).getBz();
        sflcgqpz=fuelGases.get(13).getRadio();
        sflcgqpzbz=fuelGases.get(13).getBz();
        gptxmbh=fuelGases.get(14).getRadio();
        gptxmbhbz=fuelGases.get(14).getBz();
        cpzzz=fuelGases.get(15).getRadio();
        cpzzzbz=fuelGases.get(15).getBz();
        dczyfjn=fuelGases.get(16).getRadio();
        dczyfjnbz=fuelGases.get(16).getBz();
        dyrj=fuelGases.get(17).getRadio();
        dyrjbz=fuelGases.get(17).getBz();
        syztswdm=fuelGases.get(18).getRadio();
        syztswdmbz=fuelGases.get(18).getBz();
        cdkzdfhq=fuelGases.get(19).getRadio();
        cdkzdfhqbz=fuelGases.get(19).getBz();
        pbgfmhq=fuelGases.get(20).getRadio();
        pbgfmhqbz=fuelGases.get(20).getBz();
        xysmddljz=fuelGases.get(21).getRadio();
        xysmddljzbz=fuelGases.get(21).getBz();
        bdy22=fuelGases.get(22).getRadio();
        bdy22bz=fuelGases.get(22).getBz();
        krqtsdbjzz=fuelGases.get(23).getRadio();
        krqtsdbjzzbz=fuelGases.get(23).getBz();
        lqgdljqtdxjzw=fuelGases.get(24).getRadio();
        lqgdljqtdxjzwbz=fuelGases.get(24).getBz();
        aqjsbz=fuelGases.get(25).getRadio();
        aqjsbzbz=fuelGases.get(25).getBz();
        dqktszzsw=fuelGases.get(26).getRadio();
        dqktszzswbz=fuelGases.get(26).getBz();
        zpzy=fuelGases.get(27).getRadio();
        zpzybz=fuelGases.get(27).getBz();
        fhqgk=fuelGases.get(28).getRadio();
        fhqgkbz=fuelGases.get(28).getBz();
        symh=fuelGases.get(29).getRadio();
        symhbz=fuelGases.get(29).getBz();
        qtrl=fuelGases.get(30).getRadio();
        qtrlbz=fuelGases.get(30).getBz();
        cg15n=fuelGases.get(31).getRadio();
        cg15nbz=fuelGases.get(31).getBz();
        mfqcq3n=fuelGases.get(32).getRadio();
        mfqcq3nbz=fuelGases.get(32).getBz();
        jyqsbgd=fuelGases.get(33).getRadio();
        jyqsbgdbz=fuelGases.get(33).getBz();
        ykgjg=fuelGases.get(34).getRadio();
        ykgjgbz=fuelGases.get(34).getBz();
        myjk=fuelGases.get(35).getRadio();
        myjkbz=fuelGases.get(35).getBz();
        xjrg2nmygh=fuelGases.get(36).getRadio();
        xjrg2nmyghbz=fuelGases.get(36).getBz();
        nhfsdwt=fuelGases.get(37).getRadio();
        nhfsdwtbz=fuelGases.get(37).getBz();
        cyqbchhm=fuelGases.get(38).getRadio();
        cyqbchhmbz=fuelGases.get(38).getBz();
        qtczwt=fu_ga_qtczwt.getText().toString();
        addTime=stringToDate(fu_ga_jctime.getText().toString(),"yy-mm-dd");
        sign="";
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        addPerson=preferences.getInt("id",0);
    }
    private void okHttp(){
        if (dw.length()==0 || dwdz.length()==0 || fzr.length()==0 || lxdh.length()==0 || ssjd.length()==0 || rqlb.length()==0  ||
                rqyt.length()==0 || cjcyrs.length()==0 || qtczwt.length()==0){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();

        }else {
            Gson gson = new Gson();
            Restaurant restaurant = new Restaurant(taskId,dw,dwdz,fzr,lxdh,ssjd,rqlb,rqyt,gpsl50,gpsll5,gpsll0,gpsl5,cjcyrs,sfqdgsyyzz,sfqdgsyyzzbz,sfqdcyxkz,sfqdcyxkzbz,dxshbdxs,dxshbdxsbz,
                    sfdy80,sfdy80bz,czrqsjgf,czrqsjgfbz,azkrqtldbjzz,azkrqtldbjzzbz,sfpbgfmhqdxfqc,sfpbgfmhqdxfqcbz,fsyfhaqyq,fsyfhaqyqbz
                    ,sfsygqhbfgp,sfsygqhbfgpbz,jsbsczbs,jsbsczbsbz,rqgyqysfqdrqjyxkz,rqgyqysfqdrqjyxkzbz,yhsyqsflzkfq,yhsyqsflzkfqbz,gqht
                    ,gqhtbz,sflcgqpz,sflcgqpzbz,gptxmbh,gptxmbhbz,cpzzz,cpzzzbz,dczyfjn,dczyfjnbz,dyrj,dyrjbz,syztswdm,syztswdmbz,cdkzdfhq
                    ,cdkzdfhqbz,pbgfmhq,pbgfmhqbz,xysmddljz,xysmddljzbz,bdy22,bdy22bz,krqtsdbjzz,krqtsdbjzzbz,lqgdljqtdxjzw,lqgdljqtdxjzwbz
                    ,aqjsbz,aqjsbzbz,dqktszzsw,dqktszzswbz,zpzy,zpzybz,fhqgk,fhqgkbz,symh,symhbz,qtrl,qtrlbz,cg15n,cg15nbz,mfqcq3n,mfqcq3nbz
                    ,jyqsbgd,jyqsbgdbz,ykgjg,ykgjgbz,myjk,myjkbz,xjrg2nmygh,xjrg2nmyghbz,nhfsdwt,nhfsdwtbz,cyqbchhm,cyqbchhmbz,qtczwt,addTime,sign,addPerson);
            final String jsonText = gson.toJson(restaurant);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UtlisOkhttp.sendUserJsonOkHttpRequest(jsonText, Constants.CGRQ_URL,new okhttp3.Callback(){
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
    private void okImgHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendImageOkHttpRequest(enterprises.getName()+".png", Constants.CGRQIMG_URL,new okhttp3.Callback(){
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
                System.out.println("123456"+response);
            }
        });
    }


    private List<FuelGas> initData(){
        Resources res =getResources();
        String[] name=res.getStringArray(R.array.fuelgas) ;
        for (int i =0 ;i<39;i++){
            FuelGas fuelGas = new FuelGas(name[i],"1","");
            fuelGases.add(fuelGas);
        }
        return fuelGases;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fu_fh:
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
            case R.id.fu_tj:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("是否提交，提交后无法修改" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (fu_ga_img.getDrawable() == null){
                                    Toast.makeText(FuelGasActivity.this, "未签名", Toast.LENGTH_SHORT).show();
                                }else {
                                    if (fu_ga_jctime.getText().length()==0 || fu_ga_5.getText().toString().length()==0 || fu_ga_15.getText().toString().length()==0
                                            || fu_ga_10.getText().toString().length()==0 || fu_ga_50.getText().toString().length()==0){
                                        Toast.makeText(FuelGasActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                                    }else {
                                        initDatas();
                                        okHttp();
                                        okImgHttp();
                                    }

                                }

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.fu_ga_jctime:
                DatePickerDialog.OnDateSetListener listener1= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fu_ga_jctime.setText(getString(R.string.picked_date_format,year,month+1, dayOfMonth));
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(FuelGasActivity.this, 0,listener1,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();
                break;
            case R.id.fuel_qm:
                Intent intent = new Intent(FuelGasActivity.this, SignatureActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 101) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Constants.path, options);
            fu_ga_img.setImageBitmap(bm);
//            Glide.with(this).load(path + ".sign").skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "您拒绝了读写存储权限！", Toast.LENGTH_LONG).show();
        }
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
                fu_ga_name.setText(enterprisesLists.get(position).getName());
                fu_ga_dz.setText(enterprisesLists.get(position).getAddress());
                fu_ga_fzr.setText(enterprisesLists.get(position).getLegal());
                fu_ga_phone.setText(enterprisesLists.get(position).getPhone());
                popupWindow.dismiss();
            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(fu_ga_name);
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
