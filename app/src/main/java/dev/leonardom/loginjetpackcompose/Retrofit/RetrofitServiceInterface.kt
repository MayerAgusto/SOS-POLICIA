package dev.leonardom.loginjetpackcompose.Retrofit

import dev.leonardom.loginjetpackcompose.Retrofit.Data.ResponseAPI
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServiceInterface {
    @GET("/")
    suspend fun getComisariasList():Response<ResponseAPI>
}