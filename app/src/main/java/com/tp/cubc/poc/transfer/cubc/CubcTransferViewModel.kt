package com.tp.cubc.poc.transfer.cubc

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tp.cubc.poc.transfer.model.BankAccount
import com.tp.cubc.poc.transfer.model.TransferPurpose
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CubcTransferViewModel @Inject constructor(
    app: Application
): AndroidViewModel(app) {
    val toAccount: MutableState<String?> = mutableStateOf<String?>(null)
    val toAccountError: MutableState<String?> = mutableStateOf<String?>(null)

    val transferAmount: MutableState<BigDecimal?> = mutableStateOf<BigDecimal?>(null)
    val transferAmountError: MutableState<String?> = mutableStateOf<String?>(null)

    val transferDate: State<Date> = mutableStateOf(Date())

    val nickname: MutableState<String?> = mutableStateOf<String?>(null)
    val nicknameError: MutableState<String?> = mutableStateOf<String?>(null)

    val purpose: MutableState<TransferPurpose> = mutableStateOf(TransferPurpose.PayForGoods)

    fun validateFields(): Boolean {
        var erroCount = 0

        if (toAccount.value.isNullOrBlank()) {
            toAccountError.value = "Can not be empty."
            erroCount++
        }

        if (transferAmount.value == null) {
            transferAmountError.value = "Can not be empty."
            erroCount++
        }

        return erroCount == 0
    }
}