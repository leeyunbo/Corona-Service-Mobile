package com.leeyunbo.coronaboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leeyunbo.coronaboard.data.CoronaBoard
import com.leeyunbo.coronaboard.parser.parseHtmlTags
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var thread : Thread = Thread(Runnable {
            var coronaBoard : CoronaBoard = parseHtmlTags("http://ncov.mohw.go.kr/")
            println(coronaBoard.toString())
        })

        thread.start()
    }
}
