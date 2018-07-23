package com.example.ruianapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.KzCollectActivity;
import com.example.ruianapp.adapter.ThreeCollectAdapter;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.ExtTableThree;

import java.util.ArrayList;
import java.util.List;

public class ThreeCollectFragment extends Fragment{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ThreeCollectAdapter collectAdapter;
    private Enterprises enterprises;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        enterprises= (Enterprises) getActivity().getIntent().getSerializableExtra("enterprises");
        recyclerView= (RecyclerView) getActivity().findViewById(R.id.three_recycler);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        KzCollectActivity kzCollectActivity = (KzCollectActivity) getActivity();
        collectAdapter=new ThreeCollectAdapter(kzCollectActivity.getExtTableThrees(),getActivity(),enterprises);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(collectAdapter);
    }
}
