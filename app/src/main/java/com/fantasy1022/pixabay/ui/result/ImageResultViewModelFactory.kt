package com.fantasy1022.pixabay.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fantasy1022.pixabay.repository.ImageSearchRepository

class ImageResultViewModelFactory (private val imageSearchRepository: ImageSearchRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageResultViewModel(imageSearchRepository) as T
    }
}