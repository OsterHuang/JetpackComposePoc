package com.tp.cubc.poc.transfer.model

import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable
import com.tp.cubc.poc.util.CubcCurrency
import java.math.BigDecimal
import java.util.*

class BankAccount (
    val accountNo: String,
    val balance: BigDecimal,
    val currency: CubcCurrency
) : DropdownItemSelectable {
    override fun getLabel(): String {
        return "$accountNo (${currency.name})"
    }
}