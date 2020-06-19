package com.beyondworlds.wanandroid.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 *  Created by BeyondWorlds
 *  on 2020/6/15
 */
class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //请求前
//        Log.e("test", "intercept="+System.currentTimeMillis())
        var request = chain.request()
//        Log.e("test", "time="+System.currentTimeMillis()+"  request=" + request.toString()
//                +" \nheader="+request.headers.toString()+ " \n HttpUrl="+request.url)
        //网络请求
        var response = chain.proceed(request)
//        Log.e("test", "time="+System.currentTimeMillis()+"  response Headers=" + response.headers.toString()
//        +"\n HttpUrl="+response.request.url)
        return response
    }
}