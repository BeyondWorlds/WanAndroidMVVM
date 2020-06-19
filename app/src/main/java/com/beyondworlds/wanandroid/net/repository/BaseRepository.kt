package com.beyondworlds.wanandroid.net.repository

import com.beyondworlds.wanandroid.net.bean.BaseResponse

/**
 *  Created by BeyondWorlds
 *  on 2020/6/17
 */
abstract class BaseRepository {

     fun <T> dealData(response: BaseResponse<T>): Any {
        if (response.errorCode == 0) {
            val data = response.data
            if (data == null) {
                return true;
            } else return data
        } else {
            return false
        }
    }
}