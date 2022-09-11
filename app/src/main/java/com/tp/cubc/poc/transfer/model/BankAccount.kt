package com.tp.cubc.poc.transfer.model

import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable
import com.tp.cubc.poc.util.constant.CubcCurrency
import java.math.BigDecimal

class BankAccount (
    val accountNo: String,
    val nickname: String,
    val balance: BigDecimal,
    val currency: CubcCurrency
) : DropdownItemSelectable {
    override fun getLabel(): String {
        return "$accountNo (${currency.name})"
    }
}