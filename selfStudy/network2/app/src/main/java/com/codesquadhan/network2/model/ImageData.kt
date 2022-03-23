package com.codesquadhan.network2.model

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("title")
    var title : String,
    @SerializedName("image")
    var image : String,
    @SerializedName("date")
    var date : String,
    )