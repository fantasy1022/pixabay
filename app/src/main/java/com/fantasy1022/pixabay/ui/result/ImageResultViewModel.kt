package com.fantasy1022.pixabay.ui.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fantasy1022.pixabay.common.Constant
import com.fantasy1022.pixabay.data.ImagesInfo
import com.fantasy1022.pixabay.repository.ImageSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageResultViewModel( private val imageSearchRepository: ImageSearchRepository) : ViewModel() {

    @Throws(Exception::class)
    fun getSearchImages(query: String): MutableLiveData<ImagesInfo> {
        val imagesInfo: MutableLiveData<ImagesInfo> = MutableLiveData()
        viewModelScope.launch((Dispatchers.Main)) {
            try {
                withContext(Dispatchers.IO) {
                    val result = imageSearchRepository.getImageSearch(Constant.PIXABAY_API_KEY,query, "photo")
                    imagesInfo.postValue(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                //TODO:  Handle error
            }
        }

        return imagesInfo
    }

}