package com.beyondworlds.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.beyondworlds.wanandroid.mvvm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {
    val mLoginViewModel: LoginViewModel by lazy { LoginViewModel() }
    var mPwd: String? = null
    var mUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        initView()

    }

    fun initView() {
        btn_login.isEnabled = true
        btn_login.setOnClickListener {
            mLoginViewModel.requestLogin(
                et_username.text.toString(),
                et_password.text.toString()
            )
        }
        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}
