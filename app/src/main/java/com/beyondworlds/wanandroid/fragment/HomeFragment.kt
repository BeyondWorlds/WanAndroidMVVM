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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beyondworlds.wanandroid.R
import com.beyondworlds.wanandroid.adapter.HomeArticalAdapter
import com.beyondworlds.wanandroid.net.bean.Artical
import com.beyondworlds.wanandroid.mvvm.HomeViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var mView: View
    var mDataList = mutableListOf<Artical>()
    val mHomeViewModel: HomeViewModel by lazy { HomeViewModel() }
    lateinit var mHomeAdapter: HomeArticalAdapter

    var mCurrentArticle: Artical? = null
    var mCurrentPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        refresh_layout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                mHomeViewModel.getArticalList(0)
            }
        })
        var layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recyclerview.layoutManager = layoutManager
        recyclerview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        mHomeAdapter = HomeArticalAdapter(mDataList)
        recyclerview.adapter = mHomeAdapter

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
                            mHomeViewModel.collectArtical(artical.id)
                        } else {
                            mHomeViewModel.cancelCollectArtical(artical.id)
                        }

                    }
                }
            }
        })
    }

    private fun initData() {
        mHomeViewModel.mHomeArticalLiveData.observe(viewLifecycleOwner, Observer { data ->
            Handler(Looper.getMainLooper()).post(Runnable {
                refresh_layout.isRefreshing = false
                mHomeAdapter.setList(data.datas)
            })
        })
        mHomeViewModel.mCollectStateLiveData.observe(viewLifecycleOwner, Observer {
            try {
                mCurrentArticle?.let {
                    it.collect = !it.collect
                }
                mHomeAdapter.notifyItemChanged(mCurrentPosition)
            } catch (e: Exception) {
            }

        })
        mHomeViewModel.getArticalList(0)
    }
}
