package org.tg_member.data.remote

import org.tg_member.data.remote.api.MemberApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = ""

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: MemberApiService by lazy {
        RetrofitClient.retrofit.create(MemberApiService::class.java)
    }
}
