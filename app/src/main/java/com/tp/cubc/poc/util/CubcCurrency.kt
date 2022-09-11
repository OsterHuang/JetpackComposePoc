package com.tp.cubc.poc.util

import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable

enum class CubcCurrency(val symbol: String, val code: String): DropdownItemSelectable{
    USD("$", "840") {
        override fun getLabel(): String {
            return this.name
        }
    },
    KHR("៛", "119") {
        override fun getLabel(): String {
            return this.name
        }
    },
}