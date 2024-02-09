package com.codarchy.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChocolateFactoryApi {

    @GET("napptilus/oompa-loompas")
    suspend fun getFactoryEmployees(@Query("page") page: Int): EmployeeResponse

}
