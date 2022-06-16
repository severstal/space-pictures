package com.example.space.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpacePictureDto(

    val date: String, // it's id
    val explanation: String? = null,
    val title: String? = null,
    val url: String? = null,
    @SerialName("hdurl")
    val hdUrl: String? = null,
    @SerialName("media_type")
    val mediaType: String? = null, // 'image', 'video'

) {}