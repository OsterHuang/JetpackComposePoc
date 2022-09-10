package com.tp.cubc.poc.transfer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tp.cubc.poc.transfer.model.BankAccount
import com.tp.cubc.poc.transfer.model.TransferType
import com.tp.cubc.poc.transfer.model.favorite.TransferFavoriteItem
import com.tp.cubc.poc.util.CubcCurrency
import kotlinx.coroutines.delay
import java.math.BigDecimal

class TransferMainViewModel: ViewModel() {
    val accountList = mutableStateOf<List<BankAccount>?>(listOf())
    val fromAccount = mutableStateOf<BankAccount?>(null)
    val transferType = mutableStateOf<TransferType?>(null)
    val transferFavoriteItem = mutableStateOf<TransferFavoriteItem?>(null)

    suspend fun queryAccountList() {
        delay(1200)
        accountList.value = listOf(
            BankAccount("10031745", BigDecimal("200074"), CubcCurrency.KHR),
            BankAccount("10035771", BigDecimal("5568"), CubcCurrency.KHR),
            BankAccount("800031552", BigDecimal("3111.75"), CubcCurrency.USD),
        )
    }
}





