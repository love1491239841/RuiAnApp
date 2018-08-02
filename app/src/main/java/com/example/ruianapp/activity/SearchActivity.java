package com.example.ruianapp.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.ruianapp.R;
import com.example.ruianapp.adapter.FineGcfkAdapter;
import com.example.ruianapp.adapter.FineLxAdapter;
import com.example.ruianapp.adapter.FineTgAdapter;
import com.example.ruianapp.adapter.FineZgtzAdapter;
import com.example.ruianapp.bean.Gcfk;
import com.example.ruianapp.bean.Gclx;
import com.example.ruianapp.bean.Gctg;
import com.example.ruianapp.bean.Zgtz;
import org.litepal.LitePal;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Intent intent;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.search_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_ss);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setMaxWidth(2000);
//        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.setQueryHint("请输入企业的名称");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(SearchActivity.this, "呵呵", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                switch (intent.getStringExtra("type")){
                    case "gcfk":
                        try {
                            List<Gcfk> gcfks = LitePal.where("gcmc like ?", "%" + newText + "%").limit(5).find(Gcfk.class);
                            FineGcfkAdapter adapter= new FineGcfkAdapter(gcfks,SearchActivity.this);
                            recyclerView.setAdapter(adapter);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "gclx":
                        try {
                            List<Gclx> gclxes = LitePal.where("gcmc like ?", "%" + newText + "%").limit(5).find(Gclx.class);
                            FineLxAdapter adapter= new FineLxAdapter(gclxes,SearchActivity.this);
                            recyclerView.setAdapter(adapter);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "gctg":
                        try {
                            List<Gctg> gctgs = LitePal.where("gcmc like ?", "%" + newText + "%").limit(5).find(Gctg.class);
                            FineTgAdapter adapter= new FineTgAdapter(gctgs,SearchActivity.this);
                            recyclerView.setAdapter(adapter);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "zgtz":
                        try {
                            List<Zgtz> zgtzs = LitePal.where("gcmc like ?", "%" + newText + "%").limit(5).find(Zgtz.class);
                            FineZgtzAdapter adapter= new FineZgtzAdapter(zgtzs,SearchActivity.this);
                            recyclerView.setAdapter(adapter);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
