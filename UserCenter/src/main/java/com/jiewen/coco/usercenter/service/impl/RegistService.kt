package com.jiewen.coco.usercenter.service.impl

import com.jiewen.coco.baseliabrary.data.protocol.BaseResponse
import com.jiewen.coco.baseliabrary.data.protocol.LoginBean
import rx.Observable

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/219:06 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
interface RegistService {
    fun doRegist(name:String,pwd:String,confirmPwd:String): Observable<BaseResponse<LoginBean>>
}