package com.selinkaya.selinproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Instrument(
    val symbol: String,
    val description: String? = null,
    var lastPrice: Double? = null
)