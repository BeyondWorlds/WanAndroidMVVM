package com.beyondworlds.wanandroid.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyondworlds.wanandroid.activity.data.Result
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  Created by BeyondWorlds
 *  on 2020/6/12
 *  有下拉刷新，上滑加载的界面
 */
abstract class AbsListViewModel<T> : BaseViewModel() {

    var mListUiData = MutableLiveData<ListUiData<T>>()
    var mPage = 0;

    data class ListUiData<T>(
        val isShowLoading: Boolean,
        val showError: String?,
        val showSuccess: T,
        val isLastData: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )
}