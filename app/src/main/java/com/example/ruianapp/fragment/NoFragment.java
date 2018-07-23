package com.example.ruianapp.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.MainActivity;
import com.example.ruianapp.adapter.NoAdapter;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.Inspect;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class NoFragment extends Fragment {
    private RecyclerView no_recycler;
    private LinearLayoutManager layoutManager;
    private NoAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        layoutManager=new LinearLayoutManager(getActivity());
        no_recycler=(RecyclerView)getActivity().findViewById(R.id.no_recycler);
        no_recycler.setLayoutManager(layoutManager);
        judegData();
    }
    private void judegData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    UtlisOkhttp.sendEnterprisesOkHttpRequest(getActivity().getIntent().getStringExtra("enterprisesId"),getActivity().getIntent().getStringExtra("enterprisesType"),"0",Constants.CJGS_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            try {
                                showResponse(responseData);
                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                            call.cancel();
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
                    Intent intent = getActivity().getIntent();
                    adapter = new NoAdapter(JsonGet.enterprises(response), getActivity(),intent.getStringExtra("enterprisesType"));
                    no_recycler.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
