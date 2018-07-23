package com.example.ruianapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.adapter.NoAdapter;
import com.example.ruianapp.adapter.OkAdapter;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.Inspect;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class OkFragment extends Fragment {
    private RecyclerView ok_recycler;
    private LinearLayoutManager layoutManager;
    private OkAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ok, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        layoutManager=new LinearLayoutManager(getActivity());
        ok_recycler=(RecyclerView)getActivity().findViewById(R.id.ok_recycler);
        ok_recycler.setLayoutManager(layoutManager);
        judegData();
    }
    private void judegData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("14447889955"+getActivity().getIntent().getStringExtra("enterprisesID"));
                    UtlisOkhttp.sendEnterprisesOkHttpRequest(getActivity().getIntent().getStringExtra("enterprisesId"),getActivity().getIntent().getStringExtra("enterprisesType"),"1", Constants.CJGS_URL,new okhttp3.Callback(){
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
                    adapter = new OkAdapter(JsonGet.enterprises(response), getActivity(),intent.getStringExtra("enterprisesType"));
                    ok_recycler.setAdapter(adapter);

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
