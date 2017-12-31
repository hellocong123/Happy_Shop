package com.cong.happy_shop.http;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class OkHttpHelper {

    private static final String TAG = "OkHttpHelper";
    private static OkHttpHelper mOkHttpHelper;
    private static OkHttpClient mOkHttpClient;
    private final Gson mGson;
    private final Handler mHandler;

    /**
     * 单例模式，私有构造函数，构造函数里面进行一些初始化
     */
    private OkHttpHelper() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) //连接超时
                .writeTimeout(10, TimeUnit.SECONDS) //写超时
                .readTimeout(30, TimeUnit.SECONDS) //读超时
                .build();

        mGson = new Gson();
        mHandler = new Handler();
    }

    /**
     * 使用单例模式获得OkHttpHelper实例
     */
    public static OkHttpHelper getInstance() {
        if (mOkHttpHelper == null) {
            synchronized (OkHttpHelper.class) {
                if (mOkHttpHelper == null) {
                    mOkHttpHelper = new OkHttpHelper();
                }
            }
        }
        return mOkHttpHelper;
    }

    //异步get请求
    public void get(String url, Map<String, Object> params, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        request(request, callback);
    }

    //异步post请求
    public void post(String url, Map<String, Object> params, BaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        request(request, callback);
    }

    /**
     * 构建请求对象，根据请求类型判断使用GET\POST请求
     */
    public Request buildRequest(String url, Map<String, Object> params, HttpMethodType type) {

        Request.Builder builder = new Request.Builder();

        builder.url(url);

        if (type == HttpMethodType.GET) {
            //对GET请求里面的URL进行拼接
            builder.get();
            //把拼接的token添加到request里的builder里面
            builder.url(url);
            //调用GET请求里面的get()方法
            builder.get();
        } else if (type == HttpMethodType.POST) {
            RequestBody body = buildRequestBody(params);
            //把构建好的Body放到request的post请求里面
            builder.post(body);
        }

        return builder.build();
    }

    /**
     * 通过Map的键值对构建请求对象的body
     */
    private RequestBody buildRequestBody(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, Object> entity : params.entrySet()) {
                builder.add(entity.getKey(), entity.getValue().toString());
            }
        }
        return builder.build();
    }

    /**
     * 封装一个request方法，不管post或者get方法中都会用到
     * BaseCallback基类，对OKHttp的回调进行封装。这个类用里面有一个type，
     * 是方便回调中使用Gson对JSON进行解析的封装。
     * 使用Callback的时候只需要在泛型中传入类似Data 、List<Data>即可以方便地使用JSON。
     */
    private void request(final Request request, final BaseCallback callback) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackFailure(e, callback);//联网失败的回调
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                       /*——————————————————————————————返回成功回调——————————————————————————————*/
                    String jsonString = response.body().string();
                    //如果我们需要返回String类型就不用解析
                    if (callback.mtype == String.class) {
                        //如果是字符串，就不能解析，直接通过Handler发送回来
                        callbackSuccess(jsonString, callback, response);

                    } else {

                        /*————————————————————————————————————————————————————————————*/
                        try {
                            //如果返回的是其他类型，则利用Gson去解析
                            Object json = mGson.fromJson(jsonString, callback.mtype);
                            //解析数据成功，并使用Handler发送到主线程后使用callback回调
                            callbackSuccess(json, callback, response);
                        } catch (JsonSyntaxException e) {
                            //数据解析失败返回的错误信息
                            //callback.onError(response, e,callback);
                            callbackError(response, callback, e);
                        }
                    }

                } else {

                       /*————————————————————————————————————————————————————————————*/
                    //状态码是：非200，回调错误信息
                    callbackError(response, callback, null);

                }
            }
        });

    }

    //回调错误信息到主线程（解析错误\状态码非200）
    private void callbackError(Response response, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(e);
            }
        });
    }

    //回调网络连接失败的信息到主线程
    private void callbackFailure(final IOException e, final BaseCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(e);
            }
        });
    }

    //回调成功的信息到主线程
    private void callbackSuccess(final Object object, final BaseCallback callback, final Response response) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, object);
            }
        });
    }

    //这个枚举用于指明是哪一种提交方式
    enum HttpMethodType {
        GET,
        POST
    }


}
