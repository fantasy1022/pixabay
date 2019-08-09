package com.fantasy1022.pixabay

import com.fantasy1022.pixabay.repository.ImageSearchApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageSearchApiTest {

    @Throws(Exception::class)
    @Test
    fun test_GetImageSearchApi_OK() = runBlocking {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val inputQuery = "app"
        val api = retrofit.create(ImageSearchApi::class.java)
        val call = async { api.getImagesAsync(BuildConfig.PixabayApiKey, inputQuery, "photo") }
        val imagesEntity = call.await()

        assertNotNull(imagesEntity)
        assertNotNull(imagesEntity!!.total)
        assertNotNull(imagesEntity!!.totalHits)
        imagesEntity!!.hits.forEach { imageBean ->
            assertNotNull(imageBean.id)
            assertNotNull(imageBean.pageURL)
            assertNotNull(imageBean.type)
            assertNotNull(imageBean.tags)
            assertNotNull(imageBean.previewURL)
            assertNotNull(imageBean.previewWidth)
            assertNotNull(imageBean.previewHeight)
            assertNotNull(imageBean.webformatURL)
            assertNotNull(imageBean.webformatWidth)
            assertNotNull(imageBean.webformatHeight)
            assertNotNull(imageBean.largeImageURL)
            assertNotNull(imageBean.imageWidth)
            assertNotNull(imageBean.imageHeight)
            assertNotNull(imageBean.imageSize)
            assertNotNull(imageBean.downloads)
            assertNotNull(imageBean.favorites)
            assertNotNull(imageBean.likes)
            assertNotNull(imageBean.comments)
            assertNotNull(imageBean.user_id)
            assertNotNull(imageBean.user)
            assertNotNull(imageBean.userImageURL)
        }
    }

}