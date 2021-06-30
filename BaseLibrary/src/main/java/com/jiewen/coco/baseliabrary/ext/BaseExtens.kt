package com.jiewen.coco.baseliabrary.ext

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jiewen.coco.baseliabrary.common.Constant

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/30/211:57 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */

//判断为空
fun  EditText.chechNull():Boolean{
    val text = this.text.toString()
    if (text.isBlank()){//null或是空或是空格
        Log.i(Constant.TAG,"输入为空")
        return false
    }
    return true
}

//比较字符串
fun EditText.compareText(pwd:String,comPwd:String){

}