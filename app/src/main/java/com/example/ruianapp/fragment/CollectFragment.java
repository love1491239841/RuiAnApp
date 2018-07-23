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
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.MainActivity;
import com.example.ruianapp.adapter.CollectAdapter;
import com.example.ruianapp.bean.Collect;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CollectFragment extends Fragment{
    private RecyclerView collect_jc,collect_rq,collect_kz;
    private CollectAdapter collectAdaptercg;
    private CollectAdapter collectAdapterrq;
    private CollectAdapter collectAdapterkz;
    private LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        collect_jc=(RecyclerView)getActivity().findViewById(R.id.collect_jc);
        layoutManager = new LinearLayoutManager(getActivity());
        collect_jc.setLayoutManager(layoutManager);
        collect_jc.setNestedScrollingEnabled(false);
        collect_rq=(RecyclerView)getActivity().findViewById(R.id.collect_rq);
        layoutManager = new LinearLayoutManager(getActivity());
        collect_rq.setLayoutManager(layoutManager);
        collect_rq.setNestedScrollingEnabled(false);
        collect_kz=(RecyclerView)getActivity().findViewById(R.id.collect_kz);
        layoutManager = new LinearLayoutManager(getActivity());
        collect_kz.setLayoutManager(layoutManager);
        collect_kz.setNestedScrollingEnabled(false);
        initData();
    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendOkHttpRequest( Constants.GCCJ_URL,new okhttp3.Callback(){
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
                try {
                    List<Collect> collects = JsonGet.collects(response);
                    List<Collect> CommonData = new ArrayList<>();
                    List<Collect> Restaurant = new ArrayList<>();
                    List<Collect> ExtData = new ArrayList<>();
                    for (int i=0;i<collects.size();i++){
                        if (collects.get(i).getType().equals("CommonData")){
                            CommonData.add(collects.get(i));
                        }else if (collects.get(i).getType().equals("Restaurant")){
                            Restaurant.add(collects.get(i));
                        }else {
                            ExtData.add(collects.get(i));
                        }
                    }
                    collectAdaptercg = new CollectAdapter(CommonData,getActivity());
                    collectAdapterrq = new CollectAdapter(Restaurant,getActivity());
                    collectAdapterkz = new CollectAdapter(ExtData,getActivity());
                    collect_jc.setAdapter(collectAdaptercg);
                    collect_rq.setAdapter(collectAdapterrq);
                    collect_kz.setAdapter(collectAdapterkz);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override

    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            SharedPreferences preferences = getActivity().getSharedPreferences("data",getActivity().MODE_PRIVATE);
            if (!preferences.getBoolean("if",false)){
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

        }

    }
}
