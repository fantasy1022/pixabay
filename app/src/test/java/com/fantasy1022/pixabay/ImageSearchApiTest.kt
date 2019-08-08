package com.fantasy1022.pixabay

import com.fantasy1022.pixabay.repository.ImageSearchApi
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageSearchApiTest {


    val API_KEY: String = BuildConfig.PixabayApiKey

    @Throws(Exception::class)
    @Test
    fun test_GetImageSearchApi_OK() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ImageSearchApi::class.java)
        val call = api.getImages(API_KEY, "app", "photo")

        val response = call.execute()
        Assert.assertNotNull(response)
        Assert.assertTrue("Error response: http code = " + response.code(), response.isSuccessful)

        val imagesEntity = response.body()
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