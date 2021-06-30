package com.jiewen.coco.usercenter.service.impl

import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/21/212:19 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */


//module的接口
interface LoginService  {
    fun doLogin( name:String, pwd:String): Observable<BaseResponse<LoginBean>>
}