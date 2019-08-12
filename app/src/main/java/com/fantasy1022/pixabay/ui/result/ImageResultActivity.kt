package com.fantasy1022.pixabay.ui.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_image_result.*
import kotlinx.android.synthetic.main.content_image_result.*

class ImageResultActivity : AppCompatActivity(), ImageAdapter.Callback {

    companion object {
        private const val KEY_ARG_QUERY = "KEY_ARG_QUERY"

        fun createIntent(context: Context, query: String): Intent {
            return Intent(context, ImageResultActivity::class.java).apply {
                putExtra(KEY_ARG_QUERY, query)
            }
        }
    }

    private lateinit var viewModel: ImageResultViewModel
    private lateinit var query: String
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_result)
        query = intent.getStringExtra(KEY_ARG_QUERY)
        toolbar.title = query
        setSupportActionBar(toolbar)

        toolbar.inflateMenu(R.menu.menu_layout)
        setUpRecyclerView()

        val factory = InjectorUtils.provideImageResultViewModel()
        viewModel = ViewModelProvider(this, factory).get(ImageResultViewModel::class.java)
        viewModel.getSearchImages(query).observe(this, Observer { imagesInfo ->
            imageAdapter.submitList(imagesInfo)
        })
    }

    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter(callback = this)
        imageRecyclerView.adapter = imageAdapter
    }

    override fun onClick(transitionData: ImageAdapter.ItemViewHolder.TransitionData) {

    }
}
