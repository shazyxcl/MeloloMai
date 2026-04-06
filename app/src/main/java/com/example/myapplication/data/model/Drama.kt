package com.example.myapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.IgnoredOnParcel

@Parcelize
data class Drama(
    val bookId: String,
    val title: String,
    val cover: String,
    val description: String? = null,
    val rating: String? = null,
    val episodes: Int? = null
) : Parcelable

data class DramaListResponse(
    val status: String,
    val message: String,
    val data: List<DramaItem>
)

data class DramaItem(
    val bookId: String,
    val title: String,
    val cover: String,
    val rating: String? = null,
    val episodes: String? = null
)

data class DramaDetailResponse(
    val status: String,
    val message: String,
    val data: DramaDetail
)

data class DramaDetail(
    val bookId: String,
    val title: String,
    val cover: String,
    val description: String,
    val rating: String,
    val genres: List<String>? = null,
    val episodes: List<Episode>
)

data class Episode(
    val vid: String,
    val title: String,
    val episodeNumber: String
)

data class StreamResponse(
    val status: String,
    val message: String,
    val data: StreamData
)

data class StreamData(
    val streamUrl: String,
    val quality: List<Quality>? = null
)

data class Quality(
    val label: String,
    val url: String
)

