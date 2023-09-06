package com.selinkaya.selinproject.data.repository

import com.selinkaya.selinproject.common.BaseResult
import com.selinkaya.selinproject.data.model.InstrumentDesc
import kotlinx.coroutines.flow.Flow

interface InstrumentDescRepository {
    suspend fun getInstrumentDesc(): Flow<BaseResult<List<InstrumentDesc>>>
}