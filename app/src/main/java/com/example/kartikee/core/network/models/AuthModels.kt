package com.example.kartikee.core.network.models


data class PinRequest(
    val pin: String
)

data class AuthResponse(
    val access_token: String,
    val token_type: String,
    val authenticated: Boolean
)

data class ComplianceReportResponse(
    val weekly_compliance_percentage: Float,
    val total_sessions_completed: Int,
    val average_score: Float,
    val status: String
)