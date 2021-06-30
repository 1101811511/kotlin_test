package com.jiewen.coco.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.jiewen.coco.baseliabrary.common.Constant
import com.jiewen.coco.baseliabrary.data.net.RetrofitFactory
import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.ui.activity.BaseMvpActivity
import com.jiewen.coco.usercenter.R
import com.jiewen.coco.usercenter.presenter.LoginPresenter
import com.jiewen.coco.usercenter.presenter.view.LoginView
import com.jiewen.coco.usercenter.service.impl.UserService
import kotlinx.android.synthetic.main.activity_login_regist.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/17/212:57 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_regist)
        mPresenter = LoginPresenter()
        mPresenter.mView = this
        login.setOnClickListener {
            mPresenter.loginRequest()
        }
    }


    override fun LoginResponse(response: LoginBean) {
      Log.i(Constant.TAG,"登陆成功")
        toast(response.username)
    }

    override fun getUserName(): String {
        return user_name.text.toString().trim()
    }

    override fun getPassWord(): String {
        return pass_word.text.toString().trim()
    }


    override fun onError(msg: String) {
        toast(msg)
    }


}