package com.example.manymanymanycats.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET(value = "v1/images/search")
  suspend  fun getCat(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int,
//        @Query("order") order: String,
//        @Query("breed_ids") breed: String,
//        @Query("category_ids") category: String
    ): Response<List<CatInfo>>

}