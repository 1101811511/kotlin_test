package com.jiewen.coco.usercenter.ui.activity

import android.os.Bundle
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.ui.activity.BaseMvpActivity
import com.jiewen.coco.usercenter.R
import com.jiewen.coco.usercenter.presenter.LoginPresenter
import com.jiewen.coco.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login_regist.*
import org.jetbrains.anko.toast

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/17/212:57 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class LoginActivity :BaseMvpActivity<LoginPresenter>(),LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_regist)
        mPresenter = LoginPresenter()
        mPresenter.mView =this
        login.setOnClickListener{
            mPresenter.loginRequest("","234234")
        }
    }



    override fun LoginResponse(response: LoginBean) {
        toast("success")
    }

    override fun onError(msg: String) {
        toast(msg);
    }
}