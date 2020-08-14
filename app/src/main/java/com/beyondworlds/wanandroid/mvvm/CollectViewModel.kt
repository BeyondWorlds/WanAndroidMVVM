package com.beyondworlds.wanandroid.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beyondworlds.wanandroid.net.repository.CommonRepository

/**
 *  Created by BeyondWorlds
 *  on 2020/6/22
 */
class CollectViewModel : BaseViewModel() {
    val mCommonRepository: CommonRepository by lazy { CommonRepository() }
    var mCollectStateLiveData = MutableLiveData<Boolean>().apply { value = false }

    /**
     * 收藏文章
     */
    fun collectArtical(articalId: Int) {
        executeRequest({
            mCommonRepository.collectArtical(articalId)
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
            mCommonRepository.cancelArtical(articalId)
        }, {
            mCollectStateLiveData.value = mCollectStateLiveData.value?.let { !it }
        }, {
            if (it.errCode == 0) {
                mCollectStateLiveData.value = mCollectStateLiveData.value?.let { !it }
            }
        })
    }

}