package org.sopt.sample.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.sample.BuildConfig
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiFactory {
    val json = Json { ignoreUnknownKeys = true }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(providesOkHttpClient())
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)

    private fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
                }
            }
            .build()

    object ServicePool {
        val loginService = create<LoginService>()
        val signupService = create<SignupService>()
    }
}
