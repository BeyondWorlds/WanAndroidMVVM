package com.beyondworlds.wanandroid.net.bean

/**
 *  created by BeyondWorlds
 *  on 2020/6/9
 */
class BaseResponse<T>(val errorCode: Int, val errorMsg: String, val data: T)