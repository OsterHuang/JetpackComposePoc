package com.tp.cubc.poc.transfer.model.favorite

class OtherBankFavoriteItem(
    toAccount: String,
    toAccountName: String,
    bankCode: String
) : TransferFavoriteItem(
    toAccount,
    toAccountName
) {
}