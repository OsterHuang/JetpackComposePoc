package com.tp.cubc.poc.transfer.dataModel.favorite

abstract class TransferFavoriteItem(val toAccount: String, val toAccountName: String) {
}


// Other fast local
//val favoriteAccountTransInfo = FavoriteAccountTransInfo(
//    toAccount = transferUiState.beneficiaryAccountNum.value,
//    custName = transferUiState.beneficiaryName.value,
//    bankCode = transferUiState.beneficiaryBankCode.value
//)

// Bakong wallet
//val favoriteAccountTransInfo = FavoriteAccountTransInfo(
//    toAccount = PHONE_PREFIX_KH + transferUiState.beneficiaryAccountNum.value?.removePrefix("0"),
//    custName = transferUiState.beneficiaryName.value
//)

// Other bank bakong
//val favoriteAccountTransInfo = FavoriteAccountTransInfo(
//    toAccount = transferUiState.beneficiaryAccountNum.value,
//    custName = transferUiState.beneficiaryName.value,
//    bankCode = transferUiState.beneficiaryBankCode.value
//)

// CUBC
//val favoriteAccountTransInfo = FavoriteAccountTransInfo(
//    toAccount = transferUiState.beneficiaryAccountNum.value,
//    custName = transferUiState.beneficiaryName.value
//)