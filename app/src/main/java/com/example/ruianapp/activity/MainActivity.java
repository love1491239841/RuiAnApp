package com.example.ruianapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.BottomNavigationViewHelper;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.fragment.CollectFragment;
import com.example.ruianapp.fragment.FineFragment;
import com.example.ruianapp.fragment.HomeFragment;
import com.example.ruianapp.fragment.TaskFragment;
import com.example.ruianapp.fragment.UserFragment;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter adapter;
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confi();
        makeRootDirectory(Constants.path);
    }
    private void confi(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissons = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissons,1);
        }else {
            addFragment();
            initView();
        }
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new com.example.ruianapp.adapter.FragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.setSelectedItemId(navigation.getMenu().getItem(position).getItemId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_task:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_collect:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_fine:
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.navigation_user:
                        viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(navigation);

    }
    private void addFragment(){
        fragmentList = new ArrayList<Fragment>();
        Fragment fragment1=new HomeFragment();
        Fragment fragment2=new TaskFragment();
        Fragment fragment3=new CollectFragment();
        Fragment fragment4=new FineFragment();
        Fragment fragment5=new UserFragment();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
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
                    DataSupport.deleteAll(EnterprisesList.class);
                    System.exit(0);
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
                    addFragment();
                    initView();
                }else {
                    Toast.makeText(this, "发生未知的错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }


}
