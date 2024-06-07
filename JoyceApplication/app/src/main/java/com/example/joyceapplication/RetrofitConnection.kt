package com.example.joyceapplication

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object{
        private const val BASE_URL = "http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/"

        val jsonNetServ: NetworkService
        val jsonRetrofit:Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val xmlNetServ:NetworkService
        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
        val xmlRetrofit:Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()

        init {
            jsonNetServ = jsonRetrofit.create(NetworkService::class.java)
            xmlNetServ = xmlRetrofit.create(NetworkService::class.java)
        }
    }
}