package com.jiewen.coco.usercenter.service

import com.jiewen.coco.baseliabrary.data.Api.ApiService
import com.jiewen.coco.baseliabrary.data.net.RetrofitFactory
import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import rx.Observable

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/21/212:20 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class Userimpl : com.jiewen.coco.usercenter.service.impl.LoginService {
    override fun doLogin(name: String, pwd: String): Observable<BaseResponse<LoginBean>> {
        return RetrofitFactory.instance.creatApiService(ApiService::class.java).LoginRequest(name,pwd)
    }

    override fun doRegist(name: String, pwd: String, repassword: String): Observable<BaseResponse<LoginBean>> {
        return RetrofitFactory.instance.creatApiService(ApiService::class.java).doRegist(name,pwd,repassword)
    }


}