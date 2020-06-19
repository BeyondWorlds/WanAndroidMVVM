package com.beyondworlds.wanandroid.net.repository

import com.beyondworlds.wanandroid.net.RetrofitHelper
import com.beyondworlds.wanandroid.net.bean.User

/**
 *  Created by BeyondWorlds
 *  on 2020/6/15
 */
class LoginRepository : BaseRepository() {

    suspend fun requestLogin(userName: String, pwd: String): User {
        return dealData(RetrofitHelper.mService.login(userName, pwd)) as User
    }
}