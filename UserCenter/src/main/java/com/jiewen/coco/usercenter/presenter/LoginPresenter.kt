package com.jiewen.coco.usercenter.presenter

import com.jiewen.coco.baseliabrary.presenter.BasePresenter
import com.jiewen.coco.baseliabrary.rx.BaseSubscriber
import com.jiewen.coco.baseliabrary.rx.extcue
import com.jiewen.coco.usercenter.presenter.view.LoginView
import com.jiewen.coco.usercenter.service.Userimpl
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/18/2110:21 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
 open class LoginPresenter :BasePresenter<LoginView>() {
     fun loginRequest(name:String,passWord:String){
         val  userService = Userimpl()
         userService.doLogin("","").extcue(object :BaseSubscriber<Boolean>(){
             override fun onNext(t: Boolean) {
                 super.onNext(t)

             }
         })
     }

    fun RegistRequest(name:String,passWord:String,confirmPwd:String){
        val  userService = Userimpl()
        userService.doLogin("","").observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :Subscriber<Boolean>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                }

                override fun onNext(t: Boolean?) {
                    mView.LoginResponse(true)

                }

            })
    }
}