package com.tp.cubc.poc.transfer

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.mbanking.cubc.myAccount.repository.dataModel.MyBankAccount
import com.tp.cubc.poc.account.repository.AccountRemoteDataSource
import com.tp.cubc.poc.transfer.dataModel.BankAccount
import com.tp.cubc.poc.transfer.dataModel.OtherBank
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.transfer.dataModel.favorite.TransferFavoriteItem
import com.tp.cubc.poc.util.constant.CubcCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TransferMainViewModel @Inject constructor(
    app: Application,
    val accountRemoteDataSource: AccountRemoteDataSource
): AndroidViewModel(app) {
    val accountList = mutableStateOf<List<MyBankAccount>>(listOf())
    val fromAccount = mutableStateOf<MyBankAccount?>(null)
    val transferToBank = mutableStateOf<OtherBank?>(null)
    val transferType = mutableStateOf<TransferType?>(null)
    val transferFavoriteItem = mutableStateOf<TransferFavoriteItem?>(null)

    suspend fun queryAccountList() {
        val result = accountRemoteDataSource.queryAccountInfo()

        accountList.value = result.getOrNull()?.run {
            currentList + digitalList + savingsList
        } ?: listOf()

        if (accountList.value.isNotEmpty()) {
            fromAccount.value = accountList.value[0]
        }
    }
}





