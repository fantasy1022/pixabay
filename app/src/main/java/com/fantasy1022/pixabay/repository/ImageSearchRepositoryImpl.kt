package com.fantasy1022.pixabay.repository

import com.fantasy1022.pixabay.data.ImagesInfo
import com.fantasy1022.pixabay.data.ImagesMapper

class ImageSearchRepositoryImpl(
    private val imageSearchApi: ImageSearchApi,
    private val imagesMapper: ImagesMapper
) : ImageSearchRepository {

    override suspend fun getImageSearch(key: String, query: String, imageType: String): ImagesInfo =
        imagesMapper.toImageInfo(imageSearchApi.getImages(key, query, imageType).await())

}