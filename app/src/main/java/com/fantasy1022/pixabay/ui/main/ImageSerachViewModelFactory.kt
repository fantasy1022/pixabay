package com.fantasy1022.pixabay.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fantasy1022.pixabay.repository.ImageSearchRepository

class ImageSerachViewModelFactory(private val imageSearchRepository: ImageSearchRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageSerachViewModel(imageSearchRepository) as T
    }
}