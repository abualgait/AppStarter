package com.company.app.data.data_source.remote.response


import com.company.app.data.data_source.local.AppEntity
import com.google.gson.annotations.SerializedName

data class AppDataResponse(
    @SerializedName("results")
    val data: List<AppEntity>
)