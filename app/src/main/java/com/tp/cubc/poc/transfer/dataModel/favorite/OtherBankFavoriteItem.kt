package com.tp.cubc.poc.transfer.dataModel.favorite

class OtherBankFavoriteItem(
    toAccount: String,
    toAccountName: String,
    bankCode: String
) : TransferFavoriteItem(
    toAccount,
    toAccountName
) {
}