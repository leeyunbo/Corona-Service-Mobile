package com.leeyunbo.coronaboard.viewmodel

import com.leeyunbo.coronaboard.data.Model

interface ViewModel {
    val model : Model
    fun doAction()
}