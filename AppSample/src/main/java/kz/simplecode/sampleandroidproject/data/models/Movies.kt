package kz.simplecode.sampleandroidproject.data.models


import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieShort>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)