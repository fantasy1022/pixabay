package com.fantasy1022.pixabay.ui.main

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.ui.result.ImageResultActivity
import kotlinx.android.synthetic.main.activity_main.*

class ImageSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchImage.setOnClickListener {
            startActivity(ImageResultActivity.createIntent(this, searchEditText.text.toString().trim()))
        }

        searchEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchImage.callOnClick()
                true
            } else {
                false
            }
        }
    }

}
