package com.beyondworlds.wanandroid.net.repository

import com.beyondworlds.wanandroid.net.RetrofitHelper
import com.beyondworlds.wanandroid.net.bean.Artical
import com.beyondworlds.wanandroid.net.bean.BaseResponse
import com.beyondworlds.wanandroid.net.bean.DataList

/**
 *  Created by BeyondWorlds
 *  on 2020/6/9
 */
class HomeRepository : ArticalRepository() {

    suspend fun getHomeAritcla(page: Int): BaseResponse<DataList<Artical>> {
        return RetrofitHelper.mService.getHomeArtical(page)
    }
}