package com.jiewen.coco.baseliabrary.presenter

import com.jiewen.coco.baseliabrary.presenter.view.BaseView

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/18/219:04 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
//presener需要持有 view层的引用
open class BasePresenter<T:BaseView> {

    lateinit var mView:T
}