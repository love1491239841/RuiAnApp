package com.example.ruianapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.adapter.FragmentPagerAdapter;
import com.example.ruianapp.fragment.NoFragment;
import com.example.ruianapp.fragment.OkFragment;

import java.util.ArrayList;
import java.util.List;

public class RoutineActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView routine_no,routine_ok;
    private ImageView routine_fh;
    private ViewPager routine_ViewPager;
    private com.example.ruianapp.adapter.FragmentPagerAdapter adapter;
    private List<Fragment>fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initData();
        initView();
    }

    private void initView(){
        routine_fh=(ImageView)findViewById(R.id.routine_fh);
        routine_fh.setOnClickListener(this);
        routine_ViewPager=(ViewPager)findViewById(R.id.routine_viewPager);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        routine_ViewPager.setAdapter(adapter);
        routine_ViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        routine_no.setTextColor(Color.parseColor("#000000"));
                        routine_ok.setTextColor(Color.parseColor("#ffffff"));
                        routine_no.setBackgroundResource(R.drawable.routinetext1_shape);
                        routine_ok.setBackgroundResource(R.drawable.routinetext2_shape);
                        break;
                    case 1:
                        routine_no.setTextColor(Color.parseColor("#ffffff"));
                        routine_ok.setTextColor(Color.parseColor("#000000"));
                        routine_no.setBackgroundResource(R.drawable.routinetext2_shape);
                        routine_ok.setBackgroundResource(R.drawable.routinetext1_shape);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        routine_no=(TextView)findViewById(R.id.routine_no);
        routine_ok=(TextView)findViewById(R.id.routine_ok);
        routine_no.setOnClickListener(this);
        routine_ok.setOnClickListener(this);
    }
    private void initData(){
        fragmentList=new ArrayList<>();
        fragmentList.add(new NoFragment());
        fragmentList.add(new OkFragment());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.routine_fh:
                finish();
                break;
            case R.id.routine_no:
                routine_ViewPager.setCurrentItem(0);
                break;
            case R.id.routine_ok:
                routine_ViewPager.setCurrentItem(1);
                break;
        }

    }
}
