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
    lateinit var todayConfirmer: String
    lateinit var todayHealer: String
    lateinit var confirmer: String
    lateinit var healer: String
    lateinit var healing: String
    lateinit var dead: String
    lateinit var addHealerCnt: String
    lateinit var addHealingCnt: String
    lateinit var addConfirmerCnt: String
    lateinit var addDeadCnt: String

    sc.init(null, trustAllCerts, SecureRandom())
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
    try {
        val doc: Document = Jsoup.connect(url).get()
        val today: Elements = doc.select("ul.liveNum_today li")
        val every: Elements = doc.select("div.liveNum ul.liveNum li")

        today.forEachIndexed { index, element ->
            var data = element.select("span.data${index+1}").text()
            if(index==1) todayConfirmer = "일일 확진자 ${data}"
            else todayHealer = "일일 완치자 ${data}"
        }

        every.forEachIndexed { index, element ->
            var num = element.select("span.num").text().replace("(누적)","")
            var before = element.select("span.before").text().replace("전일대비","").replace(" ","")
            when(index) {
                0 -> {
                    confirmer = "확진환자\n${num}\n${before}"
                    addConfirmerCnt = before
                }
                1 -> {
                    healer = "완치(격리해제)\n${num}\n${before}"
                    addHealerCnt = before
                }
                2 -> {
                    healing = "치료중(격리 중)\n${num}\n${before}"
                    addHealingCnt = before
                }
                3 -> {
                    dead = "사망\n${num}\n${before}"
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