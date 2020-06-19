package com.beyondworlds.wanandroid.net.repository

import com.beyondworlds.wanandroid.net.RetrofitHelper
import com.beyondworlds.wanandroid.net.bean.BaseResponse

/**
 *  Created by BeyondWorlds
 *  on 2020/6/16
 */
abstract class ArticalRepository {


    suspend fun collectArtical(articalId: Int): BaseResponse<String> {
       return RetrofitHelper.mService.collectArticle(articalId)
    }


    suspend fun cancelArtical(articalId: Int): BaseResponse<String> {
       return RetrofitHelper.mService.cancelCollectArticle(articalId)
    }
}