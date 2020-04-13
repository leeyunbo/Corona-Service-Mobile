package com.leeyunbo.coronaboard.data

import com.leeyunbo.coronaboard.viewmodel.ViewModel

interface Model {
    var viewModel : ViewModel
    fun getData(viewModel : ViewModel)
}