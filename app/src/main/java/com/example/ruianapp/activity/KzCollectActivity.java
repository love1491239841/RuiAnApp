package com.example.ruianapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.adapter.KzCollectPagerAdapter;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.ExtTableFour;
import com.example.ruianapp.bean.ExtTableOne;
import com.example.ruianapp.bean.ExtTableThree;
import com.example.ruianapp.bean.ExtTableTwo;
import com.example.ruianapp.fragment.FourCollectFragment;
import com.example.ruianapp.fragment.OneCollectFragment;
import com.example.ruianapp.fragment.ThreeCollectFragment;
import com.example.ruianapp.fragment.TwoCollectFragment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KzCollectActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView kz_fh,kz_tj;
    private TextView kz_name;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private KzCollectPagerAdapter fragmentPagerAdapter;
    private static final String[] CONTENT = new String[] { "附件一","附件二","附件三","附件四"};
    private TabLayout tabLayout;
    private List<ExtTableThree>extTableThrees;
    private ExtTableOne extTableOne;
    private Enterprises enterprises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kz_collect);
        initFragment();
        initView();
        initThrees();
        initOne();
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        kz_fh=(ImageView)findViewById(R.id.kz_fh);
        kz_fh.setOnClickListener(this);
        kz_tj=(ImageView)findViewById(R.id.kz_tj);
        kz_tj.setOnClickListener(this);
        kz_name=(TextView)findViewById(R.id.kz_titlename);
        enterprises = enterprises = (Enterprises) getIntent().getSerializableExtra("enterprises");
        kz_name.setText(enterprises.getName());
        viewPager = (ViewPager)findViewById(R.id.kz_viewPager);
        viewPager.setOffscreenPageLimit(3);
        fragmentPagerAdapter = new KzCollectPagerAdapter(getSupportFragmentManager(),fragmentList,CONTENT);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout=(TabLayout) findViewById(R.id.kz_tabLayout);
        tabLayout.setTabTextColors(Color.parseColor("#707070"),Color.parseColor("#248888"));
        tabLayout.setupWithViewPager(viewPager);
    }
    private void initFragment(){
        fragmentList= new ArrayList<>();
        fragmentList.add(new OneCollectFragment());
        fragmentList.add(new TwoCollectFragment());
        fragmentList.add(new ThreeCollectFragment());
        fragmentList.add(new FourCollectFragment());
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kz_fh:
                AlertDialog.Builder builder=new AlertDialog.Builder(KzCollectActivity.this);
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
            case R.id.kz_tj:
                Toast.makeText(this, extTableOne.getQymc(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, extTableThrees.get(0).getFxsd(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void initThrees(){
        extTableThrees=new ArrayList<>();
        ExtTableThree extTableThree = new ExtTableThree(enterprises.getName(),1,"","","","","","",""
                ,"","","","","","","");
        extTableThrees.add(extTableThree);
    }
    private void initOne(){
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        extTableOne = new ExtTableOne("","","","","","","","","",0,"","","","","",""
                ,"","","","","",0,"","","","","","","","",1
                ,1,1,1,1,"","","","",new Date(),preferences.getInt("id",0));

    }

    public List<ExtTableThree> getExtTableThrees() {

        return extTableThrees;
    }
    public ExtTableOne getExtTableOne() {
        return extTableOne;
    }
    public List<ExtTableTwo> getExtTableTwos() {
        List<ExtTableTwo>extTableTwos = new ArrayList<>();
        for (int i = 0; i<extTableThrees.size(); i++){
            ExtTableThree extTableThree = extTableThrees.get(i);
            ExtTableTwo extTableTwo = new ExtTableTwo(extTableThree.getDisplayOrder(),extTableThree.getFxdmc(),extTableThree.getSzwz(),extTableThree.getZywxys(),extTableThree.getKndzsglx(),"",extTableThree.getFzr());
            extTableTwos.add(extTableTwo);
        }
        return extTableTwos;
    }

    public List<ExtTableFour> getExtTableFours() {
        List<ExtTableFour> extTableFours=new ArrayList<>();
        for (int i = 0; i<extTableThrees.size(); i++){
            ExtTableThree extTableThree = extTableThrees.get(i);
            ExtTableFour extTableFour = new ExtTableFour(extTableThree.getFxsd(),extTableThree.getDisplayOrder(),extTableThree.getSzwz(),extTableThree.getZywxys(),extTableThree.getCqdzygkcs());
            extTableFours.add(extTableFour);
        }
        return extTableFours;
    }
}
