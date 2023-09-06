package com.selinkaya.selinproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selinkaya.selinproject.common.BaseResult
import com.selinkaya.selinproject.data.model.Instrument
import com.selinkaya.selinproject.data.repository.InstrumentDescRepositoryImpl
import com.selinkaya.selinproject.data.repository.InstrumentPriceRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class InstrumentViewModel () : ViewModel(){

    private val instrumentDescRepositoryImpl = InstrumentDescRepositoryImpl()
    private val instrumentPriceRepositoryImpl = InstrumentPriceRepositoryImpl()

    private val _instState: MutableStateFlow<List<Instrument>> = MutableStateFlow(emptyList())
    val instState: StateFlow<List<Instrument>> = _instState


    private suspend fun fetchInstrumentList() {
        val descResult = instrumentDescRepositoryImpl.getInstrumentDesc().firstOrNull() ?: return
        val priceResult = instrumentPriceRepositoryImpl.getInstrumentPrice().firstOrNull() ?: return

        val instList = mutableListOf<Instrument>()

        if (descResult is BaseResult.Success && priceResult is BaseResult.Success) {
            priceResult.data.forEach { price ->
                descResult.data.firstOrNull { it.symbol == price.symbol }?.let { description ->
                    instList.add(
                        Instrument(
                            symbol = price.symbol,
                            description = description?.description,
                            lastPrice = price.lastPrice
                        )
                    )
                }
            }
        }

        _instState.value = instList
    }

    fun getInstrumentList() {
        viewModelScope.launch {
            fetchInstrumentList()
        }
    }
}
