package com.company.app.di

import android.app.Application
import androidx.room.Room
import com.company.app.data.data_source.local.AppDatabase
import com.company.app.data.data_source.remote.RetrofitService
import com.company.app.data.repository.AppRepositoryImpl
import com.company.app.domain.repository.AppRepository
import com.company.app.domain.use_case.*
import com.company.app.util.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            AppDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideService(okHttpClient: OkHttpClient): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRepository(db: AppDatabase, retrofitService: RetrofitService): AppRepository {
        return AppRepositoryImpl(db.appDao, retrofitService)
    }

    @Provides
    @Singleton
    fun provideAppUseCases(repository: AppRepository): AppUseCases {
        return AppUseCases(
            getListOfData = GetListOfData(repository),
            deleteData = DeleteData(repository),
            addData = AddData(repository),
            getData = GetData(repository)
        )
    }


}