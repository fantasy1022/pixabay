package com.fantasy1022.pixabay.common

import com.fantasy1022.pixabay.BuildConfig

object Constant {
    const val PIXABAY_BASE_URL = "https://pixabay.com/api/"
    const val PIXABAY_API_KEY = BuildConfig.PixabayApiKey

    const val BASE_STAGGER_IMAGE_HEIGHT = 120f //dp
    const val BASE_GRID_LINEAR_IMAGE_HEIGHT = 150f //dp

    const val PAGE_SIZE = 20
}