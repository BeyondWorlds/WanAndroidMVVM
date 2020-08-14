package com.beyondworlds.wanandroid

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 *  Created by BeyondWorlds
 *  on 2020/6/17
 */
class App : Application() {
    companion object {
        var sApplicaton: Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        sApplicaton = this
    }
}