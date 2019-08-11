package com.fantasy1022.pixabay.repository

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.fantasy1022.pixabay.common.Constant
import com.fantasy1022.pixabay.data.ImagesInfo
import com.fantasy1022.pixabay.data.ImagesMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ImagePagingDataSource(
    private val imageSearchApi: ImageSearchApi,
    private val imagesMapper: ImagesMapper
) : PageKeyedDataSource<Int, ImagesInfo.ImageDetailInfo>(), CoroutineScope {

    private val tag = this::class.java.simpleName

    lateinit var key: String
    var query: String = "apple"
    lateinit var imageType: String

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImagesInfo.ImageDetailInfo>
    ) {
        launch {
            //TODO: try catch
            //FIXME: get parameter
            val response = imageSearchApi.getImagesAsync(Constant.PIXABAY_API_KEY, query, "photo", 1).await()
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
            val response = imageSearchApi.getImagesAsync(Constant.PIXABAY_API_KEY, query, "photo", pageNum).await()
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
    private val imagesMapper: ImagesMapper
) : DataSource.Factory<Int, ImagesInfo.ImageDetailInfo>() {
    lateinit var key: String
    lateinit var query: String
    lateinit var imageType: String

    override fun create(): DataSource<Int, ImagesInfo.ImageDetailInfo> {
        return ImagePagingDataSource(imageSearchApi, imagesMapper).apply {
            //            this.key = key
//            this.query = query
//            this.imageType = imageType
        }
    }
}