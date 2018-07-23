package com.example.ruianapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.LoginActivity;
import com.example.ruianapp.activity.MyInformActivity;
import com.example.ruianapp.activity.MyLeaveActivity;
import com.example.ruianapp.activity.MyPlanActivity;
import com.example.ruianapp.activity.MyTaskActivity;
import com.example.ruianapp.activity.SigninActivity;
import com.example.ruianapp.activity.UserSzActivity;
import com.example.ruianapp.activity.UserinfoActivity;

public class UserFragment extends Fragment implements View.OnClickListener{
    private LinearLayout fmuser_sz,fmuser_user,fmuser_task,fmuser_jh,fmuser_qj,fmuser_tz,fmuser_kq;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        fmuser_sz=(LinearLayout)getActivity().findViewById(R.id.fmuser_sz);
        fmuser_sz.setOnClickListener(this);
        fmuser_user=(LinearLayout)getActivity().findViewById(R.id.fmuser_user);
        fmuser_user.setOnClickListener(this);
        fmuser_task=(LinearLayout)getActivity().findViewById(R.id.fmuser_task);
        fmuser_task.setOnClickListener(this);
        fmuser_jh=(LinearLayout)getActivity().findViewById(R.id.fmuser_jh);
        fmuser_jh.setOnClickListener(this);
        fmuser_qj=(LinearLayout)getActivity().findViewById(R.id.fmuser_qj);
        fmuser_qj.setOnClickListener(this);
        fmuser_tz= (LinearLayout) getActivity().findViewById(R.id.fmuser_tz);
        fmuser_tz.setOnClickListener(this);
        fmuser_kq= (LinearLayout) getActivity().findViewById(R.id.fmuser_kq);
        fmuser_kq.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fmuser_sz:
                Intent intent = new Intent(getActivity(), UserSzActivity.class);
                startActivity(intent);
                break;
            case R.id.fmuser_user:
                Intent intent1 = new Intent(getActivity(), UserinfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.fmuser_task:
                Intent intent2 = new Intent(getActivity(), MyTaskActivity.class);
                startActivity(intent2);
                break;
            case R.id.fmuser_jh:
                Intent intent3 = new Intent(getActivity(), MyPlanActivity.class);
                startActivity(intent3);
                break;
            case R.id.fmuser_qj:
                Intent intent4 = new Intent(getActivity(), MyLeaveActivity.class);
                startActivity(intent4);
                break;
            case R.id.fmuser_tz:
                Intent intent5 = new Intent(getActivity(), MyInformActivity.class);
                startActivity(intent5);
                break;
            case R.id.fmuser_kq:
                Intent intent6 = new Intent(getActivity(), SigninActivity.class);
                startActivity(intent6);
                break;
        }
    }
    @Override

    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            SharedPreferences preferences = getActivity().getSharedPreferences("data",getActivity().MODE_PRIVATE);
            if (!preferences.getBoolean("if",false)){
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }

    }
}
