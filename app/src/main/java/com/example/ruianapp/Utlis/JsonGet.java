package com.example.ruianapp.Utlis;

import com.example.ruianapp.bean.Collect;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.EnterprisesList;
import com.example.ruianapp.bean.Gcfk;
import com.example.ruianapp.bean.Gclx;
import com.example.ruianapp.bean.Gctg;
import com.example.ruianapp.bean.LoginMessage;
import com.example.ruianapp.bean.MyInform;
import com.example.ruianapp.bean.MyLeave;
import com.example.ruianapp.bean.MyPlan;
import com.example.ruianapp.bean.MyTask;
import com.example.ruianapp.bean.News;
import com.example.ruianapp.bean.Userinfo;
import com.example.ruianapp.bean.Zgtz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhanghx on 2018/6/1.
 */

public class JsonGet {
    public static LoginMessage loginMessage(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,LoginMessage.class);
    }
    public static Userinfo Userinfo(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,Userinfo.class);
    }
    public static List<Collect> collects(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Collect>>(){}.getType());
    }
    public static List<Enterprises> enterprises(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Enterprises>>(){}.getType());
    }
    public static List<News> news(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<News>>(){}.getType());
    }
    public static List<MyTask> mytask(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<MyTask>>(){}.getType());
    }
    public static List<MyPlan> myPlan(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<MyPlan>>(){}.getType());
    }
    public static List<MyLeave> myLeaves(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<MyLeave>>(){}.getType());
    }
    public static List<MyTask> myTasks(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<MyTask>>(){}.getType());
    }
    public static List<MyInform> myInforms(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<MyInform>>(){}.getType());
    }
    public static List<Gcfk> myGcfk(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Gcfk>>(){}.getType());
    }
    public static List<Gclx> myGclx(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Gclx>>(){}.getType());
    }
    public static List<Gctg> myGctg(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Gctg>>(){}.getType());
    }
    public static List<Zgtz> myZgtz(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Zgtz>>(){}.getType());
    }
    public static List<EnterprisesList> myEnterprisesList(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<EnterprisesList>>(){}.getType());
    }

    public static String remoJson(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        jsonObject1.remove("baseObjId");
        return jsonObject1.toString();
    }


}
