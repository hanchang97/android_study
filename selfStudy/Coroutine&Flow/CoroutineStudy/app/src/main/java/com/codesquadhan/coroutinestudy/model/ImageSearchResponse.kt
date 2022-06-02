package com.codesquadhan.coroutinestudy.model

data class ImageSearchResponse(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val downloads: Int,
    val exif: Exif,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val location: Location,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
)