package com.example.ruianapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.KzCollectActivity;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.ExtTableOne;
import com.example.ruianapp.bean.ExtTableThree;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OneCollectFragment extends Fragment implements View.OnClickListener{
    private ExtTableOne extTableOne;
    private EditText on_co_qymc,on_co_qydz,on_co_jd,on_co_wd,on_co_frdb,on_co_lxdh,on_co_yx,on_co_cz,on_co_qyxz
            ,on_co_scgylc,on_co_zycp,on_co_cyrys,on_co_xlzz,on_co_aqglrys,on_co_cyzk,on_co_zasrs
            ,on_co_tzzyrs,on_co_czbl,on_co_aqjypx,on_co_pxfs,on_co_cfjg,on_co_nhdj,on_co_wxhgpcc,on_co_snzl
            ,on_co_sbfc,on_co_yxkjzy,on_co_rqtrq,on_co_yjya,on_co_yjyl,on_co_aqtr,on_co_kyylfsscaqsgqk;
    private Spinner on_co_sshy,on_co_aqscbzh;
    private RadioGroup on_co_jsxmsts,on_co_zywhsts,on_co_zywhsb,on_co_xfsssts;
    private RadioButton on_co_jsxmsts1,on_co_zywhsts1,on_co_zywhsb1,on_co_xfsssts1,on_co_jsxmsts2,on_co_zywhsts2,on_co_zywhsb2,on_co_xfsssts2;
    private PopupWindow popupWindow;
    private ArrayAdapter arrayAdapter;
    private View popupView;
    private String name1,legal,address,phone,email,jd,wd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_on_collect, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        KzCollectActivity kzCollectActivity = (KzCollectActivity) getActivity();
        extTableOne = kzCollectActivity.getExtTableOne();
        on_co_qymc=(EditText)getActivity().findViewById(R.id.on_co_name);
        on_co_qymc.addTextChangedListener(new textClick(on_co_qymc,extTableOne,"qymc"));
        on_co_qydz=(EditText)getActivity().findViewById(R.id.on_co_add);
        on_co_qydz.addTextChangedListener(new textClick(on_co_qydz,extTableOne,"qydz"));
        on_co_jd=(EditText)getActivity().findViewById(R.id.on_co_jd);
        on_co_jd.addTextChangedListener(new textClick(on_co_jd,extTableOne,"jd"));
        on_co_wd=(EditText)getActivity().findViewById(R.id.on_co_wd);
        on_co_wd.addTextChangedListener(new textClick(on_co_wd,extTableOne,"wd"));
        on_co_frdb=(EditText)getActivity().findViewById(R.id.on_co_frdb);
        on_co_frdb.addTextChangedListener(new textClick(on_co_frdb,extTableOne,"frdb"));
        on_co_lxdh=(EditText)getActivity().findViewById(R.id.on_co_lxdh);
        on_co_lxdh.addTextChangedListener(new textClick(on_co_lxdh,extTableOne,"lxdh"));
        on_co_yx=(EditText)getActivity().findViewById(R.id.on_co_mail);
        on_co_yx.addTextChangedListener(new textClick(on_co_yx,extTableOne,"yx"));
        on_co_cz=(EditText)getActivity().findViewById(R.id.on_co_2016);
        on_co_cz.addTextChangedListener(new textClick(on_co_cz,extTableOne,"cz"));
        on_co_qyxz=(EditText)getActivity().findViewById(R.id.on_co_qyxz);
        on_co_qyxz.addTextChangedListener(new textClick(on_co_qyxz,extTableOne,"qyxz"));
        on_co_scgylc=(EditText)getActivity().findViewById(R.id.on_co_scgylc);
        on_co_scgylc.addTextChangedListener(new textClick(on_co_scgylc,extTableOne,"scgylc"));
        on_co_zycp= (EditText) getActivity().findViewById(R.id.on_co_zycp);
        on_co_zycp.addTextChangedListener(new textClick(on_co_zycp,extTableOne,"zycp"));
        on_co_cyrys=(EditText)getActivity().findViewById(R.id.on_co_cyrys);
        on_co_cyrys.addTextChangedListener(new textClick(on_co_cyrys,extTableOne,"cyrys"));
        on_co_xlzz=(EditText)getActivity().findViewById(R.id.on_co_aqglrys);
        on_co_xlzz.addTextChangedListener(new textClick(on_co_xlzz,extTableOne,"xlzz"));
        on_co_aqglrys=(EditText)getActivity().findViewById(R.id.on_co_aqglrys);
        on_co_aqglrys.addTextChangedListener(new textClick(on_co_aqglrys,extTableOne,"aqglrys"));
        on_co_cyzk=(EditText)getActivity().findViewById(R.id.on_co_cyzk);
        on_co_cyzk.addTextChangedListener(new textClick(on_co_cyzk,extTableOne,"cyzk"));
        on_co_zasrs=(EditText)getActivity().findViewById(R.id.on_co_zasrs);
        on_co_zasrs.addTextChangedListener(new textClick(on_co_zasrs,extTableOne,"zasrs"));
        on_co_tzzyrs=(EditText)getActivity().findViewById(R.id.on_co_tzzyrs);
        on_co_tzzyrs.addTextChangedListener(new textClick(on_co_tzzyrs,extTableOne,"tzzyrs"));
        on_co_czbl=(EditText)getActivity().findViewById(R.id.on_co_czbl);
        on_co_czbl.addTextChangedListener(new textClick(on_co_czbl,extTableOne,"czbl"));
        on_co_aqjypx=(EditText)getActivity().findViewById(R.id.on_co_aqjypx);
        on_co_aqjypx.addTextChangedListener(new textClick(on_co_aqjypx,extTableOne,"aqjypx"));
        on_co_pxfs=(EditText)getActivity().findViewById(R.id.on_co_pxfs);
        on_co_pxfs.addTextChangedListener(new textClick(on_co_pxfs,extTableOne,"pxfs"));
        on_co_cfjg=(EditText)getActivity().findViewById(R.id.on_co_cfjg);
        on_co_cfjg.addTextChangedListener(new textClick(on_co_cfjg,extTableOne,"cfjg"));
        on_co_nhdj=(EditText)getActivity().findViewById(R.id.on_co_nhdj);
        on_co_nhdj.addTextChangedListener(new textClick(on_co_nhdj,extTableOne,"nhdj"));
        on_co_wxhgpcc=(EditText)getActivity().findViewById(R.id.on_co_wxhgpcc);
        on_co_wxhgpcc.addTextChangedListener(new textClick(on_co_wxhgpcc,extTableOne,"wxhgpcc"));
        on_co_snzl=(EditText)getActivity().findViewById(R.id.on_co_ydzl);
        on_co_snzl.addTextChangedListener(new textClick(on_co_snzl,extTableOne,"snzl"));
        on_co_sbfc=(EditText)getActivity().findViewById(R.id.on_co_sbfc);
        on_co_sbfc.addTextChangedListener(new textClick(on_co_sbfc,extTableOne,"sbfc"));
        on_co_yxkjzy=(EditText)getActivity().findViewById(R.id.on_co_yxkjzy);
        on_co_yxkjzy.addTextChangedListener(new textClick(on_co_yxkjzy,extTableOne,"yxkjzy"));
        on_co_rqtrq=(EditText)getActivity().findViewById(R.id.on_co_rqtrq);
        on_co_rqtrq.addTextChangedListener(new textClick(on_co_rqtrq,extTableOne,"rqtrq"));
        on_co_yjya=(EditText)getActivity().findViewById(R.id.on_co_yjya);
        on_co_yjya.addTextChangedListener(new textClick(on_co_yjya,extTableOne,"yjya"));
        on_co_yjyl=(EditText)getActivity().findViewById(R.id.on_co_yjyl);
        on_co_yjyl.addTextChangedListener(new textClick(on_co_yjyl,extTableOne,"yjyl"));
        on_co_aqtr=(EditText)getActivity().findViewById(R.id.on_co_aqtr);
        on_co_aqtr.addTextChangedListener(new textClick(on_co_aqtr,extTableOne,"aqtr"));
        on_co_kyylfsscaqsgqk=(EditText)getActivity().findViewById(R.id.on_co_kyylfsscaqsgqk);
        on_co_kyylfsscaqsgqk.addTextChangedListener(new textClick(on_co_kyylfsscaqsgqk,extTableOne,"kyylfsscaqsgqk"));
        on_co_jsxmsts = (RadioGroup) getActivity().findViewById(R.id.on_co_jsxmsts);
        on_co_zywhsts = (RadioGroup) getActivity().findViewById(R.id.on_co_zywhsts);
        on_co_zywhsb = (RadioGroup) getActivity().findViewById(R.id.on_co_zywhsb);
        on_co_xfsssts = (RadioGroup) getActivity().findViewById(R.id.on_co_xfsssts);
        on_co_jsxmsts1= (RadioButton) getActivity().findViewById(R.id.on_co_jsxmsts1);
        on_co_jsxmsts2= (RadioButton) getActivity().findViewById(R.id.on_co_jsxmsts2);
        on_co_zywhsts1= (RadioButton) getActivity().findViewById(R.id.on_co_zywhsts1);
        on_co_zywhsts2= (RadioButton) getActivity().findViewById(R.id.on_co_zywhsts2);
        on_co_zywhsb1= (RadioButton) getActivity().findViewById(R.id.on_co_zywhsb1);
        on_co_zywhsb2= (RadioButton) getActivity().findViewById(R.id.on_co_zywhsb2);
        on_co_xfsssts1= (RadioButton) getActivity().findViewById(R.id.on_co_xfsssts1);
        on_co_xfsssts2= (RadioButton) getActivity().findViewById(R.id.on_co_xfsssts2);
        on_co_jsxmsts1.setChecked(true);
        on_co_zywhsb1.setChecked(true);
        on_co_zywhsts1.setChecked(true);
        on_co_xfsssts1.setChecked(true);
        on_co_jsxmsts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.on_co_jsxmsts1:
                        extTableOne.setJsxmsts(1);
                        break;
                    case R.id.on_co_jsxmsts2:
                        extTableOne.setJsxmsts(0);
                        break;
                }
            }
        });
        on_co_zywhsb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.on_co_zywhsb1:
                        extTableOne.setJsxmsts(1);
                        break;
                    case R.id.on_co_zywhsb2:
                        extTableOne.setJsxmsts(0);
                        break;
                }
            }
        });
        on_co_zywhsts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.on_co_zywhsts1:
                        extTableOne.setJsxmsts(1);
                        break;
                    case R.id.on_co_zywhsts2:
                        extTableOne.setJsxmsts(0);
                        break;
                }
            }
        });
        on_co_xfsssts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.on_co_xfsssts1:
                        extTableOne.setJsxmsts(1);
                        break;
                    case R.id.on_co_xfsssts2:
                        extTableOne.setJsxmsts(0);
                        break;
                }
            }
        });
        on_co_sshy= (Spinner) getActivity().findViewById(R.id.on_co_sshy);
        on_co_sshy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                extTableOne.setSshy(getResources().getStringArray(R.array.spingarrzy)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        on_co_aqscbzh= (Spinner) getActivity().findViewById(R.id.on_co_aqscbzh);
        on_co_aqscbzh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int[] x ={1,2,3,4};
                extTableOne.setAqscbzh(x[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }
    private class textClick implements TextWatcher {
        private EditText editText;
        private ExtTableOne extTableOne;
        private String type;

        public textClick(EditText editText, ExtTableOne extTableOne, String type) {
            this.editText = editText;
            this.extTableOne = extTableOne;
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (type){
                case "qymc":
                    String name = on_co_qymc.getText().toString();
                    Cursor cursor = DataSupport.findBySQL("SELECT * FROM enterpriseslist WHERE NAME LIKE '%"+name+"%' limit 5");
                    List<EnterprisesList>enterprisesLists = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        if (cursor.getString(cursor.getColumnIndex("name")) != null){
                            name1 = cursor.getString(cursor.getColumnIndex("name"));
                        }else {
                            name1 ="";
                        }
                        if (cursor.getString(cursor.getColumnIndex("legal")) != null){
                            legal = cursor.getString(cursor.getColumnIndex("legal"));
                        }else {
                            legal = "";
                        }
                        if (cursor.getString(cursor.getColumnIndex("address")) != null){
                            address = cursor.getString(cursor.getColumnIndex("address"));
                        }else {
                            address = "";
                        }
                        if (cursor.getString(cursor.getColumnIndex("phone")) != null){
                            phone = cursor.getString(cursor.getColumnIndex("phone"));
                        }else {
                            phone = "";
                        }
                        if (cursor.getString(cursor.getColumnIndex("email")) != null){
                            email = cursor.getString(cursor.getColumnIndex("email"));
                        }else {
                            email = "";
                        }
                        if (cursor.getString(cursor.getColumnIndex("jd")) != null){
                            jd = cursor.getString(cursor.getColumnIndex("jd"));
                        }else {
                            jd = "";
                        }
                        if (cursor.getString(cursor.getColumnIndex("wd")) != null){
                            wd = cursor.getString(cursor.getColumnIndex("wd"));
                        }else {
                            wd = "";
                        }
                        EnterprisesList enterprisesList = new EnterprisesList(name1,legal,address,phone,email,jd,wd);
                        enterprisesLists.add(enterprisesList);
                    }
                    initSearch(enterprisesLists);

                    break;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (type){
                case "qymc":
                    extTableOne.setQymc(editText.getText().toString());
                    break;
                case "qydz":
                    extTableOne.setQydz(editText.getText().toString());
                    break;
                case "jd":
                    extTableOne.setJd(editText.getText().toString());
                    break;
                case "wd":
                    extTableOne.setWd(editText.getText().toString());
                    break;
                case "jd2":
                    extTableOne.setJd2(editText.getText().toString());
                    break;
                case "wd2":
                    extTableOne.setWd2(editText.getText().toString());
                    break;
                case "frdb":
                    extTableOne.setFrdb(editText.getText().toString());
                    break;
                case "lxdh":
                    extTableOne.setLxdh(editText.getText().toString());
                    break;
                case "yx":
                    extTableOne.setYx(editText.getText().toString());
                    break;
                case "cz":
                    extTableOne.setCz(Integer.parseInt(editText.getText().toString()));
                    break;
                case "qyxz":
                    extTableOne.setQyxz(editText.getText().toString());
                    break;
                case "scgylc":
                    extTableOne.setScgylc(editText.getText().toString());
                    break;
                case "zycp":
                    extTableOne.setZycp(editText.getText().toString());
                    break;
                case "sshy":
                    extTableOne.setSshy(editText.getText().toString());
                    break;
                case "cyrys":
                    extTableOne.setCyrys(editText.getText().toString());
                    break;
                case "xlzz":
                    extTableOne.setXlzz(editText.getText().toString());
                    break;
                case "aqglrys":
                    extTableOne.setAqglrys(editText.getText().toString());
                    break;
                case "cyzk":
                    extTableOne.setCyzk(editText.getText().toString());
                    break;
                case "zasrs":
                    extTableOne.setZasrs(editText.getText().toString());
                    break;
                case "tzzyrs":
                    extTableOne.setTzzyrs(editText.getText().toString());
                    break;
                case "czbl":
                    extTableOne.setCzbl(editText.getText().toString());
                    break;
                case "aqjypx":
                    extTableOne.setAqjypx(Integer.parseInt(editText.getText().toString()));
                    break;
                case "pxfs":
                    extTableOne.setPxfs(editText.getText().toString());
                    break;
                case "cfjg":
                    extTableOne.setCfjg(editText.getText().toString());
                    break;
                case "nhdj":
                    extTableOne.setNhdj(editText.getText().toString());
                    break;
                case "wxhgpcc":
                    extTableOne.setWxhgpcc(editText.getText().toString());
                    break;
                case "snzl":
                    extTableOne.setSnzl(editText.getText().toString());
                    break;
                case "sbfc":
                    extTableOne.setSbfc(editText.getText().toString());
                    break;
                case "yxkjzy":
                    extTableOne.setYxkjzy(editText.getText().toString());
                    break;
                case "rqtrq":
                    extTableOne.setRqtrq(editText.getText().toString());
                    break;
                case "jsxmsts":
                    extTableOne.setJsxmsts(Integer.parseInt(editText.getText().toString()));
                    break;
                case "zywhsts":
                    extTableOne.setZywhsts(Integer.parseInt(editText.getText().toString()));
                    break;
                case "zywhsb":
                    extTableOne.setZywhsb(Integer.parseInt(editText.getText().toString()));
                    break;
                case "xfsssts":
                    extTableOne.setXfsssts(Integer.parseInt(editText.getText().toString()));
                    break;
                case "aqscbzh":
                    extTableOne.setAqscbzh(Integer.parseInt(editText.getText().toString()));
                    break;
                case "yjya":
                    extTableOne.setYjya(editText.getText().toString());
                    break;
                case "yjyl":
                    extTableOne.setYjyl(editText.getText().toString());
                    break;
                case "aqtr":
                    extTableOne.setAqtr(editText.getText().toString());
                    break;
                case "kyylfsscaqsgqk":
                    extTableOne.setKyylfsscaqsgqk(editText.getText().toString());
                    break;
            }

        }
    }
    private void initSearch(final List<EnterprisesList> enterprisesLists){
        String[] data=new String[enterprisesLists.size()];
        for (int i =0;i<enterprisesLists.size();i++){
            data[i]=enterprisesLists.get(i).getName();
        }
        popupView = getActivity().getLayoutInflater().inflate(R.layout.finepopupwindow_item,null,false);
        ListView listView = (ListView) popupView.findViewById(R.id.popup_item_listview);
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                on_co_qymc.setText(enterprisesLists.get(position).getName());
                on_co_qydz.setText(enterprisesLists.get(position).getAddress());
                on_co_frdb.setText(enterprisesLists.get(position).getLegal());
                on_co_lxdh.setText(enterprisesLists.get(position).getPhone());
                on_co_yx.setText(enterprisesLists.get(position).getEmail());
                on_co_jd.setText(enterprisesLists.get(position).getJd());
                on_co_wd.setText(enterprisesLists.get(position).getWd());
                popupWindow.dismiss();
            }
        });
        popupWindow =  new PopupWindow(popupView, 720, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.routinetext1_shape));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(on_co_qymc);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    backgroundAlpha(1.0f);
                    popupWindow.dismiss();
                }
                return false;
            }
        });
    }
    private void backgroundAlpha(float f){
        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
        lp.alpha=f;
        getActivity().getWindow().setAttributes(lp);
    }
}
