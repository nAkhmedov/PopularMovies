package uz.nakhmedov.movie.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.nakhmedov.movie.MovieApp
import uz.nakhmedov.movie.ui.util.AppConstants
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-19
 * Time: 23:16
 * To change this template use File | Settings | File Templates
 */

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: MovieApp): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor { message ->
            Platform.get().log(
                Platform.INFO,
                "Log Util: $message", null
            )
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        httpClient.addInterceptor(logging)

        return httpClient.cache(cache)
            .followRedirects(true)
            .followSslRedirects(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient, gson: Gson): Api {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build().create(Api::class.java)
    }
}