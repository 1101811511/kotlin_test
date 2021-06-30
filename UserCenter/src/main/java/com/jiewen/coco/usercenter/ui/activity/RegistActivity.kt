package com.jiewen.coco.usercenter.ui.activity

import com.jiewen.coco.baseliabrary.ui.activity.BaseMvpActivity
import com.jiewen.coco.usercenter.R
import com.jiewen.coco.usercenter.presenter.RegistPresenter


/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/218:32 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class RegistActivity :BaseMvpActivity<RegistPresenter>() {
    override fun getlayout(): Int  = R.layout.activity_regist
}