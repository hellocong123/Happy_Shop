package com.cong.happy_shop.http;


import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public abstract class BaseCallback<T> {

    Type mtype;

    /**
     * 把type转换成对应的类，这里不用看明白也行。
     *
     * @param subclass
     * @return
     */
    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallback() {
        mtype = getSuperclassTypeParameter(getClass());
    }




    /**
     * 网络故障
     */
    public void onFailure(Exception e) {
    }

    /**
     * 状态码大于200，小于300 时调用此方法
     *
     * @param t 通过实体类T，回调一个解析好的对象回来
     */
    public abstract void onSuccess(Response response, T t);//成功的回调


    /**
     * 这里可以是可以在联网的时候给他回一个进度条
     */
    public void onRequestBefore(Request request) {
    }


    /**
     * 失败，得到他的状态码等
     */
    public abstract void onError(Response response, int code, Exception e);//成功的回调


}
