package com.mbanking.cubc.myAccount.repository.dataModel

import com.google.gson.annotations.SerializedName

data class QueryAccountInfoResponseBodyResult(
    @SerializedName("currentList")
    val currentList: List<MyBankAccount>,
    @SerializedName("savingsList")
    val savingsList: List<MyBankAccount>,
    @SerializedName("digitalList")
    val digitalList: List<MyBankAccount>,
    @SerializedName("fixedDepositList")
    val fixedDepositList: List<MyBankAccount>
)
