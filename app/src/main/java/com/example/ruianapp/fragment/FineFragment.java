package com.example.ruianapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.activity.ContactActivity;
import com.example.ruianapp.activity.FineActivity;
import com.example.ruianapp.activity.LockoutActivity;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.ProjectFineActivity;
import com.example.ruianapp.activity.SettleActivity;
import com.example.ruianapp.adapter.FineGcfkAdapter;
import com.example.ruianapp.adapter.FineLxAdapter;
import com.example.ruianapp.adapter.FineTgAdapter;
import com.example.ruianapp.adapter.FineZgtzAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class FineFragment extends Fragment implements View.OnClickListener{
    private LinearLayout fine_gcfk,fine_lx,fine_tg,fine_zg;
    private TextView gcfk_sl,gclx_sl,gctg_sl,gczg_sl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        fine_gcfk=(LinearLayout) getActivity().findViewById(R.id.fine_gcfk);
        fine_lx=(LinearLayout)getActivity().findViewById(R.id.fine_lx);
        fine_tg=(LinearLayout)getActivity().findViewById(R.id.fine_tg);
        fine_zg=(LinearLayout)getActivity().findViewById(R.id.fine_zg);
        fine_gcfk.setOnClickListener(this);
        fine_lx.setOnClickListener(this);
        fine_tg.setOnClickListener(this);
        fine_zg.setOnClickListener(this);
        gcfk_sl= (TextView) getActivity().findViewById(R.id.fine_gcfk_sl);
        gclx_sl= (TextView) getActivity().findViewById(R.id.fine_gclx_sl);
        gctg_sl= (TextView) getActivity().findViewById(R.id.fine_gctg_sl);
        gczg_sl= (TextView) getActivity().findViewById(R.id.fine_zgtz_sl);
        initGcfkData();
        initGclxData();
        initGctgData();
        initZgztData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fine_gcfk:
//                Intent intent1 = new Intent(getActivity(), ProjectFineActivity.class);
                Intent intent1 = new Intent(getActivity(), FineActivity.class);
                intent1.putExtra("type","gcfk");
                startActivity(intent1);
                break;
            case R.id.fine_lx:
//                Intent intent2 = new Intent(getActivity(), ContactActivity.class);
                Intent intent2 = new Intent(getActivity(), FineActivity.class);
                intent2.putExtra("type","lx");
                startActivity(intent2);
                break;
            case R.id.fine_tg:
//                Intent intent3 = new Intent(getActivity(), LockoutActivity.class);
                Intent intent3 = new Intent(getActivity(), FineActivity.class);
                intent3.putExtra("type","tg");
                startActivity(intent3);
                break;
            case R.id.fine_zg:
//                Intent intent4 = new Intent(getActivity(), SettleActivity.class);
                Intent intent4 = new Intent(getActivity(), FineActivity.class);
                intent4.putExtra("type","zg");
                startActivity(intent4);
                break;
        }
    }
    @Override

    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            initGcfkData();
            SharedPreferences preferences = getActivity().getSharedPreferences("data",getActivity().MODE_PRIVATE);
            if (!preferences.getBoolean("if",false)){
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }

    }
    private void initGcfkData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.GCFKList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showGcfkResponse(responseData);
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
    private void showGcfkResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    gcfk_sl.setText("数量："+JsonGet.myGcfk(response).size());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    private void initGclxData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.GCLXList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showGclxResponse(responseData);
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
    private void showGclxResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    gclx_sl.setText("数量："+JsonGet.myGclx(response).size());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    private void initGctgData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.GCTGList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showGctgResponse(responseData);
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
    private void showGctgResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    gctg_sl.setText("数量："+JsonGet.myGctg(response).size());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    private void initZgztData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.ZGTZList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showZgtzResponse(responseData);
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
    private void showZgtzResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    gczg_sl.setText("数量："+JsonGet.myZgtz(response).size());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
