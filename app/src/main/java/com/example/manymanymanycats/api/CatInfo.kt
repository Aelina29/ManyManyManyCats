package com.example.manymanymanycats.api

import com.squareup.moshi.Json
import java.io.Serializable

data class ImageCatInfo (
    @field:Json(name = "id")
    val id: String? = null,

    @field:Json(name = "width")
    val width: Int? = null,

    @field:Json(name = "height")
    val height: Int? = null,

    @field:Json(name = "url")
    val url: String? = null
)

data class CatInfo (

    //val breeds: List<BreedCatInfo>? = null,

    val width: Int? = null,

    val height: Int? = null,

    val url: String,

    val id: String="",

    //val categories: List<CategoryCat>? = null,

    val image_id:String?=null,

    val userId:String?=null,

    val imageId:String?=null,

    //Должен быть только у favorites
    val created_at:String="",

    val image:ImageCatInfo?=null

)