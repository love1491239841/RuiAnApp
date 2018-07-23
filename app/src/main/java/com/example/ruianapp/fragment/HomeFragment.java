package com.example.ruianapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.activity.HomeContactActivity;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.MainActivity;
import com.example.ruianapp.activity.MoreNewsActivity;
import com.example.ruianapp.adapter.NewsAdapter;
import com.example.ruianapp.bean.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView home_news;
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    private ImageView home_lx;
    private LinearLayout home_gd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        home_news= (RecyclerView) getActivity().findViewById(R.id.home_recycler);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        home_news.setLayoutManager(linearLayoutManager);
        home_news.setNestedScrollingEnabled(false);
        initData();
        home_lx= (ImageView) getActivity().findViewById(R.id.home_lx);
        home_lx.setOnClickListener(this);
        home_gd = (LinearLayout) getActivity().findViewById(R.id.home_gd);
        home_gd.setOnClickListener(this);
    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendOkHttpRequest(Constants.News_URL,new okhttp3.Callback(){
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    newsAdapter = new NewsAdapter(JsonGet.news(response),getActivity(),0);
                    home_news.setAdapter(newsAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_lx:
                Intent intent = new Intent(getActivity(), HomeContactActivity.class);
                startActivity(intent);
                break;
            case R.id.home_gd:
                Intent intent1 = new Intent(getActivity(), MoreNewsActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
