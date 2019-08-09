package com.fantasy1022.pixabay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class ImageSearchActivity : AppCompatActivity() {

    private lateinit var viewModel: ImageSerachViewModel
    //TODO: handle type
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val factory = InjectorUtils.provideImageSearchViewModel()
        //ViewModelProviders.of(this, factory).get(ImageSerachViewModel::class.java)
        viewModel = ViewModelProvider(this, factory).get(ImageSerachViewModel::class.java)
        viewModel = ViewModelProvider(this, factory).get(ImageSerachViewModel::class.java)


        testBtn.setOnClickListener {
            viewModel.getSearchImages("app").observe(this, Observer { imagesInfo ->
                Log.d("Fan", "${imagesInfo.totalHits}")
            })
        }
    }


}
