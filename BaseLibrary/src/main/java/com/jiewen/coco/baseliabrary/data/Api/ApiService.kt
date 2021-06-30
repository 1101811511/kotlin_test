package com.jiewen.coco.baseliabrary.data.Api

import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/23/219:13 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
interface ApiService {

    @POST("user/login")
    @FormUrlEncoded
    fun LoginRequest(@Field("username") username:String,@Field("password") pwd:String): Observable<BaseResponse<LoginBean>>


    @POST()
    @FormUrlEncoded
    fun doRegist(@Field("username")  name:String,@Field("password") pwd: String,@Field("repassword") confirmPwd:String):Observable<BaseResponse<LoginBean>>
}