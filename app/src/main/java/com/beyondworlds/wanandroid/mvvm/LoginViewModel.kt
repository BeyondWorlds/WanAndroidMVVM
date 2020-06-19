package com.beyondworlds.wanandroid.mvvm

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import com.beyondworlds.wanandroid.net.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  Created by BeyondWorlds
 *  on 2020/6/15
 */
class LoginViewModel : BaseViewModel() {

    val mLoginRepository: LoginRepository by lazy { LoginRepository() }
    fun requestLogin(userName: String, pwd: String) {
        if (!checkLoginValid(userName, pwd)) return
        GlobalScope.launch {
            val user = mLoginRepository.requestLogin(userName, pwd)
            withContext(Dispatchers.Main) {
                Log.e("LoginViewModel", "user=" + user.toString())
            }
        }
    }

    fun checkLoginValid(userName: String, pwd: String): Boolean {
        return !TextUtils.isEmpty(userName) && !TextUtils.isEmpty((pwd))
    }
}