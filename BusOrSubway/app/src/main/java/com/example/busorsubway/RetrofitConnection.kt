package com.example.busorsubway

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

// http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year=2024&pageNo=1&numOfRows=10&returnType=xml&serviceKey=서비스키(일반 인증키(Encoding))
class RetrofitConnection {

    companion object {
        // API 서버의 주소가 BASE_URL이 됩니다.
        private const val BASE_URL = "https://business.juso.go.kr/addrlink/"
        private const val COORDINATE_BASE_URL = "https://api.vworld.kr/"

        // 네트워크 서비스 인터페이스
        var jsonNetworkService: NetworkService
        var coordinateNetworkService: NetworkService

        init {
            val jsonRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            jsonNetworkService = jsonRetrofit.create(NetworkService::class.java)

            val coordinateRetrofit = Retrofit.Builder()
                .baseUrl(COORDINATE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            coordinateNetworkService = coordinateRetrofit.create(NetworkService::class.java)
        }
    }
}