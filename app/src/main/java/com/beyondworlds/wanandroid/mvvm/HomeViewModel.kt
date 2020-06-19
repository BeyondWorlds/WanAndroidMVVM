package com.beyondworlds.wanandroid.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beyondworlds.wanandroid.net.bean.Artical
import com.beyondworlds.wanandroid.net.bean.DataList
import com.beyondworlds.wanandroid.net.repository.HomeRepository
import kotlinx.coroutines.*

/**
 *  Created by BeyondWorlds
 *  on 2020/6/11
 */
class HomeViewModel : AbsListViewModel() {
    var mHomeArticalLiveData = MutableLiveData<DataList<Artical>>()

    var mCollectStateLiveData = MutableLiveData<Boolean>()
    private val mHomeRepository: HomeRepository by lazy { HomeRepository() }

    init {
        mCollectStateLiveData.value = false
    }

    /**
     * 获取首页文章列表
     */
    fun getArticalList(page: Int, isLoadMore: Boolean = false) {
        if (isLoadMore) {
            loadMoreArtical()
        } else {
            refreshArticalList(page)
        }
    }

    fun refreshArticalList(page: Int) {
        executeRequest({
            mHomeRepository.getHomeAritcla(page)
        }, {
            mHomeArticalLiveData.value = it
        })
    }

    fun loadMoreArtical() {
        executeRequest({
            mHomeRepository.getHomeAritcla(mPage)
        }, {
            mHomeArticalLiveData.value = it
        })
    }

    /**
     * 收藏文章
     */
    fun collectArtical(articalId: Int) {
        executeRequest({
            mHomeRepository.collectArtical(articalId)
        }, {
            Log.e("http", "collectArticalSuccess")
            mCollectStateLiveData.value = mCollectStateLiveData.value?.let { !it }
        }, {
            if (it.errCode == 0) {
                mCollectStateLiveData.value = mCollectStateLiveData.value?.let { !it }

            }
        })
    }

    /**
     * 取消收藏
     */
    fun cancelCollectArtical(articalId: Int) {
        executeRequest({
            mHomeRepository.cancelArtical(articalId)
        }, {
            mCollectStateLiveData.value = mCollectStateLiveData.value?.let { !it }
        }, {
            if (it.errCode == 0) {
                mCollectStateLiveData.value = mCollectStateLiveData.value?.let { !it }
            }
        })
    }
}