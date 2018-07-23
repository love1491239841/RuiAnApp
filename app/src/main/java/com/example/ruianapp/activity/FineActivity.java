package com.example.ruianapp.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.adapter.FineGcfkAdapter;
import com.example.ruianapp.adapter.FineLxAdapter;
import com.example.ruianapp.adapter.FineTgAdapter;
import com.example.ruianapp.adapter.FineZgtzAdapter;
import com.example.ruianapp.adapter.NewsAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class FineActivity extends AppCompatActivity {
    private Intent intent;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        recyclerView= (RecyclerView) findViewById(R.id.fine_recycler);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        intent = getIntent();
        switch (intent.getStringExtra("type")){
            case "gcfk":
                actionBar.setTitle("工程罚款通知单");
                initGcfkData();
                break;
            case "lx":
                actionBar.setTitle("工程联系单");
                initGclxData();
                break;
            case "tg":
                actionBar.setTitle("工程停工通知单");
                initGctgData();
                break;
            case "zg":
                actionBar.setTitle("整改通知单");
                initZgztData();
                break;
        }
    }
    private void initGcfkData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.GCFKList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showGcfkResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showGcfkResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    FineGcfkAdapter adapter= new FineGcfkAdapter(JsonGet.myGcfk(response),FineActivity.this);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    private void initGclxData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.GCLXList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showGclxResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showGclxResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    FineLxAdapter adapter= new FineLxAdapter(JsonGet.myGclx(response),FineActivity.this);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    private void initGctgData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.GCTGList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showGctgResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showGctgResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    FineTgAdapter adapter= new FineTgAdapter(JsonGet.myGctg(response),FineActivity.this);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    private void initZgztData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendFineOkHttpRequest(Constants.ZGTZList_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showZgtzResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showZgtzResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    FineZgtzAdapter adapter= new FineZgtzAdapter(JsonGet.myZgtz(response),FineActivity.this);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fine_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fine_menu_tj:
                switch (intent.getStringExtra("type")){
                    case "gcfk":
                        Intent intent1 = new Intent(FineActivity.this, ProjectFineActivity.class);
                        startActivity(intent1);
                        break;
                    case "lx":
                        Intent intent2 = new Intent(FineActivity.this, ContactActivity.class);
                        startActivity(intent2);
                        break;
                    case "tg":
                        Intent intent3 = new Intent(FineActivity.this, LockoutActivity.class);
                        startActivity(intent3);
                        break;
                    case "zg":
                        Intent intent4 = new Intent(FineActivity.this, SettleActivity.class);
                        startActivity(intent4);
                        break;
                }
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        switch (intent.getStringExtra("type")){
            case "gcfk":
                initGcfkData();
                break;
            case "lx":
                initGclxData();
                break;
            case "tg":
                initGctgData();
                break;
            case "zg":
                initZgztData();
                break;
        }
    }
}
