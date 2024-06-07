package com.example.ch18_image

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https:/apis.data.go.kr/B53748/CertImgListServiceV3/getCertImgListServiceV3/
interface NetworkService {
    @GET("getCertImgListServiceV3")
    fun getXmlList(
        @Query("prdlstNm") prdlstNm:String,
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
        @Query("returnType") returnType:String,
        @Query("serviceKey") apiKey:String,

    ) : Call<XmlResponse>
}