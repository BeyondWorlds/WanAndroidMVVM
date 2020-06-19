package com.beyondworlds.wanandroid.mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beyondworlds.wanandroid.App
import com.beyondworlds.wanandroid.net.bean.BaseResponse
import com.beyondworlds.wanandroid.net.responseHandle.AppException
import com.beyondworlds.wanandroid.net.responseHandle.ExceptionHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  Created by BeyondWorlds
 *  on 2020/6/17
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * 处理请求结果，过滤错误码
     */
    fun <T> executeRequest(
        block: suspend () -> BaseResponse<T>,
        onSuccess: (T) -> Unit,
        error: (AppException) -> Unit = {}
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) { block() }
            }.onSuccess {
                kotlin.runCatching {
                    withContext(Dispatchers.Main) {
                        executeResponse(it) {
                            onSuccess(it)
                        }
                    }
                }.onFailure {
                    error(ExceptionHandle.handleException(it))
                }

            }.onFailure {
                error(ExceptionHandle.handleException(it))
            }
        }
    }

    /**
     * 处理请求结果，不过滤
     */
    fun <T> executeRequestNoCheck(
        block: suspend () -> T,
        onSuccess: (T) -> Unit,
        error: (AppException) -> Unit = {}
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) { block() }
            }.onSuccess { data ->
                withContext(Dispatchers.Main) {
                    onSuccess(data)
                }
            }.onFailure {
                error(ExceptionHandle.handleException(it))
            }
        }
    }

    /**
     *结果过滤
     */
    private fun <T> executeResponse(response: BaseResponse<T>, success: (T) -> Unit) {
        if (response.errorCode == 0) {
            if (response.data != null) {
                success(response.data)
            } else {
                throw AppException(response.errorCode, response.errorMsg)
            }
        } else throw AppException(response.errorCode, response.errorMsg)
    }
}