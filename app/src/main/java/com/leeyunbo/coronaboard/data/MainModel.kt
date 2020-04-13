package com.leeyunbo.coronaboard.data

import com.leeyunbo.coronaboard.parser.parseHtmlTags
import com.leeyunbo.coronaboard.viewmodel.MainViewModel
import com.leeyunbo.coronaboard.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MainModel : Model {
    override lateinit var viewModel: ViewModel

    override fun getData(viewModel: ViewModel) {
        lateinit var coronaBoard : CoronaBoard
        this.viewModel = viewModel as MainViewModel

        coronaBoard = parseHtmlTags("http://ncov.mohw.go.kr/")
        viewModel.completeGetData(coronaBoard)

    }
}