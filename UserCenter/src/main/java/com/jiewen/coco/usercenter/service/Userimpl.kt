package com.jiewen.coco.usercenter.service

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
    override fun doLogin(name: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }


}