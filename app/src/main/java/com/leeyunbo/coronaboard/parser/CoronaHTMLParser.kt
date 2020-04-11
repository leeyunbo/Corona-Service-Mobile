package com.leeyunbo.coronaboard.parser

import android.util.Log
import com.leeyunbo.coronaboard.data.CoronaBoard
import org.jsoup.*
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

var trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
    override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
})


fun parseHtmlTags(url : String) : CoronaBoard {
    val sc : SSLContext = SSLContext.getInstance("SSL")
    var todayConfirmer: Int = 0
    var todayHealer: Int = 0
    var confirmer: Int = 0
    var healer: Int = 0
    var healing: Int = 0
    var dead: Int = 0
    var addHealerCnt: String = ""
    var addHealingCnt: String = ""
    var addConfirmerCnt: String = ""
    var addDeadCnt: String = ""

    sc.init(null, trustAllCerts, SecureRandom())
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
    try {
        val doc: Document = Jsoup.connect(url).get()
        val today: Elements = doc.select("ul.liveNum_today li")
        val every: Elements = doc.select("div.liveNum ul.liveNum li")

        today.forEachIndexed { index, element ->
            var data = element.select("span.data${index+1}").text().toInt()
            if(index==1) todayConfirmer = data
            else todayHealer = data
        }

        every.forEachIndexed { index, element ->
            var num = element.select("span.num").text().toInt()
            var before = element.select("span.before").text()
            when(index) {
                0 -> {
                    confirmer = num
                    addConfirmerCnt = before
                }
                1 -> {
                    healer = num
                    addHealerCnt = before
                }
                2 -> {
                    healing = num
                    addHealingCnt = before
                }
                3 -> {
                    dead = num
                    addDeadCnt = before
                }
            }
        }

    } catch(e: IOException) {
        Log.e("IOException","MetaDataPraser.getMetadata()")
        e.printStackTrace()
    } catch(e:IllegalAccessException) {
        Log.e("IllegalAccessException","MetaDataParser.getMetaData()")
        e.printStackTrace()
    } catch(e: HttpStatusException) {
        Log.e("HttpStatusException", "MetaDataParser.getMetadata()")
        e.printStackTrace()
    }
    return CoronaBoard(todayConfirmer,todayHealer,confirmer,healer,healing,dead,addConfirmerCnt,addHealerCnt,addHealingCnt,addDeadCnt)
}