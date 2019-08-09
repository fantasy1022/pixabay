package com.fantasy1022.pixabay.utilities

import com.fantasy1022.pixabay.common.Constant
import com.fantasy1022.pixabay.data.ImagesMapper
import com.fantasy1022.pixabay.repository.ImageSearchApi
import com.fantasy1022.pixabay.repository.ImageSearchRepository
import com.fantasy1022.pixabay.repository.ImageSearchRepositoryImpl
import com.fantasy1022.pixabay.ui.result.ImageResultViewModelFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {
    private const val BASE_URL = Constant.PIXABAY_BASE_URL

    fun provideImageResultViewModel(): ImageResultViewModelFactory {
        return ImageResultViewModelFactory(provideImageSearchRepository())
    }

    private fun provideImageSearchRepository(): ImageSearchRepository {
        return ImageSearchRepositoryImpl(provideImageSearchApi(), ImagesMapper)
    }

    private fun provideImageSearchApi(): ImageSearchApi {
        return provideRetrofit().create(ImageSearchApi::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().client(provideOkHttpClient()).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }


    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}