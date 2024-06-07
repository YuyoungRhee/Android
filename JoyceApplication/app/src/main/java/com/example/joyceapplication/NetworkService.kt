package com.example.joyceapplication

import androidx.annotation.XmlRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo
interface NetworkService {
    @GET("getUlfptcaAlarmInfo")
    fun getJsonList(
        @Query("year") year:Int,
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
        @Query("returnType") returnType:String,
        @Query("servicekey") servicekey:String,

        ): Call<JsonResponse>

    @GET("getUlfptcaAlarmInfo")
    fun getXmlList(
        @Query("year") year:Int,
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
        @Query("returnType") returnType:String,
        @Query("servicekey") servicekey:String,
    ):Call<XmlResponse>
}