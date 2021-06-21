package com.jiewen.coco.baseliabrary.rx

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/21/213:00 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
fun <T> Observable<T>.extcue(subscriber: BaseSubscriber<T>) {
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}