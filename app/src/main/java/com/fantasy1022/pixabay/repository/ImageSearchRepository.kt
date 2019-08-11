package com.fantasy1022.pixabay.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.fantasy1022.pixabay.data.ImagesInfo

interface ImageSearchRepository {

    @Throws(Exception::class)
    fun getImageSearch(key: String, query: String, imageType: String): LiveData<PagedList<ImagesInfo.ImageDetailInfo>>
}