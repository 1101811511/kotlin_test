package com.jiewen.coco.usercenter.service

import com.jiewen.coco.baseliabrary.data.Api.ApiService
import com.jiewen.coco.baseliabrary.data.net.RetrofitFactory
import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.usercenter.service.impl.RegistService
import rx.Observable

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/219:15 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class Registimpl :RegistService {
    override fun doRegist(name: String, pwd: String, confirmPwd: String): Observable<BaseResponse<LoginBean>> {
        return RetrofitFactory.instance.creatApiService(ApiService::class.java).doRegist(name, pwd, confirmPwd)
    }
}