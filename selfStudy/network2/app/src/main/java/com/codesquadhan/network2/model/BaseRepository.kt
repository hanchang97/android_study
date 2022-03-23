package com.codesquadhan.network2.model

interface BaseRepository {
    suspend fun getImageData() : Array<ImageData>?
}