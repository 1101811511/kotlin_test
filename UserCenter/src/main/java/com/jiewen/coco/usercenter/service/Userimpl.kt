package com.jiewen.coco.usercenter.service

import com.jiewen.coco.baseliabrary.data.Api.LoginService
import com.jiewen.coco.baseliabrary.data.net.RetrofitFactory
import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import com.jiewen.coco.usercenter.service.impl.UserService
import rx.Observable

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/21/212:20 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class Userimpl :UserService {
    override fun doLogin(name: String, pwd: String): Observable<BaseResponse<LoginBean>> {
        return RetrofitFactory.instance.creatApiService(LoginService::class.java).LoginRequest(name,pwd)
    }


}