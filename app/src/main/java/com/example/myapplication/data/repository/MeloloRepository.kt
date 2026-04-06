package com.example.myapplication.data.repository

import com.example.myapplication.data.api.RetrofitClient
import com.example.myapplication.data.model.DramaDetail
import com.example.myapplication.data.model.DramaItem
import com.example.myapplication.data.model.StreamData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MeloloRepository {
    private val apiService = RetrofitClient.apiService
    
    suspend fun getLatestDramas(): Result<List<DramaItem>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getLatestDramas()
            if (response.status == "success") {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTrendingDramas(): Result<List<DramaItem>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTrendingDramas()
            if (response.status == "success") {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun searchDramas(query: String, limit: Int = 10, offset: Int = 0): Result<List<DramaItem>> = 
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchDramas(query, limit, offset)
                if (response.status == "success") {
                    Result.success(response.data)
                } else {
                    Result.failure(Exception(response.message))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    
    suspend fun getDramaDetail(bookId: String): Result<DramaDetail> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getDramaDetail(bookId)
            if (response.status == "success") {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getStreamUrl(videoId: String): Result<StreamData> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getStreamUrl(videoId)
            if (response.status == "success") {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

