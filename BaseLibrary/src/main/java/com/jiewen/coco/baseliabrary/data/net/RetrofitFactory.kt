package com.jiewen.coco.baseliabrary.data.net

import android.util.Log
import com.jiewen.coco.baseliabrary.common.Constant
import com.jiewen.coco.baseliabrary.widget.PsqLogUtil
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException
import java.nio.charset.Charset

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 6/21/215:14 PM
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(initOkhttp())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message -> Log.i(Constant.TAG, message!!) }.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


     ;


    open class CustomInteceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request();//获取请求
            val url = request.url();
            val method = request.method();
            val t1: Long = System.nanoTime()
            val requestBody: RequestBody? = request.body();
            if (requestBody != null) {
                var sb = StringBuilder("Request Body [")
                var buffer = Buffer();
                requestBody.writeTo(buffer)
                var charset: Charset? = Charset.forName("UTF-8")
                val contentType: MediaType? = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(charset)
                }
                if (isPlaintext(buffer)) {
                    sb.append(buffer.readString(charset))
                    if (contentType != null) sb.append(" (Content-Type = ")
                        .append(contentType.toString())
                        .append(",")
                        .append(requestBody.contentLength()).append("-byte body)")
                } else {
                    if (contentType != null) sb.append(" (Content-Type = ")
                        .append(contentType.toString())
                        .append(",binary ").append(requestBody.contentLength())
                        .append("-byte body omitted)")
                }
                sb.append("]")
            }
            val response: Response = chain.proceed(request)
            val t2 = System.nanoTime()

            //the response data
            //the response data
            val body = response.body()
            val source = body!!.source()
            source.request(kotlin.Long.Companion.MAX_VALUE) // Buffer the entire body.

            val buffer = source.buffer()
            var charset = Charset.defaultCharset()
            val contentType = body!!.contentType()
            if (contentType != null) {
                charset = contentType!!.charset(charset)
            }

            var bodyString = buffer.clone().readString(charset)
            var str = String(bodyString.toByteArray(charset("UTF-8")))
            PsqLogUtil.e(
                ("请求地址：" + url + "   请求方法：" + method + "   响应耗时：" + ((t2 - t1) / 1e6
                        ) + "\r\n请求状态：" + (if (response.isSuccessful()) "success" else "fail") + response.message() + "   请求状态码：" + response.code()
                        + "\r\n响应实体：" + PsqLogUtil.decode(str))
            )
            return response


        }
        fun isPlaintext(buffer: Buffer): Boolean {
            return try {
                val prefix = Buffer()
                val byteCount = if (buffer.size() < 64) buffer.size() else 64
                buffer.copyTo(prefix, 0, byteCount)
                for (i in 0..15) {
                    if (prefix.exhausted()) {
                        break
                    }
                    val codePoint = prefix.readUtf8CodePoint()
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false
                    }
                }
                true
            } catch (e: EOFException) {
                false // Truncated UTF-8 sequence.
            }
        }

    }

    fun <T> creatApiService(service:Class<T>):T{
       return retrofit.create(service)
    }


}