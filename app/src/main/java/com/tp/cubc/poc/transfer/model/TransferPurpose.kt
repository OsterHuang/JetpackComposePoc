package com.tp.cubc.poc.transfer.model

import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable

enum class TransferPurpose(val text: String): DropdownItemSelectable {
    PayForGoods("Pay for Goods") {
        override fun getLabel(): String {
            return this.text
        }
    },
    PayForHouse("Pay for House") {
        override fun getLabel(): String {
            return this.text
        }
    },
    PayForLend("Pay for Lend") {
        override fun getLabel(): String {
            return this.text
        }
    },
}