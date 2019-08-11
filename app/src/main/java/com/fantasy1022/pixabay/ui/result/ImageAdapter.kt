package com.fantasy1022.pixabay.ui.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.common.Constant
import com.fantasy1022.pixabay.data.ImagesInfo.ImageDetailInfo
import com.fantasy1022.pixabay.utilities.convertDpToPixel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image_grid.*


class ImageAdapter(
    private val callback: Callback
) : PagedListAdapter<ImageDetailInfo, ImageAdapter.ItemViewHolder>(diffCallback) {

    interface Callback : ItemViewHolder.OnItemClickListener

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ImageDetailInfo>() {
            override fun areItemsTheSame(oldItem: ImageDetailInfo, newItem: ImageDetailInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageDetailInfo, newItem: ImageDetailInfo): Boolean {
                return oldItem.webformatURL == newItem.webformatURL
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_grid, parent, false)
        return ItemViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { holder.updateContent(it) }
    }

    class ItemViewHolder(
        itemView: View,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View? = itemView

        data class TransitionData(
            val imageDetailInfo: ImageDetailInfo,
            val position: Int,
            val sharedElements: Array<Pair<View, String>>
        )

        interface OnItemClickListener {
            fun onClick(transitionData: TransitionData)
        }

        fun updateContent(imageDetailInfo: ImageDetailInfo) {
            imageSingleView.layoutParams.height = getImageHeightByRatio(imageDetailInfo.imageRatio)
            imageSingleView.setImageURI(imageDetailInfo.webformatURL)
        }

        private fun getImageHeightByRatio(ratio: Float): Int =
            (Constant.BASE_IMAGE_HEIGHT.convertDpToPixel(itemView.context) * (if (ratio < 1) 1f else ratio)).toInt()

    }
}