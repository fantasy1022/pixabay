package com.fantasy1022.pixabay.data


data class ImagesInfo(
    val total: Int,
    val totalHits: Int,
    val imagesDetailInfos: List<ImageDetailInfo>
) {
    data class ImageDetailInfo(
        val id: Long,
        val pageURL: String,
        val type: String,
        val tags: String,
        val previewURL: String,
        val previewWidth: String,
        val previewHeight: String,
        val webformatURL: String,
        val webformatWidth: Int,
        val webformatHeight: Int,
        val largeImageURL: String,
        val imageWidth: Int,
        val imageHeight: Int,
        val imageSize: Long,
        val views: Long,
        val downloads: Long,
        val favorites: Long,
        val likes: Long,
        val comments: Long,
        val user_id: Long,
        val user: String,
        val userImageURL: String
    )
}