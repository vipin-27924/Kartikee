package com.example.kartikee.core.network.models

data class PoseFrameRequest(
    val pose_name: String,
    val landmarks: Map<String, LandmarkPoint>
)

data class LandmarkPoint(
    val x: Float,
    val y: Float,
    val z: Float,
    val visibility: Float
)

data class PostureResponse(
    val is_aligned: Boolean,
    val alignment_score: Float,
    val feedback: String
)