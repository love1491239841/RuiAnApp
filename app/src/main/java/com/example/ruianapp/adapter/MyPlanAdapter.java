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
import com.example.ruianapp.activity.MyPlanDetailsActivity;
import com.example.ruianapp.activity.MyTaskDetailsActivity;
import com.example.ruianapp.activity.RoutineActivity;
import com.example.ruianapp.bean.Collect;
import com.example.ruianapp.bean.MyPlan;
import com.example.ruianapp.bean.MyTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class MyPlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyPlan> MyPlanList;
    private Context context;

    public MyPlanAdapter(List<MyPlan> MyPlanList, Context context) {
        this.MyPlanList = MyPlanList;
        this.context = context;
    }
    class MyPlanHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView status;
        private View view;
        public MyPlanHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.myplan_item_name);
            status= (ImageView) itemView.findViewById(R.id.myplan_item_status);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return MyPlanList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MyPlanHolder holder = new MyPlanHolder(LayoutInflater.from(context).inflate(R.layout.myplan_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyPlan MyPlan = MyPlanList.get(position);
        ((MyPlanHolder)holder).name.setText(MyPlan.getTitle());
        System.out.println("cjscyusbc"+MyPlan.getTitle());
        if (MyPlan.getStatus()==1){
            ((MyPlanHolder)holder).status.setImageResource(R.mipmap.planjx);

        }else {
            ((MyPlanHolder)holder).status.setImageResource(R.mipmap.planjs);
        }
        ((MyPlanHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyPlanDetailsActivity.class);
                intent.putExtra("myPlan",MyPlan);
                context.startActivity(intent);
            }
        });


    }
}
