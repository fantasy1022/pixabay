package com.fantasy1022.pixabay.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        viewModel = ViewModelProvider(this, factory).get(ImageSerachViewModel::class.java)

        searchImage.setOnClickListener {


            viewModel.getSearchImages(searchEditText.text.toString()).observe(this, Observer {
                imagesInfo->  Log.i("Fan",imagesInfo.toString())
                //TODO: to new activity
            })
        }
    }


}
