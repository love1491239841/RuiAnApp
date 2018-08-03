package com.example.ruianapp.Utlis;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetImageView {
    public void getImageView(final Context context, String imgUrl, final String imgName) {
        Glide.with(context).load(imgUrl).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
            @Override
            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                try {
                    savaBitmap(imgName, bytes,context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void savaBitmap(String imgName, byte[] bytes,Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileOutputStream fos = null;
            try {
                File imgDir = new File(Constants.path);
                if (!imgDir.exists()) {
                    imgDir.mkdirs();
                }
                imgName = Constants.path + "/" + imgName;
                fos = new FileOutputStream(imgName);
                fos.write(bytes);
                Toast.makeText(context, "图片已保存到" + Constants.path, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(context, "请检查SD卡是否可用", Toast.LENGTH_SHORT).show();
        }
    }
}
