package com.fantasy1022.pixabay.ui.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.utilities.InjectorUtils

import kotlinx.android.synthetic.main.activity_image_result.*


class ImageResultActivity : AppCompatActivity() {

    companion object {
        private const val KEY_ARG_QUERY = "_KEY_ARG_QUERY"

        fun createIntent(context: Context, query: String): Intent {
            return Intent(context, ImageResultActivity::class.java).apply {
                putExtra(KEY_ARG_QUERY, query)
            }
        }
    }

    private lateinit var viewModel: ImageResultViewModel
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_result)
        setSupportActionBar(toolbar)
        query = intent.getStringExtra(KEY_ARG_QUERY)


        val factory = InjectorUtils.provideImageResultViewModel()
        viewModel = ViewModelProvider(this, factory).get(ImageResultViewModel::class.java)
        viewModel.getSearchImages(query).observe(this, Observer { imagesInfo ->
            Log.i("Fan", imagesInfo.toString())
            //TODO: to new activity
        })

    }

}
