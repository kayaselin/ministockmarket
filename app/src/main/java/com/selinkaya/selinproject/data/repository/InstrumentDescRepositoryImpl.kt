package com.selinkaya.selinproject.data.repository

import com.selinkaya.selinproject.common.BaseResult
import com.selinkaya.selinproject.data.model.InstrumentDesc
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class InstrumentDescRepositoryImpl : InstrumentDescRepository {
    private val instrumentDescJson = "[\n" +
            " { \"symbol\": \"THYAO\", \"description\": \"Türk Hava Yolları\" }, \n" +
    " { \"symbol\": \"GARAN\", \"description\": \"Garanti Bankası\" },\n" +
    " { \"symbol\": \"ASTOR\", \"description\": \"Astor Enerji\" }, \n" +
    " { \"symbol\": \"HEKTS\", \"description\": \"HEKTAS\" },\n" +
    " { \"symbol\": \"ENKAI\", \"description\": \"ENKA INSAAT\" },\n" +
    " { \"symbol\": \"GUBRF\", \"description\": \"GUBRE FABRIKALAR\" }\n" + " ]\n"
    override suspend fun getInstrumentDesc(): Flow<BaseResult<List<InstrumentDesc>>> {
        return flow {
            val instrumentDesc = Json.decodeFromString<List<InstrumentDesc>>(instrumentDescJson)
            val response = BaseResult.Success(instrumentDesc) // Create a successful Response
            emit(response)
        }
    }
}