package com.jiewen.coco.baseliabrary.ui.activity

import android.os.Bundle
import com.jiewen.coco.baseliabrary.presenter.BasePresenter
import com.jiewen.coco.baseliabrary.presenter.view.BaseView

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/18/219:34 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayout())
        initData()
    }

    abstract fun initData()


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(msg: String) {

    }

    abstract fun getlayout():Int




    //延迟加载
    lateinit var mPresenter: T
}