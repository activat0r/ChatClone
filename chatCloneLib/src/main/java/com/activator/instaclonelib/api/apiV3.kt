package com.activator.instaclonelib.api

import retrofit2.http.GET
import retrofit2.http.Path

interface apiV3 {
    @GET("gallery/{section}")
    suspend fun getGallery(
        @Path("section") section:String)
}