package com.fantasy1022.pixabay.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.fantasy1022.pixabay.common.Constant
import com.fantasy1022.pixabay.data.ImagesInfo
import com.fantasy1022.pixabay.repository.ImageSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageResultViewModel(private val imageSearchRepository: ImageSearchRepository) : ViewModel() {

    @Throws(Exception::class)
    fun getSearchImages(query: String): LiveData<PagedList<ImagesInfo.ImageDetailInfo>> =
        imageSearchRepository.getImageSearch(Constant.PIXABAY_API_KEY, query, "photo")

}