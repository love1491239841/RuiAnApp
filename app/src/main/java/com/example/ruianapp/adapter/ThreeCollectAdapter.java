package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.example.ruianapp.R;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.ExtTableThree;
import com.example.ruianapp.bean.Plan;
import com.example.ruianapp.bean.Task;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class ThreeCollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ExtTableThree> ThreeList;
    private Context context;
    private double x=1,y=1,z=1,q=1;
    private Enterprises enterprises;

    public ThreeCollectAdapter(List<ExtTableThree> threeList, Context context, Enterprises enterprises) {
        ThreeList = threeList;
        this.context = context;
        this.enterprises = enterprises;
    }

    class RwHolder extends RecyclerView.ViewHolder{
        private TextView displayOrder,dfxz,fxdj,fxsd;
        private EditText fxdmc,szwz,zywxys,bsfjsj,cqdzygkcs,zrbm,fzr;
        private Spinner kndzsglx,lfssgdknxdx,eblywxhj,cfssgcsdhg;
        private Button sc,tj;
        public RwHolder(View itemView) {
            super(itemView);
            displayOrder=(TextView)itemView.findViewById(R.id.three_item_displayOrder);
            fxdmc= (EditText) itemView.findViewById(R.id.three_item_fxdmc);
            szwz= (EditText) itemView.findViewById(R.id.three_item_szwz);
            zywxys= (EditText) itemView.findViewById(R.id.three_item_zywxys);
            kndzsglx= (Spinner) itemView.findViewById(R.id.three_item_kndzsglx);
            lfssgdknxdx= (Spinner) itemView.findViewById(R.id.three_item_lfssgdknxdx);
            eblywxhj= (Spinner) itemView.findViewById(R.id.three_item_eblywxhj);
            cfssgcsdhg= (Spinner) itemView.findViewById(R.id.three_item_cfssgcsdhg);
            dfxz= (TextView) itemView.findViewById(R.id.three_item_dfxz);
            fxdj= (TextView) itemView.findViewById(R.id.three_item_fxdj);
            fxsd= (TextView) itemView.findViewById(R.id.three_item_fxsd);
            bsfjsj= (EditText) itemView.findViewById(R.id.three_item_bsfjsj);
            cqdzygkcs= (EditText) itemView.findViewById(R.id.three_item_cqdzygkcs);
            zrbm= (EditText) itemView.findViewById(R.id.three_item_zrbm);
            fzr= (EditText) itemView.findViewById(R.id.three_item_fzr);
            sc= (Button) itemView.findViewById(R.id.three_item_sc);
            tj= (Button) itemView.findViewById(R.id.three_item_tj);
        }
    }
    @Override
    public int getItemCount() {
        return ThreeList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RwHolder holder = new RwHolder(LayoutInflater.from(context).inflate(R.layout.threecollect_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ExtTableThree extTableThree = ThreeList.get(position);
        ((RwHolder)holder).displayOrder.setText(extTableThree.getDisplayOrder()+"");
        ((RwHolder)holder).fxdmc.addTextChangedListener(new textClick(((RwHolder)holder).fxdmc,extTableThree,"fxdmc"));
        ((RwHolder)holder).szwz.addTextChangedListener(new textClick(((RwHolder)holder).szwz,extTableThree,"szwz"));
        ((RwHolder)holder).zywxys.addTextChangedListener(new textClick(((RwHolder)holder).zywxys,extTableThree,"zywxys"));
        ((RwHolder)holder).kndzsglx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                extTableThree.setKndzsglx(context.getResources().getStringArray(R.array.spingarrsg)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((RwHolder)holder).lfssgdknxdx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                x= Double.parseDouble(context.getResources().getStringArray(R.array.lfssgdknxdx)[position]);
                q=x*y*z;
                ((RwHolder)holder).dfxz.setText(q+"");
                extTableThree.setDfxz(q+"");
                if (q<70){
                    ((RwHolder)holder).fxdj.setText("D");
                    extTableThree.setFxdj("D");
                    ((RwHolder)holder).fxsd.setText("蓝色风险");
                    extTableThree.setFxsd("蓝色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.BLUE);
                }else if (q>=70 && q<160){
                    ((RwHolder)holder).fxdj.setText("C");
                    extTableThree.setFxdj("C");
                    ((RwHolder)holder).fxsd.setText("黄色风险");
                    extTableThree.setFxsd("黄色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.YELLOW);

                }else if (q>=160 && q<320){
                    ((RwHolder)holder).fxdj.setText("B");
                    extTableThree.setFxdj("B");
                    ((RwHolder)holder).fxsd.setText("橙色风险");
                    extTableThree.setFxsd("橙色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.parseColor("#F9CE00"));

                }else if (q>=320){
                    ((RwHolder)holder).fxdj.setText("A");
                    extTableThree.setFxdj("A");
                    ((RwHolder)holder).fxsd.setText("红色风险");
                    extTableThree.setFxsd("红色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.RED);
                }
                extTableThree.setLfssgdknxdx(x+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((RwHolder)holder).eblywxhj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y= Double.parseDouble(context.getResources().getStringArray(R.array.eblywxhj)[position]);
                q=x*y*z;
                ((RwHolder)holder).dfxz.setText(q+"");
                extTableThree.setDfxz(q+"");
                if (q<70){
                    ((RwHolder)holder).fxdj.setText("D");
                    extTableThree.setFxdj("D");
                    ((RwHolder)holder).fxsd.setText("蓝色风险");
                    extTableThree.setFxsd("蓝色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.BLUE);
                }else if (q>=70 && q<160){
                    ((RwHolder)holder).fxdj.setText("C");
                    extTableThree.setFxdj("C");
                    ((RwHolder)holder).fxsd.setText("黄色风险");
                    extTableThree.setFxsd("黄色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.YELLOW);

                }else if (q>=160 && q<320){
                    ((RwHolder)holder).fxdj.setText("B");
                    extTableThree.setFxdj("B");
                    ((RwHolder)holder).fxsd.setText("橙色风险");
                    extTableThree.setFxsd("橙色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.parseColor("#F9CE00"));

                }else if (q>=320){
                    ((RwHolder)holder).fxdj.setText("A");
                    extTableThree.setFxdj("A");
                    ((RwHolder)holder).fxsd.setText("红色风险");
                    extTableThree.setFxsd("红色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.RED);
                }
                extTableThree.setEblywxhj(y+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((RwHolder)holder).cfssgcsdhg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                z= Double.parseDouble(context.getResources().getStringArray(R.array.cfssgcsdhg)[position]);
                q=x*y*z;
                ((RwHolder)holder).dfxz.setText(q+"");
                extTableThree.setDfxz(q+"");
                if (q<70){
                    ((RwHolder)holder).fxdj.setText("D");
                    extTableThree.setFxdj("D");
                    ((RwHolder)holder).fxsd.setText("蓝色风险");
                    extTableThree.setFxsd("蓝色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.BLUE);
                }else if (q>=70 && q<160){
                    ((RwHolder)holder).fxdj.setText("C");
                    extTableThree.setFxdj("C");
                    ((RwHolder)holder).fxsd.setText("黄色风险");
                    extTableThree.setFxsd("黄色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.YELLOW);

                }else if (q>=160 && q<320){
                    ((RwHolder)holder).fxdj.setText("B");
                    extTableThree.setFxdj("B");
                    ((RwHolder)holder).fxsd.setText("橙色风险");
                    extTableThree.setFxsd("橙色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.parseColor("#F9CE00"));

                }else if (q>=320){
                    ((RwHolder)holder).fxdj.setText("A");
                    extTableThree.setFxdj("A");
                    ((RwHolder)holder).fxsd.setText("红色风险");
                    extTableThree.setFxsd("红色风险");
                    ((RwHolder)holder).fxsd.setBackgroundColor(Color.RED);
                }
                extTableThree.setCfssgcsdhg(z+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((RwHolder)holder).bsfjsj.addTextChangedListener(new textClick(((RwHolder)holder).bsfjsj,extTableThree,"bsfjsj"));
        ((RwHolder)holder).cqdzygkcs.addTextChangedListener(new textClick(((RwHolder)holder).cqdzygkcs,extTableThree,"cqdzygkcs"));
        ((RwHolder)holder).zrbm.addTextChangedListener(new textClick(((RwHolder)holder).zrbm,extTableThree,"zrbm"));
        ((RwHolder)holder).fzr.addTextChangedListener(new textClick(((RwHolder)holder).fzr,extTableThree,"fzr"));
        ((RwHolder)holder).sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    Toast.makeText(context, "第一组表格不能删除", Toast.LENGTH_SHORT).show();
                }else {
                    new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("是否删除这组表格" )
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ThreeList.remove(position);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();

                }

            }
        });
        ((RwHolder)holder).tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatas();
            }
        });
    }
    private void initDatas(){
        ExtTableThree extTableThree = new ExtTableThree(enterprises.getName(),ThreeList.size()+1,"","","","","","",""
                ,"","","","","","","");
        ThreeList.add(extTableThree);
        notifyDataSetChanged();
    }
    private class textClick implements TextWatcher{
        private EditText editText;
        private ExtTableThree extTableThree;
        private String type;

        public textClick(EditText editText, ExtTableThree extTableThree, String type) {
            this.editText = editText;
            this.extTableThree = extTableThree;
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (type){
                case "fxdmc":
                    extTableThree.setFxdmc(editText.getText().toString());
                    break;
                case "szwz":
                    extTableThree.setSzwz(editText.getText().toString());
                    break;
                case "zywxys":
                    extTableThree.setZywxys(editText.getText().toString());
                    break;
                case "bsfjsj":
                    extTableThree.setBsfjsj(editText.getText().toString());
                    break;
                case "cqdzygkcs":
                    extTableThree.setCqdzygkcs(editText.getText().toString());
                    break;
                case "zrbm":
                    extTableThree.setZrbm(editText.getText().toString());
                    break;
                case "fzr":
                    extTableThree.setFzr(editText.getText().toString());
                    break;

            }

        }
    }
}
