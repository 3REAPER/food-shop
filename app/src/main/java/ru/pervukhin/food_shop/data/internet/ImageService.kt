package ru.pervukhin.food_shop.data.internet

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ImageService {
    @Streaming
    @GET
    suspend fun downloadImage(@Url url: String): Response<ResponseBody>
}