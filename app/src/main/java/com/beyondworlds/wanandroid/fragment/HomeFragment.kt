package com.beyondworlds.wanandroid.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beyondworlds.wanandroid.App
import com.beyondworlds.wanandroid.R
import com.beyondworlds.wanandroid.adapter.HomeArticalAdapter
import com.beyondworlds.wanandroid.mvvm.CollectViewModel
import com.beyondworlds.wanandroid.net.bean.Artical
import com.beyondworlds.wanandroid.mvvm.HomeViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseVMFragment<HomeViewModel>() {

    val mHomeViewModel: HomeViewModel by lazy {
        createViewModel(HomeViewModel::class.java)
    }
    val mCollectViewModel: CollectViewModel by lazy { createViewModel(CollectViewModel::class.java) }
    var mDataList = mutableListOf<Artical>()
    lateinit var mHomeAdapter: HomeArticalAdapter
    var mCurrentArticle: Artical? = null
    var mCurrentPosition = 0

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        smart_refresh.setOnRefreshListener {
            mHomeViewModel.getArticalList(0)
        }

        var layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        mHomeAdapter = HomeArticalAdapter(mDataList)
        recyclerview.run {
            layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = mHomeAdapter
        }
        mHomeAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

            }
        })
        mHomeAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                var artical = mDataList[position]
                mCurrentArticle = artical
                mCurrentPosition = position
                when (view.id) {
                    R.id.iv_like -> {
                        if (!artical.collect) {
                            mCollectViewModel.collectArtical(artical.id)
                        } else {
                            mCollectViewModel.cancelCollectArtical(artical.id)
                        }

                    }
                }
            }
        })
    }


    override fun startObserver() {
        mHomeViewModel.mHomeArticalLiveData.observe(viewLifecycleOwner, Observer { data ->
            Handler(Looper.getMainLooper()).post(Runnable {
                smart_refresh.finishRefresh()
                mHomeAdapter.setList(data.datas)
            })
        })
        mCollectViewModel.mCollectStateLiveData.observe(
            viewLifecycleOwner,
            Observer {
                try {
                    mCurrentArticle?.let {
                        it.collect = !it.collect
                    }
                    mHomeAdapter.notifyItemChanged(mCurrentPosition)
                } catch (e: Exception) {
                }

            })
    }

    override fun initData() {
        mHomeViewModel.getArticalList(0)
    }
}
