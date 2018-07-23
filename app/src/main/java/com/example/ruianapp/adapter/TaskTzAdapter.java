package com.example.ruianapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ruianapp.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class TaskTzAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> StringList;
    private Context context;

    public TaskTzAdapter(List<String> StringList, Context context) {
        this.StringList = StringList;
        this.context = context;
    }
    class TzHolder extends RecyclerView.ViewHolder{
        private TextView tz_textView;
        public TzHolder(View itemView) {
            super(itemView);
            tz_textView=(TextView)itemView.findViewById(R.id.tz_text);
        }
    }
    @Override
    public int getItemCount() {
        return StringList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TzHolder holder = new TzHolder(LayoutInflater.from(context).inflate(R.layout.tasklist_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TzHolder) holder).tz_textView.setText(StringList.get(position));
    }
}
