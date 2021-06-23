package com.jiewen.coco.baseliabrary.data.protocol

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/23/219:17 AM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class BaseResponse<T>(val errorCode:Int,val errorMsg:String,val data:T)