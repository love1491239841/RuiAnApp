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
import com.example.ruianapp.activity.MyLeaveDetailsActivity;
import com.example.ruianapp.activity.MyTaskDetailsActivity;
import com.example.ruianapp.activity.RoutineActivity;
import com.example.ruianapp.bean.Collect;
import com.example.ruianapp.bean.MyLeave;
import com.example.ruianapp.bean.MyTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class MyLeaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyLeave> MyLeaveList;
    private Context context;

    public MyLeaveAdapter(List<MyLeave> MyLeaveList, Context context) {
        this.MyLeaveList = MyLeaveList;
        this.context = context;
    }
    class MyLeaveHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView status;
        private View view;
        public MyLeaveHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.myleave_item_name);
            status= (ImageView) itemView.findViewById(R.id.myleave_item_status);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return MyLeaveList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MyLeaveHolder holder = new MyLeaveHolder(LayoutInflater.from(context).inflate(R.layout.myleave_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyLeave MyLeave = MyLeaveList.get(position);
        ((MyLeaveHolder)holder).name.setText(MyLeave.getPersons());
        if (MyLeave.getStatus()==1){
            ((MyLeaveHolder)holder).status.setImageResource(R.mipmap.levaetg);

        }else if (MyLeave.getStatus()==2){
            ((MyLeaveHolder)holder).status.setImageResource(R.mipmap.levaewsh);
        }else {
            ((MyLeaveHolder)holder).status.setImageResource(R.mipmap.levaewtg);
        }
        ((MyLeaveHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyLeaveDetailsActivity.class);
                intent.putExtra("myLeave",MyLeave);
                context.startActivity(intent);
            }
        });


    }
}
