package com.jiewen.coco.baseliabrary.presenter.view

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/18/219:04 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
 interface BaseView {

    fun showLoading();
    fun hideLoading();
    fun onError(msg:String);
}