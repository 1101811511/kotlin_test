package com.jiewen.coco.usercenter.presenter.view

import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.presenter.view.BaseView

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/218:33 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
interface RegistView :BaseView {

    fun getUserName():String
    fun getPassWord():String
    fun getConfirmPsd():String

    fun registResponse(response:LoginBean):Unit

}