package com.tp.cubc.poc.transfer.cubc;

import android.os.Parcelable
import com.tp.cubc.poc.ui.component.TransactionDetailItem;

import java.util.List;

import kotlinx.parcelize.Parcelize;

@Parcelize
class CubcSuccessResultDetail(
    val transferAmount: String = "-",
    val toAccount: String = "-",
    val fromAccount: String = "-",
    val transferDate: String = "-",
): Parcelable

