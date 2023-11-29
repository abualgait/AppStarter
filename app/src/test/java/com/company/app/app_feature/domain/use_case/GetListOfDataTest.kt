package com.company.app.app_feature.domain.use_case

import com.company.app.app_feature.data.repository.FakeDataRepository
import com.company.app.data.data_source.remote.RetrofitService
import com.company.app.domain.model.AppEntity
import com.company.app.domain.use_case.GetListOfData
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetListOfDataTest {

    private lateinit var getListOfData: GetListOfData
    private lateinit var fakeRepository: FakeDataRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private lateinit var retrofitService: RetrofitService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/getEntitys/")
        retrofitService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            )
            .build()
            .create(RetrofitService::class.java)
        fakeRepository = FakeDataRepository(retrofitService)
        getListOfData = GetListOfData(fakeRepository)

        val entitysToInsert = mutableListOf<AppEntity>()
        ('a'..'z').forEachIndexed { index, c ->
            entitysToInsert.add(
                AppEntity(
                    title = c.toString()
                )
            )
        }
        entitysToInsert.shuffle()
        runBlocking {
            entitysToInsert.forEach { fakeRepository.insertData(it) }
        }
    }


}