package com.fantasy1022.pixabay.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fantasy1022.pixabay.common.Constant
import com.fantasy1022.pixabay.data.ImagesInfo
import com.fantasy1022.pixabay.data.ImagesMapper

class ImageSearchRepositoryImpl(
    private val imageSearchApi: ImageSearchApi,
    private val imagesMapper: ImagesMapper

) : ImageSearchRepository {

    override fun getImageSearch(
        key: String,
        query: String,
        imageType: String
    ): LiveData<PagedList<ImagesInfo.ImageDetailInfo>> {

        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(Constant.PAGE_SIZE)
            .setInitialLoadSizeHint(Constant.PAGE_SIZE * 2)
            .setPrefetchDistance(4)
            .build()

        val imagePagingDataSourceFactory =
            ImagePagingDataSourceFactory(imageSearchApi, imagesMapper, key, query, imageType)

        return LivePagedListBuilder(imagePagingDataSourceFactory, pagedListConfig)
            .setInitialLoadKey(1)
            .build()
    }

}