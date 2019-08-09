package com.fantasy1022.pixabay.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.utilities.InjectorUtils

class ImageSearchActivity : AppCompatActivity() {

    private lateinit var viewModel: ImageSerachViewModel
    //TODO: handle type
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val factory = InjectorUtils.provideImageSearchViewModel()
        viewModel = ViewModelProvider(this, factory).get(ImageSerachViewModel::class.java)
    }


}
