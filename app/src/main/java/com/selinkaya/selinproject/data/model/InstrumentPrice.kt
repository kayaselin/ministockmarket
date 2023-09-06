package com.selinkaya.selinproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InstrumentPrice(
    val symbol: String,
    val lastPrice: Double
)
