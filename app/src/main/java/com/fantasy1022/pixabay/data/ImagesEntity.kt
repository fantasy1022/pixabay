package com.fantasy1022.pixabay.data

import com.google.gson.annotations.SerializedName

data class ImagesEntity(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<ImageBean>
) {

    data class ImageBean(
        @SerializedName("id") val id: Long,
        @SerializedName("pageURL") val pageURL: String,
        @SerializedName("type") val type: String,
        @SerializedName("tags") val tags: String,
        @SerializedName("previewURL") val previewURL: String,
        @SerializedName("previewWidth") val previewWidth: String,
        @SerializedName("previewHeight") val previewHeight: String,
        @SerializedName("webformatURL") val webformatURL: String,
        @SerializedName("webformatWidth") val webformatWidth: Int,
        @SerializedName("webformatHeight") val webformatHeight: Int,
        @SerializedName("largeImageURL") val largeImageURL: String,
        @SerializedName("imageWidth") val imageWidth: Int,
        @SerializedName("imageHeight") val imageHeight: Int,
        @SerializedName("imageSize") val imageSize: Long,
        @SerializedName("views") val views: Long,
        @SerializedName("downloads") val downloads: Long,
        @SerializedName("favorites") val favorites: Long,
        @SerializedName("likes") val likes: Long,
        @SerializedName("comments") val comments: Long,
        @SerializedName("user_id") val user_id: Long,
        @SerializedName("user") val user: String,
        @SerializedName("userImageURL") val userImageURL: String
    )

}


