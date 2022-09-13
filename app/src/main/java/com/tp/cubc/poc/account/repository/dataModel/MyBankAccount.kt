package com.mbanking.cubc.myAccount.repository.dataModel

import com.google.gson.annotations.SerializedName
import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable
import com.tp.cubc.poc.util.constant.CubcCurrency
import java.math.BigDecimal

data class MyBankAccount(
    @SerializedName("branchCode") // 帳戶分行代碼
    val branchCode: String = "",
    @SerializedName("account") // 帳號
    val account: String = "",
    @SerializedName("curr") // 幣別
    val curr: String = "",
    @SerializedName("balance") // 帳戶餘額
    val balance: BigDecimal,
    @SerializedName("nickname") // 暱稱
    val nickname: String? = null
): DropdownItemSelectable {
    override fun getLabel() = "$curr $account $nickname"

    fun getCurrency() = if (curr == CubcCurrency.USD.name) CubcCurrency.USD else CubcCurrency.KHR

    val formattedBalance: String
        get() =
            if (curr.equals(CubcCurrency.KHR.name, true)) {
                balance.toPlainString().split(".")[0]
            } else {
                balance.toPlainString()
            }
}
