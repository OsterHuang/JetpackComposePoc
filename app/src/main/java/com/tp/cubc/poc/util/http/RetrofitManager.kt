package com.tp.cubc.poc.util.http

import com.tp.cubc.poc.BuildConfig
import com.tp.cubc.poc.TAG
import com.tp.cubc.poc.landing.repository.LandingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitManager {
    val tag = TAG

    private var URL = "ht" + "tp://" + "192.168.20.118:3000/"
    private const val URL_STG = "ht" + "tps://" + "mbkhut.cathaybkdev.com.tw/cubcapi/"
    private const val URL_UAT = "ht" + "tps://" + "mbkhuat.cathaybkdev.com.tw/cubcapi/"
    private const val URL_PRODUCTION = "ht" + "tp://" + "192.168.20.80:7080/"

    @Provides
    @Singleton
//    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
    fun provideRetrofit(): Retrofit {
        when {
            BuildConfig.FLAVOR.equals("dev", true) -> {
                URL_STG
            }
            BuildConfig.FLAVOR.equals("stg", true) -> {
                URL_STG
            }
            BuildConfig.FLAVOR.equals("uat", true) -> {
                URL_UAT
            }
            else -> {
                URL_PRODUCTION
            }
        }

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): LandingApi = retrofit.create(LandingApi::class.java)

}
