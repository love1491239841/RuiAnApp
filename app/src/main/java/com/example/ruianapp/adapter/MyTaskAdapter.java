package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.MyTaskDetailsActivity;
import com.example.ruianapp.activity.RoutineActivity;
import com.example.ruianapp.bean.Collect;
import com.example.ruianapp.bean.MyTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class MyTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyTask> MyTaskList;
    private Context context;

    public MyTaskAdapter(List<MyTask> MyTaskList, Context context) {
        this.MyTaskList = MyTaskList;
        this.context = context;
    }
    class MyTaskHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView status;
        private View view;
        public MyTaskHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.mytask_item_name);
            status= (ImageView) itemView.findViewById(R.id.mytask_item_status);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return MyTaskList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MyTaskHolder holder = new MyTaskHolder(LayoutInflater.from(context).inflate(R.layout.mytask_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyTask MyTask = MyTaskList.get(position);
        ((MyTaskHolder)holder).name.setText(MyTask.getName());
        if (MyTask.isStatus()){
            ((MyTaskHolder)holder).status.setImageResource(R.mipmap.taskwwc);

        }else {
            ((MyTaskHolder)holder).status.setImageResource(R.mipmap.taskwc);
        }
        ((MyTaskHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyTaskDetailsActivity.class);
                intent.putExtra("myTask",MyTask);
                context.startActivity(intent);
            }
        });


    }
}
