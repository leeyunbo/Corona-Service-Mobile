package com.leeyunbo.coronaboard.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leeyunbo.coronaboard.data.CoronaBoard
import com.leeyunbo.coronaboard.data.MainModel
import com.leeyunbo.coronaboard.data.Model
import java.util.*

class MainViewModel : ViewModel {
    val coronaBoard = ObservableField<CoronaBoard>()
    override val model: Model = MainModel()

    override fun doAction() {
        model.getData(this)
    }

    fun completeGetData(_coronaBoard: CoronaBoard) {
        coronaBoard.set(_coronaBoard)
        System.out.println(coronaBoard.get().toString())
    }
}