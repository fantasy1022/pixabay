package com.fantasy1022.pixabay.data

import com.google.gson.annotations.SerializedName

data class ImagesEntity(@SerializedName("totalHits") val totalHits: Int) {

    data class ImageBean(@SerializedName("id") val id: Long)
    //TODO
}