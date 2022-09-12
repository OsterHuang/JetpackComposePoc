package com.tp.cubc.poc.ui.component

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.ui.theme.Green600
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionDetailItem(val label: String, val value: String): Parcelable

@Composable
fun TransactionDetail(
    items: List<TransactionDetailItem> = listOf()
) {
    val valueTextModifier = Modifier.padding(0.dp, 10.dp)
    val lastValueTextModifier = Modifier.padding(0.dp, 10.dp, 0.dp, 2.dp)

    RoundedBorderColumn(horizontalAlignment = Alignment.Start) {
        items.mapIndexed { index, item ->
            Subtitle2(
                text = item.label
            )
            ValueText(
                text = item.value,
                modifier = if (index != items.size - 1) valueTextModifier else lastValueTextModifier
            )
            if (index != items.size - 1) {
                Divider()
                Spacer(Modifier.height(14.dp))
            }
        }

    }
}

@Preview
@Composable
fun PreviewTransactionDetail() {
    val items = listOf(
        TransactionDetailItem("Transfer Amount", "$10,000.0"),
        TransactionDetailItem("Transfer To", "CUCB"),
        TransactionDetailItem("Beneficiary Account Number", "12345000004079"),
        TransactionDetailItem("Beneficiary Name", "Sovana Keo"),
        TransactionDetailItem("From Account", "USD 1079-4423-770169 USD Acc 1"),
        TransactionDetailItem("Transfer Date", "01/12/2021 (Immediate)"),
    )

    CubcAppTheme {
        Surface(color = Green600) {
            TransactionDetail(items)
        }
    }
}