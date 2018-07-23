package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.CommonPracticeActivity;
import com.example.ruianapp.activity.FuelGasActivity;
import com.example.ruianapp.activity.KzCollectActivity;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.FuelGas;
import com.example.ruianapp.bean.Inspect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class FuelGasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FuelGas> FuelGassList;
    private Context context;

    public FuelGasAdapter(List<FuelGas> FuelGassList, Context context) {
        this.FuelGassList = FuelGassList;
        this.context = context;
    }
    class NoHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private RadioButton radioButton1;
        private RadioButton radioButton2;
        private RadioGroup group;
        private EditText bz;
        public NoHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.fuel_item_name);
            bz= (EditText) itemView.findViewById(R.id.fuel_item_bz);
            group = (RadioGroup) itemView.findViewById(R.id.fuel_item_group);
            radioButton1= (RadioButton) itemView.findViewById(R.id.fuel_item_radio1);
            radioButton2= (RadioButton) itemView.findViewById(R.id.fuel_item_radio2);
        }
    }
    @Override
    public int getItemCount() {
        return FuelGassList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final NoHolder holder = new NoHolder(LayoutInflater.from(context).inflate(R.layout.fuel_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final FuelGas fuelGas = FuelGassList.get(position);
        ((NoHolder)holder).name.setText(fuelGas.getName());
        ((NoHolder)holder).radioButton1.setChecked(true);
        ((NoHolder)holder).group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fuel_item_radio1:
                        fuelGas.setRadio("1");
                        break;
                    case R.id.fuel_item_radio2:
                        fuelGas.setRadio("0");
                        break;
                }
            }
        });
        ((NoHolder)holder).bz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fuelGas.setBz(((NoHolder)holder).bz.getText().toString());
            }
        });

    }
}
