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
import com.example.ruianapp.activity.MyInformDetailsActivity;
import com.example.ruianapp.activity.MyTaskDetailsActivity;
import com.example.ruianapp.activity.RoutineActivity;
import com.example.ruianapp.bean.Collect;
import com.example.ruianapp.bean.MyInform;
import com.example.ruianapp.bean.MyTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class MyInformAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyInform> MyInformList;
    private Context context;

    public MyInformAdapter(List<MyInform> MyInformList, Context context) {
        this.MyInformList = MyInformList;
        this.context = context;
    }
    class MyInformHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView status;
        private View view;
        public MyInformHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.myinform_item_name);
            status= (ImageView) itemView.findViewById(R.id.myinform_item_status);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return MyInformList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MyInformHolder holder = new MyInformHolder(LayoutInflater.from(context).inflate(R.layout.myinform_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyInform myInform = MyInformList.get(position);
        ((MyInformHolder)holder).name.setText(myInform.getTitle());
        if (myInform.getStatus()==1){
            ((MyInformHolder)holder).status.setImageResource(R.mipmap.myinformwd);

        }else {
            ((MyInformHolder)holder).status.setImageResource(R.mipmap.myinformyd);
        }
        ((MyInformHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyInformDetailsActivity.class);
                intent.putExtra("myInform",myInform);
                context.startActivity(intent);
            }
        });


    }
}
