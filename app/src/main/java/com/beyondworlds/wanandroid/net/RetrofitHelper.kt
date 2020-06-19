package com.beyondworlds.wanandroid.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Created by BeyondWorlds
 *  on 2020/6/9
 */
object RetrofitHelper : BaseRetrofitHelper() {

    private var retorfit: Retrofit? = null

    val mService by lazy { getService(ApiService::class.java, getRetrofit()!!) }

    private fun getRetrofit(): Retrofit? {
        if (retorfit == null) {
            synchronized(RetrofitHelper::class.java) {
                if (retorfit == null) {
                    retorfit = Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
        return retorfit

    }
}