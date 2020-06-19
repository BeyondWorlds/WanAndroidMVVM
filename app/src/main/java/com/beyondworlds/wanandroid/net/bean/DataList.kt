package com.beyondworlds.wanandroid.net.bean

/**
 *     created by  BeyondWorlds
 *     on    2020/6/9
 */
data class DataList<T>(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: MutableList<T>
)