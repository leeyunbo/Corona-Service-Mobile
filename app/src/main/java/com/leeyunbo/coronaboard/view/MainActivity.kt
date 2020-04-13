package com.leeyunbo.coronaboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.leeyunbo.coronaboard.R
import com.leeyunbo.coronaboard.data.CoronaBoard
import com.leeyunbo.coronaboard.databinding.ActivityMainBinding
import com.leeyunbo.coronaboard.parser.parseHtmlTags
import com.leeyunbo.coronaboard.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        MainViewModel()
    }
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.apply {
            vm = viewModel
        }

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                viewModel.doAction()
            }.await()
        }
    }
}
