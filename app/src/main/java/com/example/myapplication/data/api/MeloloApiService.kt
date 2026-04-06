package com.example.myapplication.data.api

import com.example.myapplication.data.model.DramaDetailResponse
import com.example.myapplication.data.model.DramaListResponse
import com.example.myapplication.data.model.StreamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MeloloApiService {
    
    @GET("api/melolo/latest")
    suspend fun getLatestDramas(): DramaListResponse
    
    @GET("api/melolo/trending")
    suspend fun getTrendingDramas(): DramaListResponse
    
    @GET("api/melolo/search")
    suspend fun searchDramas(
        @Query("query") query: String,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): DramaListResponse
    
    @GET("api/melolo/detail")
    suspend fun getDramaDetail(
        @Query("bookId") bookId: String
    ): DramaDetailResponse
    
    @GET("api/melolo/stream")
    suspend fun getStreamUrl(
        @Query("videoId") videoId: String
    ): StreamResponse
}
