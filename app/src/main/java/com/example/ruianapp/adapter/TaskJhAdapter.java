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

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class TaskJhAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Plan> PlanList;
    private Context context;

    public TaskJhAdapter(List<Plan> PlanList, Context context) {
        this.PlanList = PlanList;
        this.context = context;
    }
    class JhHolder extends RecyclerView.ViewHolder{
        private TextView task_item_name;
        private TextView task_item_time;
        private View view;
        public JhHolder(View itemView) {
            super(itemView);
            task_item_name=(TextView)itemView.findViewById(R.id.item_name);
            task_item_time=(TextView)itemView.findViewById(R.id.item_time);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return PlanList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        JhHolder holder = new TaskJhAdapter.JhHolder(LayoutInflater.from(context).inflate(R.layout.tasklist2_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Plan plan = PlanList.get(position);
        ((JhHolder)holder).task_item_name.setText(plan.getName());
        ((JhHolder)holder).task_item_time.setText(plan.getTime());
        ((JhHolder)holder).view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("确定删除这条计划" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataSupport.deleteAll(Plan.class,"name = ?",PlanList.get(position).getName());
                                PlanList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#248888"));
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#248888"));
                return false;
            }
        });
        ((JhHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("计划")
                        .setMessage(PlanList.get(position).getContent())
                        .show();
            }
        });
    }
}
