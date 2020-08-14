package com.beyondworlds.wanandroid.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beyondworlds.wanandroid.net.bean.Artical
import com.beyondworlds.wanandroid.net.bean.DataList
import com.beyondworlds.wanandroid.net.bean.ListUiData
import com.beyondworlds.wanandroid.net.repository.HomeRepository
import kotlinx.coroutines.*

/**
 *  Created by BeyondWorlds
 *  on 2020/6/11
 */
class HomeViewModel : BaseViewModel() {
    var mHomeArticalLiveData = MutableLiveData<DataList<Artical>>()
    var mPage = 0
    var mHomeUIState = MutableLiveData<ListUiData<MutableList<Artical>>>()
    private val mHomeRepository: HomeRepository by lazy { HomeRepository() }


    /**
     * 获取首页文章列表
     */
    fun getArticalList(page: Int = 0, isRefresh: Boolean = true) {
        executeRequest({
            if (!isRefresh) {
                mPage++
            }
            mHomeRepository.getHomeAritcla(mPage)
        }, {
            mPage = it.curPage
            if (isRefresh) {
                updateUIState(isRefresh = isRefresh, showSuccess = it.datas)
            } else {
                updateUIState(isRefresh = isRefresh, showSuccess = it.datas, isLastData = it.over)
            }
            mHomeArticalLiveData.value = it
        }, {
            updateUIState(isRefresh = isRefresh, showError = it.errorMsg)
        })

    }

    fun refreshArticalList(page: Int) {
        executeRequest({
            mHomeRepository.getHomeAritcla(page)
        }, {
            mHomeArticalLiveData.value = it
        })
    }

    fun loadMoreArtical(page: Int = 0) {
        if (page != 0) {
            mPage = page
        }
        executeRequest({
            mHomeRepository.getHomeAritcla(mPage)
        }, {
            mHomeArticalLiveData.value = it
        })
    }

    fun updateUIState(
        isShowLoading: Boolean = false,
        isRefresh: Boolean = false,
        isLastData: Boolean = false,
        showSuccess: MutableList<Artical>? = null,
        showError: String = ""
    ) {
        mHomeUIState.value = ListUiData<MutableList<Artical>>(
            isShowLoading,
            isRefresh,
            isLastData,
            showSuccess!!,
            showError
        )
    }

}