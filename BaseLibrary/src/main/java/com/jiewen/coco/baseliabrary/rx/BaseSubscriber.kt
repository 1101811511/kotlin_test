package com.jiewen.coco.baseliabrary.rx

import rx.Subscriber


/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/21/212:57 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
open class BaseSubscriber<T> : Subscriber<T>(){
    override fun onCompleted() {
    }
    //统一处理 onError和onComplete
    override fun onError(e: Throwable?) {

    }

    override fun onNext(t: T) {

    }
}