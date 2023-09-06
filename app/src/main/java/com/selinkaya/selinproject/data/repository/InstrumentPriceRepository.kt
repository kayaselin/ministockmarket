package com.selinkaya.selinproject.data.repository

import com.selinkaya.selinproject.common.BaseResult
import com.selinkaya.selinproject.data.model.InstrumentPrice
import kotlinx.coroutines.flow.Flow

interface InstrumentPriceRepository {
    suspend fun getInstrumentPrice(): Flow<BaseResult<List<InstrumentPrice>>>
}