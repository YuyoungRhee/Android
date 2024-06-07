package com.example.busorsubway

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https:/apis.data.go.kr/B53748/CertImgListServiceV3/getCertImgListServiceV3/
interface NetworkService {
    @GET("addrLinkApi.do")
    fun getJsonList(
        @Query("keyword") keyword: String,
        @Query("currentPage") currentPage: Int,
        @Query("countPerPage") countPerPage: Int,
        @Query("resultType") resultType: String = "json",
        @Query("confmKey") confmKey: String
    ): Call<JsonResponse>

    @GET("req/address")
    fun getCoordinates(
        @Query("service") service: String,
        @Query("request") request: String,
        @Query("version") version: String,
        @Query("crs") crs: String,
        @Query("address") address: String,
        @Query("refine") refine: Boolean,
        @Query("simple") simple: Boolean,
        @Query("format") format: String,
        @Query("type") type: String,
        @Query("key") key: String
    ): Call<CoordinateResponse>
}