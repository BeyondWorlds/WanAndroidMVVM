package com.beyondworlds.wanandroid.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.beyondworlds.wanandroid.App
import com.beyondworlds.wanandroid.mvvm.BaseViewModel

/**
 *  Created by BeyondWorlds
 *  on 2020/6/22
 */
abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserver()
        initData()
    }

    abstract fun startObserver()
    abstract fun initData()

    /**
     * 创建viewModel
     */
    protected fun <T : BaseViewModel> createViewModel(vmClass: Class<T>): T {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(App.sApplicaton)
        ).get(vmClass)
    }
}