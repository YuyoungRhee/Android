package com.example.busorsubway

data class CoordinateResponse(
    val response: CoordinateResultWrapper
)

data class CoordinateResultWrapper(
    val result: CoordinateResult?,
    val status: String
)

data class CoordinateResult(
    val point: CoordinatePoint?
)

data class CoordinatePoint(
    val x: String,
    val y: String
)
