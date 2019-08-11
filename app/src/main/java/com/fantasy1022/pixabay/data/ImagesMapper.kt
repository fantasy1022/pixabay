package com.fantasy1022.pixabay.data

import java.util.*

object ImagesMapper {

    fun toImagesInfo(entity: ImagesEntity): ImagesInfo {
        val averageRatio = entity.hits.map { (it.imageWidth / it.imageHeight) }.average()
        return ImagesInfo(total = entity.total,
            totalHits = entity.totalHits,
            imagesDetailInfos = entity.hits.map { toImageDetailInfo(it, averageRatio) }
        )
    }

    private fun toImageDetailInfo(
        imageBean: ImagesEntity.ImageBean,
        averageRation: Double
    ): ImagesInfo.ImageDetailInfo {
        return imageBean.run {
            ImagesInfo.ImageDetailInfo(
                id = id,
                pageURL = pageURL,
                type = type,
                tags = tags,
                previewURL = previewURL,
                previewHeight = previewWidth,
                previewWidth = previewWidth,
                webformatURL = webformatURL,
                webformatHeight = webformatHeight,
                webformatWidth = webformatWidth,
                largeImageURL = largeImageURL,
                imageWidth = imageWidth,
                imageHeight = imageHeight,
                imageSize = imageSize,
                views = views,
                downloads = downloads,
                favorites = favorites,
                likes = likes,
                comments = comments,
                user_id = user_id,
                user = user,
                userImageURL = userImageURL,
                imageRatio = getImageHeightRatio(imageWidth, imageHeight, averageRation)
            )
        }
    }

    private fun getImageHeightRatio(imageWidth: Int, imageHeight: Int, averageRatio: Double): Float =
        ((imageWidth / imageHeight) / averageRatio + Random().nextFloat()).toFloat()

}