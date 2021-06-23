package com.jiewen.coco.baseliabrary.ui.activity

import com.jiewen.coco.baseliabrary.presenter.BasePresenter
import com.jiewen.coco.baseliabrary.presenter.view.BaseView

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/18/219:34 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
open class BaseMvpActivity<T:BasePresenter<*>> :BaseActivity(),BaseView {
    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }


    //延迟加载
    lateinit var mPresenter:T
}