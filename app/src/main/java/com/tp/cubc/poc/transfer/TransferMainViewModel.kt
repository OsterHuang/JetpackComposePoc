package com.tp.cubc.poc.transfer

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.tp.cubc.poc.transfer.model.BankAccount
import com.tp.cubc.poc.transfer.model.OtherBank
import com.tp.cubc.poc.transfer.model.TransferType
import com.tp.cubc.poc.transfer.model.favorite.TransferFavoriteItem
import com.tp.cubc.poc.util.constant.CubcCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TransferMainViewModel @Inject constructor(
    app: Application
): AndroidViewModel(app) {
    val accountList = mutableStateOf<List<BankAccount>?>(listOf())
    val fromAccount = mutableStateOf<BankAccount?>(null)
    val transferToBank = mutableStateOf<OtherBank?>(null)
    val transferType = mutableStateOf<TransferType?>(null)
    val transferFavoriteItem = mutableStateOf<TransferFavoriteItem?>(null)

    suspend fun queryAccountList() {

        delay(1200)
        accountList.value = listOf(
            BankAccount("10031745", "My HKR 1", BigDecimal("200074"), CubcCurrency.KHR),
            BankAccount("10035771", "My HKR 2", BigDecimal("5568"), CubcCurrency.KHR),
            BankAccount("800031552", "MY USD 1", BigDecimal("3111.75"), CubcCurrency.USD),
        )
    }
}





