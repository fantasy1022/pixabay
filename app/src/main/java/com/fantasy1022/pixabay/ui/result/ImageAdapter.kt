package com.fantasy1022.pixabay.ui.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.fantasy1022.pixabay.R
import com.fantasy1022.pixabay.data.ImagesInfo.ImageDetailInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image_grid.*

class ImageAdapter(
    private var imageDetailInfos: List<ImageDetailInfo> = mutableListOf(),
    private val callback: Callback
) : RecyclerView.Adapter<ImageAdapter.ItemViewHolder>() {

    interface Callback : ItemViewHolder.OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_grid, parent, false)
        return ItemViewHolder(view, callback)
    }

    override fun getItemCount(): Int = imageDetailInfos.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.updateContent(imageDetailInfos[position])
    }

    class ItemViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) :
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
            imageSingleView.setImageURI(imageDetailInfo.previewURL)
        }
    }
}