package com.jiewen.coco.usercenter.presenter

import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.presenter.BasePresenter
import com.jiewen.coco.baseliabrary.rx.BaseSubscriber
import com.jiewen.coco.baseliabrary.rx.extcue
import com.jiewen.coco.usercenter.presenter.view.LoginView
import com.jiewen.coco.usercenter.service.Loginimpl

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/18/2110:21 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
 open class LoginPresenter :BasePresenter<LoginView>() {
    //继承泛型类时，必须对父类中的类型参数进行初始化。
     fun loginRequest(){
         val  userService = Loginimpl()
         userService.doLogin(mView.getUserName(),mView.getPassWord()).extcue(object :BaseSubscriber<BaseResponse<LoginBean>>(){
             override fun onNext(t: BaseResponse<LoginBean>) {
                    if (t.errorCode==0){
                        mView.LoginResponse(t.data)
                    }else{
                        mView.onError(t.errorMsg)
                    }

             }
         })
     }

}