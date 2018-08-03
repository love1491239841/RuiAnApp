package com.example.ruianapp.Utlis;

import android.widget.Toast;

import com.example.ruianapp.activity.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhanghx on 2018/6/1.
 */

public class UtlisOkhttp {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendFineOkHttpRequest(final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("pageIndex","0")
                .add("pageSize","0")
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendLoginOkHttpRequest(String name,String pass,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginName",name)
                .add("password",pass)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendnewPasswordOkHttpRequest(String name,String pass,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginName",name)
                .add("newPassword",pass)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendNameOkHttpRequest(String name,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginName",name)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendEnterprisesOkHttpRequest(String Id,String type,String status,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("tasksID",Id)
                .add("tasksType",type)
                .add("status",status)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendUserJsonOkHttpRequest(String json,final String address, final okhttp3.Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendIdOkHttpRequest(String userId,final String address, final okhttp3.Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("userId",userId)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendIdStatusOkHttpRequest(String userID,final String address, final okhttp3.Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("userID",userID)
                .add("statu","1")
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendImageOkHttpRequest(String path,String imageName,final String address, final okhttp3.Callback callback) throws IOException {
        File file = new File(path);
        if (!file.exists())
        {
            System.out.println("文件路径不存在");
            return;
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img", imageName, requestBody)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
//    public static void sendImagesOkHttpRequest(File file,final String address, final okhttp3.Callback callback) throws IOException {
//        if (!file.exists())
//        {
//            System.out.println("文件路径不存在");
//            return;
//        }
//        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
//        RequestBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("img", file.getName(), requestBody)
//                .build();
//        Request request = new Request.Builder()
//                .url(address)
//                .post(body)
//                .build();
//        client.newCall(request).enqueue(callback);
//    }
    public static void sendImagesOkHttpRequest(List<String>keys,List<File> files, final String address, final okhttp3.Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i <files.size() ; i++) {
            File file=files.get(i);
            if (file!=null) {
                builder.addFormDataPart(keys.get(i), file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
            }
        }
        MultipartBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendQyNmaeOkHttpRequest(final String address, final okhttp3.Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("pageIndex","0")
                .add("pageSize","0")
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
