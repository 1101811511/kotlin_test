package com.jiewen.coco.usercenter.ui.activity

import android.content.Intent
import android.util.Log
import com.jiewen.coco.baseliabrary.common.Constant
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.ui.activity.BaseMvpActivity
import com.jiewen.coco.usercenter.R
import com.jiewen.coco.usercenter.presenter.RegistPresenter
import com.jiewen.coco.usercenter.presenter.view.RegistView
import kotlinx.android.synthetic.main.activity_regist.*
import org.jetbrains.anko.toast


/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/218:32 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class RegistActivity : BaseMvpActivity<RegistPresenter>(), RegistView {
    override fun getlayout(): Int = R.layout.activity_regist
    override fun initData() {
        val registPresenter = RegistPresenter();
        registPresenter.mView = this
        register_confirm.setOnClickListener {
            registPresenter.doRegister()
        }
    }

    override fun getUserName(): String = user_name_re.text.toString().trim()

    override fun getPassWord(): String = pass_word_re.text.toString().trim()

    override fun getConfirmPsd(): String = confirm_pass_word_re.text.toString().trim()

    override fun registResponse(response: LoginBean) {
        Log.i(Constant.TAG, response.username)
        val intent = Intent()
        intent.putExtra("userName", response.username)
        setResult(0, intent)
        finish()
    }
}