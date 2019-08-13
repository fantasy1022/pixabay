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
import com.fantasy1022.pixabay.utilities.SpUtils
import kotlinx.android.synthetic.main.activity_image_result.*
import kotlinx.android.synthetic.main.content_image_result.*

class ImageResultActivity : AppCompatActivity(), ImageAdapter.Callback {

    companion object {
        private const val KEY_ARG_QUERY = "KEY_ARG_QUERY"
        private const val KEY_SP_LAYOUT = "KEY_SP_LAYOUT"

        fun createIntent(context: Context, query: String): Intent {
            return Intent(context, ImageResultActivity::class.java).apply {
                putExtra(KEY_ARG_QUERY, query)
            }
        }
    }

    enum class LayoutManagerType(val value: Int) {
        STAGGER(0), GRID(1), LINEAR(2);
    }

    private lateinit var viewModel: ImageResultViewModel
    private lateinit var query: String
    private lateinit var imageAdapter: ImageAdapter
    private var currentLayoutMangerType: Int = 0
    private lateinit var spUtils: SpUtils

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
        initToolBar()
        setUpRecyclerView()
        initViewModel()
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
                listItemsSingleChoice(
                    items = styleList,
                    initialSelection = currentLayoutMangerType
                ) { _, index, _ ->
                    switchLayoutManager(index)
                }
                lifecycleOwner(this@ImageResultActivity)
            }

        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun initToolBar() {
        query = intent.getStringExtra(KEY_ARG_QUERY)
        toolbar.title = query
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu_layout)
    }

    private fun setUpRecyclerView() {
        spUtils = SpUtils(this)
        currentLayoutMangerType = spUtils.getInt(KEY_SP_LAYOUT, 0)
        imageAdapter = ImageAdapter(callback = this)
        imageRecyclerView.adapter = imageAdapter
        switchLayoutManager(currentLayoutMangerType)
    }

    private fun initViewModel() {
        val factory = InjectorUtils.provideImageResultViewModel()
        viewModel = ViewModelProvider(this, factory).get(ImageResultViewModel::class.java)
        viewModel.getSearchImages(query).observe(this, Observer { imagesInfo ->
            imageAdapter.submitList(imagesInfo)
        })
    }

    private fun switchLayoutManager(layoutIndex: Int) {
        imageAdapter.isDynamicHeight = layoutIndex == LayoutManagerType.STAGGER.value

        val currentManager: RecyclerView.LayoutManager = when (layoutIndex) {
            LayoutManagerType.STAGGER.value -> staggeredGridLayoutManager
            LayoutManagerType.GRID.value -> gridLayoutManager
            LayoutManagerType.LINEAR.value -> linearLayoutManager
            else -> throw IllegalArgumentException()
        }

        //Use originally index to get previous position of layout manager
        val scrollPosition: Int = when (currentLayoutMangerType) {
            LayoutManagerType.STAGGER.value -> {
                imageRecyclerView.layoutManager?.let {
                    (it as StaggeredGridLayoutManager).findFirstCompletelyVisibleItemPositions(null)[0]
                } ?: 0
            }
            LayoutManagerType.GRID.value -> {
                imageRecyclerView.layoutManager?.let {
                    (it as GridLayoutManager).findFirstVisibleItemPosition()
                } ?: 0
            }
            LayoutManagerType.LINEAR.value -> {
                imageRecyclerView.layoutManager?.let {
                    (it as LinearLayoutManager).findFirstVisibleItemPosition()
                } ?: 0
            }
            else -> throw IllegalArgumentException()
        }

        currentLayoutMangerType = layoutIndex
        spUtils.putInt(KEY_SP_LAYOUT, layoutIndex)

        with(imageRecyclerView) {
            layoutManager = currentManager
            scrollToPosition(scrollPosition)
            imageAdapter.notifyDataSetChanged()
        }
    }

    override fun onClick(transitionData: TransitionData) {

    }
}
