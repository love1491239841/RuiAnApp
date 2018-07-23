package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.activity.NewsActivity;
import com.example.ruianapp.bean.News;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News> newsList;
    private Context context;
    private int x;

    public NewsAdapter(List<News> newsList, Context context,int x) {
        this.newsList = newsList;
        this.context = context;
        this.x=x;
    }
    class NewsHolder extends RecyclerView.ViewHolder{
        private TextView tz_name;
        private TextView tz_time;
        private View view;
        public NewsHolder(View itemView) {
            super(itemView);
            tz_name=(TextView)itemView.findViewById(R.id.tz_name);
            tz_time=(TextView)itemView.findViewById(R.id.tz_time);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        if (x==1){
            return newsList.size();
        }else {
            return 5;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final News news = newsList.get(position);
        ((NewsHolder) holder).tz_name.setText(news.getTitle());
        ((NewsHolder) holder).tz_time.setText(news.getAddTime());
        ((NewsHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("news",news);
                context.startActivity(intent);
            }
        });
    }
    public String transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}
