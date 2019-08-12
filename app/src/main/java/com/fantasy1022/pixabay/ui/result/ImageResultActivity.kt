package com.fantasy1022.pixabay.ui.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItemsSingleChoice
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

    enum class LayoutManagerType(val value: Int) {
        STAGGER(0), GRID(1), LINEAR(2);

        companion object {
            fun toEnum(value: Int): LayoutManagerType = values().first { it.value == value }
        }
    }

    private lateinit var viewModel: ImageResultViewModel
    private lateinit var query: String
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var currentLayoutMangerType: LayoutManagerType
    private val staggeredGridLayoutManager by lazy {
        StaggeredGridLayoutManager(2, VERTICAL)
    }
    private val gridLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }
    private val linearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val styleList = listOf("Stagger", "Grid", "Linear")

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val styleView = menu.findItem(R.id.layoutStyle).actionView
        styleView.setOnClickListener {
            MaterialDialog(this).show {
                title(R.string.choose_style)
                //TODO: get initial selection
                listItemsSingleChoice(items = styleList, initialSelection = 1) { _, index, text ->
                    switchLayoutManager(LayoutManagerType.toEnum(index))
                }
                lifecycleOwner(this@ImageResultActivity)
            }

        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter(callback = this)
        with(imageRecyclerView) {
            adapter = imageAdapter
            layoutManager = staggeredGridLayoutManager
        }
    }

    private fun switchLayoutManager(currentLayoutMangerType: LayoutManagerType) {
        // If a layout manager has already been set, get current scroll position.
        var scrollPosition: IntArray = imageRecyclerView.layoutManager?.let {
            (it as StaggeredGridLayoutManager).findFirstCompletelyVisibleItemPositions(null)
            //TODO:Check other manager
        } ?: IntArray(2)

        val currentManager: RecyclerView.LayoutManager = when (currentLayoutMangerType) {
            LayoutManagerType.STAGGER -> staggeredGridLayoutManager
            LayoutManagerType.GRID -> gridLayoutManager
            LayoutManagerType.LINEAR -> linearLayoutManager
        }

        with(imageRecyclerView) {
            layoutManager = currentManager
            scrollToPosition(scrollPosition[0])
        }

    }

    override fun onClick(transitionData: ImageAdapter.ItemViewHolder.TransitionData) {

    }
}
