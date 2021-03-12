package com.guhun.locatorapplication07.server;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// 仿造axios书写的http请求——————GuHun
public class AxiosGH {
    private String requestMethod;
    private boolean useCaches;
    private String serverUrl;
    private int connectTimeout;
    private String encode;
    // 初始化实例
    public AxiosGH(){
        init();
    }
    // 默认为get请求(地址,回调函数)
    public AxiosGH(String url,Callback callback){
        init();
        serverUrl = url;
        handleData(null,callback);
    }
    // get请求(地址,回调函数)
    public void get(String url,Callback callback){
        serverUrl = url;
        handleData(null,callback);
    }
    // get请求(地址,map参数,回调函数)
    public void get(String url,Map<String,Object> params,Callback callback){
        serverUrl = url;
        handleData(params,callback);
    }
    // get请求(地址,object参数,回调函数)
    public void get(String url,Object params,Callback callback) {
        serverUrl = url;
        handleData(objectToMap(params),callback);
    }
    // post请求(地址,回调函数)
    public void post(String url,Callback callback){
        requestMethod = "POST";
        serverUrl = url;
        handleData(null,callback);
    }
    // post请求(地址,map参数,回调函数)
    public void post(String url,Map<String,Object> params,Callback callback){
        requestMethod = "POST";
        serverUrl = url;
        handleData(params,callback);
    }
    // post请求(地址,map参数,回调函数)
    public void post(String url,Object params,Callback callback) {
        requestMethod = "POST";
        serverUrl = url;
        handleData(objectToMap(params),callback);
    }
    // 初始化参数
    private void init(){
        requestMethod = "GET";
        useCaches = false;
        connectTimeout = 3000;
        encode="utf-8";
    }

    //handle中操作页面，以及创建线程
    private void handleData(Map<String, Object> params, Callback callback){
        @SuppressLint("HandlerLeak")
        android.os.Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                callback.onSuccess((String) msg.obj);
            }
        };
        new Thread(new Runnable(){
            @Override
            public void run() {
                submitAllData(params,callback,handler);
            }
        }).start();
    }
    // 博客园-依旧淡然
    //HttpUtils.submitPostData(params, "utf-8")
    private void submitAllData(Map<String, Object> params, Callback callback,Handler handler){
        String data = null;
        if (params!=null) {
             data = getRequestData(params, encode).toString();
        }
        try {
            if(requestMethod.equals("GET") && data != null){
                serverUrl += '?'+data;
            }
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);//方式
            connection.setConnectTimeout(connectTimeout);//超时
            if(requestMethod.equals("POST")){
                connection.setDoInput(true);//向服务器输入
                connection.setDoOutput(true);//向服务器输出
                connection.setUseCaches(useCaches);//缓存
                //设置请求体的类型是文本类型
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //设置请求体的长度
                connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
                //获得输出流，向服务器写入数据
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data.getBytes());
            }
            int response = connection.getResponseCode();            //获得服务器的响应码
            if(response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = connection.getInputStream();
                Message message = new Message();
                message.obj = dealResponseResult(inptStream);       //处理服务器的响应结果
                handler.sendMessage(message);
            }else {
                callback.onFailed("Error: response is" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 博客园-依旧淡然
    private static StringBuffer getRequestData(Map<String, Object> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(Uri.encode(entry.getValue().toString(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
    // 博客园-依旧淡然
    private static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }
    // 回调函数
    public interface Callback{
        void onSuccess(String res);
        void onFailed(String err);
    }

    /**
    * @Description: java类型转为map类型
    * @Param: [obj]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Date: 2021/3/11
    */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        return map;
    }

    // 请求方式
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
    // 使用缓存
    public void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches;
    }
    // 请求地址
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
    // 请求参数
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    // 编码方式
    public void setEncode(String encode) {
        this.encode = encode;
    }
}
