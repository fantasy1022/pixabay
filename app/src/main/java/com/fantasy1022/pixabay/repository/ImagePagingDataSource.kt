package com.fantasy1022.pixabay.repository

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.fantasy1022.pixabay.data.ImagesInfo
import com.fantasy1022.pixabay.data.ImagesMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ImagePagingDataSource(
    private val imageSearchApi: ImageSearchApi,
    private val imagesMapper: ImagesMapper,
    private val key: String,
    private val query: String,
    private val imageType: String
) : PageKeyedDataSource<Int, ImagesInfo.ImageDetailInfo>(), CoroutineScope {

    private val tag = this::class.java.simpleName

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImagesInfo.ImageDetailInfo>
    ) {
        launch {
            //TODO: try catch
            val response = imageSearchApi.getImagesAsync(key, query, imageType, 1).await()
            if (response.isSuccessful) {
                val imagesInfo = imagesMapper.toImagesInfo(response.body()!!)
                callback.onResult(imagesInfo.imagesDetailInfos, null, 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImagesInfo.ImageDetailInfo>) {
        launch {
            var pageNum = params.key
            Log.d(tag, "loadAfter $pageNum")
            val response = imageSearchApi.getImagesAsync(key, query, imageType, pageNum).await()
            if (response.isSuccessful) {
                val imagesInfo = imagesMapper.toImagesInfo(response.body()!!)
                callback.onResult(imagesInfo.imagesDetailInfos, ++pageNum)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImagesInfo.ImageDetailInfo>) {
        //Do nothing
    }

}

class ImagePagingDataSourceFactory(
    private val imageSearchApi: ImageSearchApi,
    private val imagesMapper: ImagesMapper,
    private val key: String,
    private val query: String,
    private val imageType: String
) : DataSource.Factory<Int, ImagesInfo.ImageDetailInfo>() {

    override fun create(): DataSource<Int, ImagesInfo.ImageDetailInfo> {
        return ImagePagingDataSource(imageSearchApi, imagesMapper, key, query, imageType)
    }
}
