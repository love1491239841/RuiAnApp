package com.example.ruianapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.bean.ExtTableFour;

import java.util.List;

public class FourCollectAdapter extends RecyclerView.Adapter<FourCollectAdapter.FourViewHolder> {
    private List<ExtTableFour>extTableFours;
    private Context context;

    public FourCollectAdapter(List<ExtTableFour> extTableFours, Context context) {
        this.extTableFours = extTableFours;
        this.context = context;
    }

    @Override
    public FourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FourViewHolder fourViewHolder = new FourViewHolder(LayoutInflater.from(context).inflate(R.layout.fourcollect_item,parent,false));
        return fourViewHolder;
    }

    @Override
    public void onBindViewHolder(FourViewHolder holder, int position) {
        ExtTableFour extTableFour = extTableFours.get(position);
        if (extTableFour.getType().equals("红色风险")){
            ((FourViewHolder)holder).four_type.setBackgroundColor(Color.RED);
        }else if (extTableFour.getType().equals("橙色风险")){
            ((FourViewHolder)holder).four_type.setBackgroundColor(Color.parseColor("#F9CE00"));
        }else if (extTableFour.getType().equals("黄色风险")){
            ((FourViewHolder)holder).four_type.setBackgroundColor(Color.YELLOW);
        }else {
            ((FourViewHolder)holder).four_type.setBackgroundColor(Color.BLUE);
        }
        ((FourViewHolder)holder).four_type.setText(extTableFour.getType());
        ((FourViewHolder)holder).four_cs.setText(extTableFour.getCol1());
        ((FourViewHolder)holder).four_fx.setText(extTableFour.getCol2());
        ((FourViewHolder)holder).four_zd.setText(extTableFour.getCol3());
    }

    @Override
    public int getItemCount() {
        return extTableFours.size();
    }

    public class FourViewHolder extends RecyclerView.ViewHolder{
        private TextView four_type;
        private EditText four_cs;
        private EditText four_fx;
        private EditText four_zd;
        public FourViewHolder(View itemView) {
            super(itemView);

            four_type= (TextView) itemView.findViewById(R.id.four_item_type);
            four_cs= (EditText) itemView.findViewById(R.id.four_item_cs);
            four_fx= (EditText) itemView.findViewById(R.id.four_item_fx);
            four_zd= (EditText) itemView.findViewById(R.id.four_item_zd);
        }
    }
}
