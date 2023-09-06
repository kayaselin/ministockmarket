package com.selinkaya.selinproject.data.repository

import com.selinkaya.selinproject.common.BaseResult
import com.selinkaya.selinproject.data.model.InstrumentPrice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class InstrumentPriceRepositoryImpl : InstrumentPriceRepository {

    private val instrumentPriceJson = "[\n" +
            " { \"symbol\": \"THYAO\", \"lastPrice\":  152.006 }, \n" +
            " { \"symbol\": \"GARAN\", \"lastPrice\":  53.799 },\n" +
            " { \"symbol\": \"ASTOR\", \"lastPrice\":  43.145123 }, \n" +
            " { \"symbol\": \"HEKTS\", \"lastPrice\":  26.165 },\n" +
            " { \"symbol\": \"ENKAI\", \"lastPrice\":  32.0834 },\n" +
            " { \"symbol\": \"GUBRF\", \"lastPrice\":  307.506 }\n" + " ]\n"

    override suspend fun getInstrumentPrice(): Flow<BaseResult<List<InstrumentPrice>>> {
        return flow {
            val instrumentDesc = Json.decodeFromString<List<InstrumentPrice>>(instrumentPriceJson)
            val response = BaseResult.Success(instrumentDesc) // Create a successful Response
            emit(response)
        }
    }
}