package com.beyondworlds.wanandroid.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  Created by BeyondWorlds
 *  on 2020/6/12
 *  有下拉刷新，上滑加载的界面
 */
abstract class AbsListViewModel : BaseViewModel() {

    var mListUiModel = MutableLiveData<ListUiModel>()
    var mPage=0;

    data class ListUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val isLast: Boolean,
        val needLogin: Boolean? = null
    )
}