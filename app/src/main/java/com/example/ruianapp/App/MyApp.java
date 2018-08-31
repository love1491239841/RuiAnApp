package com.example.ruianapp.App;

import android.app.Application;

import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.bean.EnterprisesList;
import com.liulishuo.filedownloader.FileDownloader;

import org.json.JSONException;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MyApp extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.setup(this);
        DataSupport.deleteAll(EnterprisesList.class);
        initDataBase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DataSupport.deleteAll(EnterprisesList.class);
    }
    private void initDataBase(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendQyNmaeOkHttpRequest(Constants.QYNAME_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            List<EnterprisesList> enterprisesLists = null;
                            try {
                                enterprisesLists = JsonGet.myEnterprisesList(responseData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            DataSupport.saveAll(enterprisesLists);


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
}
