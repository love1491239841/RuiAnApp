package com.example.ruianapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.bean.ExtTableTwo;

import java.util.List;

public class TwoCollectAdapter extends RecyclerView.Adapter<TwoCollectAdapter.TwoViewHolder> {
    private List<ExtTableTwo>extTableTwos;
    private Context context;

    public TwoCollectAdapter(List<ExtTableTwo> extTableTwos, Context context) {
        this.extTableTwos = extTableTwos;
        this.context = context;
    }

    @Override
    public TwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TwoViewHolder twoViewHolder = new TwoViewHolder(LayoutInflater.from(context).inflate(R.layout.twocollect_item,parent,false));
        return twoViewHolder;
    }

    @Override
    public void onBindViewHolder(TwoViewHolder holder, int position) {
        ExtTableTwo extTableTwo = extTableTwos.get(position);
        holder.tw_co_display_order.setText(extTableTwo.getDisplay_order()+"");
        holder.tw_co_fxdmc.setText(extTableTwo.getFxdmc());
        holder.tw_co_szwz.setText(extTableTwo.getSzwz());
        holder.tw_co_czdzywx.setText(extTableTwo.getCzdzywx());
        holder.tw_co_yfsrsglx.setText(extTableTwo.getYfsrsglx());
        holder.tw_co_bz.setText(extTableTwo.getBz());
        holder.tw_co_fzr.setText(extTableTwo.getFzr());
    }

    @Override
    public int getItemCount() {
        return extTableTwos.size();
    }
    class TwoViewHolder extends RecyclerView.ViewHolder{
        private TextView tw_co_display_order;
        private EditText tw_co_fxdmc;
        private EditText tw_co_szwz;
        private EditText tw_co_czdzywx;
        private EditText tw_co_yfsrsglx;
        private EditText tw_co_bz;
        private EditText tw_co_fzr;
        public TwoViewHolder(View itemView) {
            super(itemView);
            tw_co_display_order=(TextView) itemView.findViewById(R.id.tw_co_id);
            tw_co_fxdmc=(EditText)itemView.findViewById(R.id.tw_co_name);
            tw_co_szwz=(EditText)itemView.findViewById(R.id.tw_co_add);
            tw_co_czdzywx=(EditText)itemView.findViewById(R.id.tw_co_reason);
            tw_co_yfsrsglx=(EditText)itemView.findViewById(R.id.tw_co_acc);
            tw_co_bz=(EditText)itemView.findViewById(R.id.tw_co_bz);
            tw_co_fzr=(EditText)itemView.findViewById(R.id.tw_co_cz);
        }
    }
}
