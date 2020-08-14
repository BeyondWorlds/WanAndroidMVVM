package com.beyondworlds.wanandroid.net.bean

/**
 *  Created by BeyondWorlds
 *  on 2020/6/23
 */
/**
 * 列表界面ui控制
 */
data class ListUiData<T>(
    val isShowLoading: Boolean,
    val isRefresh: Boolean, // 刷新
    val isLastData: Boolean, // 加载更多
    val showSuccess: T,
    val showError: String?,
    val needLogin: Boolean? = null
)