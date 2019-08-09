package com.fantasy1022.pixabay.repository

import com.fantasy1022.pixabay.data.ImagesEntity
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImageSearchApi {

    /**
     * Ref:https://pixabay.com/api/docs/#api_search_images
     */

    @Headers("Accept: application/json")
    @GET(".")
    fun getImages(@Query("key") key: String, @Query("q") query: String, @Query("image_type") imageType: String): Deferred<ImagesEntity>


}