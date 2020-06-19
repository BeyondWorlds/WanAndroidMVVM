package com.beyondworlds.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.beyondworlds.wanandroid.fragment.HomeFragment
import com.beyondworlds.wanandroid.fragment.MyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mHomeFragment: HomeFragment? = null
    var mMyFragment: MyFragment? = null
    val FRAGMENT_HOME = 0
    val FRAGMENT_MY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    fun initView() {
        showFragment(FRAGMENT_HOME)
        bottom_navigation_view.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_home -> {
                        showFragment(FRAGMENT_HOME)
                        return true
                    }
                    R.id.action_my -> {
                        showFragment(FRAGMENT_MY)
                        return true
                    }
                    else -> {
                        return false
                    }
                }

            }

        })
    }

    fun initData(){
    }

    /**
     * 显示fragment
     */
    fun showFragment(index: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            FRAGMENT_HOME -> {
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment()
                    transaction.add(R.id.fl_content, mHomeFragment!!)
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }
            FRAGMENT_MY -> {
                if (mMyFragment == null) {
                    mMyFragment = MyFragment()
                    transaction.add(R.id.fl_content, mMyFragment!!)
                } else {
                    transaction.show(mMyFragment!!)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mMyFragment?.let { transaction.hide(it) }
    }
}
