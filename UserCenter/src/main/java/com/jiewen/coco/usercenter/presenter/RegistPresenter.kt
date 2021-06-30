package com.jiewen.coco.usercenter.presenter

import android.app.ActivityManager
import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.baseliabrary.presenter.BasePresenter
import com.jiewen.coco.baseliabrary.rx.BaseSubscriber
import com.jiewen.coco.baseliabrary.rx.extcue
import com.jiewen.coco.usercenter.presenter.view.RegistView
import com.jiewen.coco.usercenter.service.Registimpl
import rx.Scheduler
import rx.schedulers.Schedulers
import rx.schedulers.Schedulers.io

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/218:38 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class RegistPresenter :BasePresenter<RegistView>() {

    val registImp =Registimpl();
    //这里要用到扩展函数
    fun doRegister(name:String,password:String,repassWord:String){
        registImp.doRegist(mView.getUserName(),mView.getPassWord(),mView.getConfirmPsd())
                .extcue(object : BaseSubscriber<BaseResponse<LoginBean>>() {
                    override fun onNext(t: BaseResponse<LoginBean>) {
                        if (t.errorCode ==0){
                            mView.registResponse(t.data)
                        }else{
                            mView.onError(t.errorMsg)
                        }
                    }
                })
               


    }

}