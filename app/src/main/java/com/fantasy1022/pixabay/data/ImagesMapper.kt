package com.fantasy1022.pixabay.data

object ImagesMapper {

    fun toImageInfo(entity: ImagesEntity): ImagesInfo {
        return ImagesInfo(total = entity.total,
            totalHits = entity.totalHits,
            imagesDetailInfos = entity.hits.map { toImageDetailInfo(it) })
    }

    //TODO: Check image properties
    private fun toImageDetailInfo(imageBean: ImagesEntity.ImageBean): ImagesInfo.ImageDetailInfo {
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
                userImageURL = userImageURL
            )
        }
    }
}