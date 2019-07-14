package com.example.bottombar.nohttp.Interface;

import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by 59246 on 2018/3/12.
 */

public interface HttpListener<T> {
    //请求成功的监听方法
    void onSuccesed(int what, Response<String> response);

    //请求失败的监听方法
    void onFailed(int what, Response<String> response);

}
