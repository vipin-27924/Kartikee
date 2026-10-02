package com.example.kartikee.core.network

import com.example.kartikee.core.network.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/exercise/evaluate-ai")
    suspend fun evaluatePoseWithAi(
        @Body request: PoseFrameRequest
    ): Response<PostureResponse>

    @POST("api/auth/verify-pin")
    suspend fun verifyPin(
        @Body request: PinRequest
    ): Response<AuthResponse>

    // 3. Fetch parental compliance stats
    @GET("api/parental/report")
    suspend fun getParentalReport(
        @Header("Authorization") token: String
    ): Response<ComplianceReportResponse>
}