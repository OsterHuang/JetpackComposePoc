package com.tp.cubc.poc.transfer.otherlocalfast

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tp.cubc.poc.transfer.dataModel.BankAccount
import java.math.BigDecimal

class LocalFastViewModel: ViewModel() {
    val fromAccount: State<BankAccount?> = mutableStateOf<BankAccount?>(null)
    val toAccount: State<String?> = mutableStateOf<String?>(null)
    val toAccountName: State<String?> = mutableStateOf<String?>(null)
    val transferAmount: State<BigDecimal?> = mutableStateOf<BigDecimal?>(null)
    val nickname: State<String?> = mutableStateOf<String?>(null)
}