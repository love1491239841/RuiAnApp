package com.example.ruianapp.Utlis;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetImageView {
    public static void saveImage(Bitmap bmp,final String imgName) {
        File appDir = new File(Constants.path);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = imgName;
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
