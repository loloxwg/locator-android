package com.guhun.locatorapplication07.server;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class GetBitMap {

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getHttpBitmap(String url,Callback callback) {
        @SuppressLint("HandlerLeak") Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                callback.onSuccess((Bitmap) msg.obj);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL myFileUrl = null;
                Bitmap bitmap = null;
                try {
                    Log.d(TAG, url);
                    myFileUrl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }).start();
    }

    public interface Callback{
        void onSuccess(Bitmap bitmap);
    }
}
