package com.selinkaya.selinproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InstrumentDesc(
    val symbol: String,
    val description: String
)
