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
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.activity.KzCollectActivity;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.MyPlanActivity;
import com.example.ruianapp.adapter.FourCollectAdapter;
import com.example.ruianapp.adapter.MyPlanAdapter;
import com.example.ruianapp.bean.ExtTableFour;
import com.example.ruianapp.bean.ExtTableThree;
import com.example.ruianapp.bean.ExtTableTwo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FourCollectFragment extends Fragment{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FourCollectAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four_collect, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        KzCollectActivity kzCollectActivity = (KzCollectActivity) getActivity();
        List<ExtTableFour> extTableFours=kzCollectActivity.getExtTableFours();
        recyclerView= (RecyclerView) getActivity().findViewById(R.id.four_recycler);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        adapter = new FourCollectAdapter(extTableFours,getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }
    @Override

    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            initView();
            adapter.notifyDataSetChanged();
        }
    }
}
