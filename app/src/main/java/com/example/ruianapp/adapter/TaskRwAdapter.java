package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.bean.Plan;
import com.example.ruianapp.bean.Task;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class TaskRwAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Task> TaskList;
    private Context context;

    public TaskRwAdapter(List<Task> TaskList, Context context) {
        this.TaskList = TaskList;
        this.context = context;
    }
    class RwHolder extends RecyclerView.ViewHolder{
        private TextView task_item_name;
        public RwHolder(View itemView) {
            super(itemView);
            task_item_name=(TextView)itemView.findViewById(R.id.tasklist3_item_name);
        }
    }
    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RwHolder holder = new RwHolder(LayoutInflater.from(context).inflate(R.layout.tasklist3_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Task Task = TaskList.get(position);
        ((RwHolder)holder).task_item_name.setText(Task.getName());
    }
}
