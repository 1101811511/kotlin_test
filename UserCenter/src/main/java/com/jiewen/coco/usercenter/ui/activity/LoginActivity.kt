package com.jiewen.coco.usercenter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.jiewen.coco.baseliabrary.common.Constant
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.ui.activity.BaseMvpActivity
import com.jiewen.coco.usercenter.R
import com.jiewen.coco.usercenter.presenter.LoginPresenter
import com.jiewen.coco.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login_regist.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/17/212:57 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {

    override fun getlayout(): Int = R.layout.activity_login_regist
    override fun getUserName(): String = user_name.text.toString().trim()
    override fun getPassWord(): String = pass_word.text.toString().trim()
    override fun LoginResponse(response: LoginBean) {
        Log.i(Constant.TAG, "登陆成功")
    }


    override fun onError(msg: String) {
        toast(msg)
    }

    override fun initData() {
        mPresenter = LoginPresenter()
        mPresenter.mView =this
        login.setOnClickListener {
            mPresenter.loginRequest()
        }
        register.setOnClickListener{
            this@LoginActivity.startActivityForResult<RegistActivity>(1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // ?.如何有值就返回值，如果没有就返回null
        if (data != null && requestCode == 1) {
            Log.i(Constant.TAG, data.getStringExtra("userName").toString())
            user_name.setText(data.getStringExtra("userName"))
        }
    }


}