package com.fantasy1022.pixabay.repository

import com.fantasy1022.pixabay.data.ImagesInfo

interface ImageSearchRepository {

    @Throws(Exception::class)
    suspend fun getImageSearch(key: String, query: String, imageType: String): ImagesInfo
}