package com.leeyunbo.coronaboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leeyunbo.coronaboard.data.CoronaBoard
import com.leeyunbo.coronaboard.data.MainModel
import com.leeyunbo.coronaboard.data.Model

class MainViewModel : ViewModel {
    val coronaBoard = MutableLiveData<CoronaBoard>()
    override val model: Model = MainModel()

    override fun doAction() {
        model.getData(this)
    }

    fun completeGetData(_coronaBoard: CoronaBoard) {
        coronaBoard.postValue(_coronaBoard)
        System.out.println(coronaBoard.value.toString())
    }
}