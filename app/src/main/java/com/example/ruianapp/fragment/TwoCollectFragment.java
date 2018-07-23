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
import com.example.ruianapp.adapter.FourCollectAdapter;
import com.example.ruianapp.adapter.TwoCollectAdapter;
import com.example.ruianapp.bean.ExtTableFour;
import com.example.ruianapp.bean.ExtTableTwo;

import java.util.List;

public class TwoCollectFragment extends Fragment{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TwoCollectAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two_collect, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        KzCollectActivity kzCollectActivity = (KzCollectActivity) getActivity();
        List<ExtTableTwo> extTableTwos = kzCollectActivity.getExtTableTwos();
        recyclerView= (RecyclerView) getActivity().findViewById(R.id.two_recycler);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        adapter=new TwoCollectAdapter(extTableTwos,getActivity());
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
