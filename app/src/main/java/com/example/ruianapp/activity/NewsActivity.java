package com.example.ruianapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ruianapp.R;
import com.example.ruianapp.bean.News;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView news_name,news_time,news_content;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        news_name = (TextView)findViewById(R.id.news_name);
        news_time = (TextView)findViewById(R.id.news_time);
        news_content = (TextView)findViewById(R.id.news_content);
        imageView=(ImageView)findViewById(R.id.news_fh);
        imageView.setOnClickListener(this);
        initData();

    }

    private void initData() {
        News news = (News) getIntent().getSerializableExtra("news");
        news_name.setText(news.getTitle());
        news_time.setText(news.getAddTime());
        news_content.setText(news.getContent());
    }
    @Override
    public void onClick(View view) {
        switch (R.id.news_fh){
            case R.id.news_fh:
                finish();
                break;
        }
    }
}
