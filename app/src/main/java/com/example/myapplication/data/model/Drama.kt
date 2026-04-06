package com.example.myapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Drama(
    val bookId: String,
    val title: String,
    val cover: String,
    val description: String? = null,
    val rating: String? = null,
    val episodes: Int? = null
) : Parcelable

// Response untuk list drama (latest, trending, search)
data class DramaListResponse(
    val status: String,
    val message: String,
    val data: DramaListData  // ← diubah dari List menjadi Object
)

// Data wrapper untuk list drama
data class DramaListData(
    @SerializedName("items") val items: List<DramaItem>,  // sesuaikan nama field dengan API
    @SerializedName("total") val total: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("totalPages") val totalPages: Int? = null
)

// Item drama untuk list
data class DramaItem(
    val bookId: String,
    val title: String,
    val cover: String,
    val rating: String? = null,
    val episodes: String? = null
)

// Response untuk detail drama
data class DramaDetailResponse(
    val status: String,
    val message: String,
    val data: DramaDetail
)

// Detail drama lengkap
data class DramaDetail(
    val bookId: String,
    val title: String,
    val cover: String,
    val description: String,
    val rating: String,
    val genres: List<String>? = null,
    val episodes: List<Episode>
)

// Episode
data class Episode(
    val vid: String,
    val title: String,
    val episodeNumber: String
)

// Response untuk stream URL
data class StreamResponse(
    val status: String,
    val message: String,
    val data: StreamData
)

// Data stream
data class StreamData(
    val streamUrl: String,
    val quality: List<Quality>? = null
)

// Kualitas video
data class Quality(
    val label: String,
    val url: String
)
