package com.example.ruianapp.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.view.HandWriteView;

import java.io.File;
import java.io.IOException;

public class SignatureActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2;
    private HandWriteView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        initView();
    }
    private void initView(){
        button1= (Button) findViewById(R.id.save);
        button2= (Button) findViewById(R.id.clear);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        view= (HandWriteView) findViewById(R.id.view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                if (view.isSign())
                    try {
                        view.save(Constants.path);
                        setResult(101);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                else {
                    Toast.makeText(SignatureActivity.this, "还没有签名！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.clear:
                view.clear();
                break;
        }
    }
}
