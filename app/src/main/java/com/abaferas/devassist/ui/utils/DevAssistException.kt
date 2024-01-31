package com.abaferas.devassist.ui.utils

sealed class DevAssistException(msg: String): Exception() {
    data object NoInternetConnection: DevAssistException("No Internet Connection!")
    data object ConnectionTimeOut: DevAssistException("Connection Time out!")
    data object InternalServerErrorException: DevAssistException("Server is down.")
    data object NoData: DevAssistException("Nothing found!")




}