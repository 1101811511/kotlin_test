package com.jiewen.coco.baseliabrary.widget

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.annotation.IntDef
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

class PsqLogUtil private constructor() {
    @IntDef(V, D, I, W, E, A)
    @Retention(RetentionPolicy.SOURCE)
    annotation class TYPE
    class Builder(context: Context) {
        fun setGlobalTag(tag: String): Builder {
            if (!isSpace(tag)) {
               sGlobalTag = tag
                sTagIsSpace = false
            } else {
               sGlobalTag = ""
                sTagIsSpace = true
            }
            return this
        }

        fun setLogSwitch(logSwitch: Boolean): Builder {
            sLogSwitch = logSwitch
            return this
        }

        fun setLog2FileSwitch(log2FileSwitch: Boolean): Builder {
         sLog2FileSwitch = log2FileSwitch
            return this
        }

        fun setBorderSwitch(borderSwitch: Boolean): Builder {
         sLogBorderSwitch = borderSwitch
            return this
        }

        fun setLogFilter(@TYPE logFilter: Int): Builder {
           sLogFilter = logFilter
            return this
        }

        init {
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
                dir = context.externalCacheDir.toString() + File.separator + "log" + File.separator
            } else {
                dir = context.cacheDir.toString() + File.separator + "log" + File.separator
            }
        }
    }

    companion object {
        const val V = 0x01
        const val D = 0x02
        const val I = 0x04
        const val W = 0x08
        const val E = 0x10
        const val A = 0x20
        private const val FILE = 0xF1
        private const val JSON = 0xF2
        private const val XML = 0xF4
        private var dir // log存储目录
                : String? = null
        private var sLogSwitch = true // log总开关
        private var sGlobalTag: String? = null // log标签
        private var sTagIsSpace = true // log标签是否为空白
        private var sLog2FileSwitch = false // log写入文件开关
        private var sLogBorderSwitch = true // log边框开关
        private var sLogFilter = V // log过滤器
        private const val TOP_BORDER =
            "╔═══════════════════════════════════════════════════════════════════════════════════════════════════"
        private const val LEFT_BORDER = "║ "
        private const val BOTTOM_BORDER =
            "╚═══════════════════════════════════════════════════════════════════════════════════════════════════"
        private val LINE_SEPARATOR = System.getProperty("line.separator")
        private const val MAX_LEN = 4000
        private const val NULL_TIPS = "Log with null object."
        private const val NULL = "null"
        private const val ARGS = "args"
        fun v(contents: Any?) {
            log(V, sGlobalTag, contents!!)
        }

        fun v(tag: String?, vararg contents: Any?) {
            log(V, tag, *contents as Array<out Any>)
        }

        fun d(contents: Any?) {
            log(D, sGlobalTag, contents!!)
        }

        fun d(tag: String?, vararg contents: Any?) {
            log(D, tag, *contents as Array<out Any>)
        }

        fun i(contents: Any?) {
            log(I, sGlobalTag, contents!!)
        }

        fun i(tag: String?, vararg contents: Any?) {
            log(I, tag, *contents as Array<out Any>)
        }

        fun w(contents: Any?) {
            log(W, sGlobalTag, contents!!)
        }

        fun w(tag: String?, vararg contents: Any?) {
            log(W, tag, *contents as Array<out Any>)
        }

        fun e(contents: Any?) {
            log(E, sGlobalTag, contents!!)
        }

        fun e(tag: String?, vararg contents: Any?) {
            log(E, tag, *contents as Array<out Any>)
        }

        fun a(contents: Any?) {
            log(A, sGlobalTag, contents!!)
        }

        fun a(tag: String?, vararg contents: Any?) {
            log(A, tag, *contents as Array<out Any>)
        }

        fun file(contents: Any?) {
            log(FILE, sGlobalTag, contents!!)
        }

        fun file(tag: String?, contents: Any?) {
            log(FILE, tag, contents!!)
        }

        fun json(contents: String?) {
            log(JSON, sGlobalTag, contents!!)
        }

        fun json(tag: String?, contents: String?) {
            log(JSON, tag, contents!!)
        }

        fun xml(contents: String?) {
            log(XML, sGlobalTag, contents!!)
        }

        fun xml(tag: String?, contents: String?) {
            log(XML, tag, contents!!)
        }

        private fun log(type: Int, tag: String?, vararg contents: Any) {
            var tag = tag
            if (!sLogSwitch) return
            val processContents = processContents(type, tag, *contents)
            tag = processContents[0]
            val msg = processContents[1]
            when (type) {
                V, D, I, W, E, A -> {
                    if (V == sLogFilter || type >= sLogFilter) {
                        printLog(type, tag, msg)
                    }
                    if (sLog2FileSwitch) {
                        print2File(tag, msg)
                    }
                }
                FILE -> print2File(tag, msg)
                JSON -> printLog(D, tag, msg)
                XML -> printLog(D, tag, msg)
            }
        }

        private fun processContents(type: Int, tag: String?, vararg contents: Any): Array<String?> {
            var tag = tag
            val targetElement = Thread.currentThread().stackTrace[5]
            var className = targetElement.className
            val classNameInfo = className.split("\\.").toTypedArray()
            if (classNameInfo.size > 0) {
                className = classNameInfo[classNameInfo.size - 1]
            }
            if (className.contains("$")) {
                className = className.split("\\$").toTypedArray()[0]
            }
            tag = if (!sTagIsSpace) { // 如果全局tag不为空，那就用全局tag
                sGlobalTag
            } else { // 全局tag为空时，如果传入的tag为空那就显示类名，否则显示tag
                if (isSpace(tag)) className else tag
            }
            val head = Formatter()
                .format(
                    "Thread: %s, %s(%s.java:%d)" + LINE_SEPARATOR,
                    Thread.currentThread().name,
                    targetElement.methodName,
                    className,
                    targetElement.lineNumber
                )
                .toString()
            var msg = NULL_TIPS
            if (contents != null) {
                if (contents.size == 1) {
                    val `object` = contents[0]
                    msg = `object`?.toString() ?: NULL
                    if (type == JSON) {
                        msg = formatJson(msg)
                    } else if (type == XML) {
                        msg = formatXml(msg)
                    }
                } else {
                    val sb = StringBuilder()
                    var i = 0
                    val len = contents.size
                    while (i < len) {
                        val content = contents[i]
                        sb.append(ARGS)
                            .append("[")
                            .append(i)
                            .append("]")
                            .append(" = ")
                            .append(content?.toString() ?: NULL)
                            .append(LINE_SEPARATOR)
                        ++i
                    }
                    msg = sb.toString()
                }
            }
            if (sLogBorderSwitch) {
                val sb = StringBuilder()
                val lines = msg.split(LINE_SEPARATOR).toTypedArray()
                for (line in lines) {
                    sb.append(LEFT_BORDER).append(line).append(LINE_SEPARATOR)
                }
                msg = sb.toString()
            }
            return arrayOf(tag, head + msg)
        }

        private fun formatJson(json: String): String {
            var json = json
            try {
                if (json.startsWith("{")) {
                    json = JSONObject(json).toString(4)
                } else if (json.startsWith("[")) {
                    json = JSONArray(json).toString(4)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return json
        }

        private fun formatXml(xml: String): String {
            var xml = xml
            try {
                val xmlInput: Source = StreamSource(StringReader(xml))
                val xmlOutput = StreamResult(StringWriter())
                val transformer = TransformerFactory.newInstance().newTransformer()
                transformer.setOutputProperty(OutputKeys.INDENT, "yes")
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
                transformer.transform(xmlInput, xmlOutput)
                xml = xmlOutput.writer.toString().replaceFirst(">".toRegex(), ">" + LINE_SEPARATOR)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return xml
        }

        private fun printLog(type: Int, tag: String?, msg: String?) {
            if (sLogBorderSwitch) printBorder(type, tag, true)
            val len = msg!!.length
            val countOfSub = len / MAX_LEN
            if (countOfSub > 0) {
                var index = 0
                var sub: String
                for (i in 0 until countOfSub) {
                    sub = msg.substring(index, index + MAX_LEN)
                    printSubLog(type, tag, sub)
                    index += MAX_LEN
                }
                printSubLog(type, tag, msg.substring(index, len))
            } else {
                printSubLog(type, tag, msg)
            }
            if (sLogBorderSwitch) printBorder(type, tag, false)
        }

        private fun printSubLog(type: Int, tag: String?, msg: String?) {
            var msg = msg
            if (sLogBorderSwitch) msg = LEFT_BORDER + msg
            when (type) {
                V -> Log.v(tag, msg!!)
                D -> Log.d(tag, msg!!)
                I -> Log.i(tag, msg!!)
                W -> Log.w(tag, msg!!)
                E -> Log.e(tag, msg!!)
                A -> Log.wtf(tag, msg)
            }
        }

        private fun printBorder(type: Int, tag: String?, isTop: Boolean) {
            val border = if (isTop) TOP_BORDER else BOTTOM_BORDER
            when (type) {
                V -> Log.v(tag, border)
                D -> Log.d(tag, border)
                I -> Log.i(tag, border)
                W -> Log.w(tag, border)
                E -> Log.e(tag, border)
                A -> Log.wtf(tag, border)
            }
        }

        @Synchronized
        private fun print2File(tag: String?, msg: String?) {
            val now = Date()
            val date = SimpleDateFormat("MM-dd", Locale.getDefault()).format(now)
            val fullPath = dir + date + ".txt"
            if (!createOrExistsFile(fullPath)) {
                Log.e(tag, "log to $fullPath failed!")
                return
            }
            val time = SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault()).format(now)
            val sb = StringBuilder()
            if (sLogBorderSwitch) sb.append(TOP_BORDER).append(LINE_SEPARATOR)
            sb.append(time)
                .append(tag)
                .append(": ")
                .append(msg)
                .append(LINE_SEPARATOR)
            if (sLogBorderSwitch) sb.append(BOTTOM_BORDER).append(LINE_SEPARATOR)
            val dateLogContent = sb.toString()
            Thread {
                var bw: BufferedWriter? = null
                try {
                    bw = BufferedWriter(FileWriter(fullPath, true))
                    bw.write(dateLogContent)
                    Log.d(tag, "log to $fullPath success!")
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e(tag, "log to $fullPath failed!")
                } finally {
                    try {
                        bw?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        private fun createOrExistsFile(filePath: String): Boolean {
            return createOrExistsFile(if (isSpace(filePath)) null else File(filePath))
        }

        private fun createOrExistsFile(file: File?): Boolean {
            if (file == null) return false
            if (file.exists()) return file.isFile
            return if (!createOrExistsDir(file.parentFile)) false else try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }

        fun decode(unicodeStr: String?): String? {
            if (unicodeStr == null) {
                return null
            }
            val retBuf = StringBuffer()
            val maxLoop = unicodeStr.length
            var i = 0
            while (i < maxLoop) {
                if (unicodeStr[i] == '\\') {
                    if (i < maxLoop - 5 && (unicodeStr[i + 1] == 'u' || unicodeStr[i + 1] == 'U')) try {
                        retBuf.append(unicodeStr.substring(i + 2, i + 6).toInt(16).toChar())
                        i += 5
                    } catch (localNumberFormatException: NumberFormatException) {
                        retBuf.append(unicodeStr[i])
                    } else retBuf.append(unicodeStr[i])
                } else {
                    retBuf.append(unicodeStr[i])
                }
                i++
            }
            return retBuf.toString()
        }

        private fun createOrExistsDir(file: File?): Boolean {
            return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}