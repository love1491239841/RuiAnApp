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

import com.example.ruianapp.R;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.MainActivity;
import com.example.ruianapp.activity.RecordActivity;
import com.example.ruianapp.adapter.TaskJhAdapter;
import com.example.ruianapp.adapter.TaskRwAdapter;
import com.example.ruianapp.adapter.TaskTzAdapter;
import com.example.ruianapp.bean.Plan;
import com.example.ruianapp.bean.Task;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerViewtz,recyclerViewjh;
    private TaskTzAdapter tzAdapter;
    private LinearLayoutManager tzlayoutManager;
    private ImageView task_tj;
    private TaskJhAdapter jhAdapter;
    private LinearLayoutManager jhlayoutManager;
//    private LinearLayoutManager rwlayoutManager;
//    private TaskRwAdapter taskRwAdapter;
//    private RecyclerView rwrecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        /*
        * 公司的通知
        * */
        recyclerViewtz = (RecyclerView)getActivity().findViewById(R.id.task_recycler1);
        tzAdapter = new TaskTzAdapter(TzData(),getActivity());
        tzlayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewtz.setLayoutManager(tzlayoutManager);
        recyclerViewtz.setNestedScrollingEnabled(false);
        recyclerViewtz.setAdapter(tzAdapter);
//        /*
//        * 每日的任务
//        * */
//        rwlayoutManager=new LinearLayoutManager(getActivity());
//        rwrecyclerView=(RecyclerView)getActivity().findViewById(R.id.task_recycler3);
//        rwrecyclerView.setLayoutManager(rwlayoutManager);
//        rwrecyclerView.setNestedScrollingEnabled(false);
//        taskRwAdapter = new TaskRwAdapter(RwData(),getActivity());
//        rwrecyclerView.setAdapter(taskRwAdapter);


        /*
        * 添加个人计划
        * */
        task_tj=(ImageView)getActivity().findViewById(R.id.task_tj);
        task_tj.setOnClickListener(this);
        recyclerViewjh = (RecyclerView)getActivity().findViewById(R.id.task_recycler2);
        jhlayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewjh.setLayoutManager(jhlayoutManager);
        recyclerViewjh.setNestedScrollingEnabled(false);
        jhAdapter = new TaskJhAdapter(DataSupport.findAll(Plan.class),getActivity());
        recyclerViewjh.setAdapter(jhAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        jhAdapter.notifyDataSetChanged();
    }

    private List<String> TzData(){
        List<String> listString = new ArrayList<>();
        for (int i=0;i<5;i++){
            listString.add("今天开会"+i);
        }
        return listString;
    }
    private List<Task> RwData(){
        List<Task> listTask = new ArrayList<>();
        for (int i=0;i<5;i++){
            Task task = new Task("采集10家数据采集");
            listTask.add(task);
        }
        return listTask;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.task_tj:
                Intent intent1 = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent1);
                break;
        }
    }
    @Override

    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            SharedPreferences preferences = getActivity().getSharedPreferences("data",getActivity().MODE_PRIVATE);
            if (!preferences.getBoolean("if",false)){
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }

        }

    }
}
