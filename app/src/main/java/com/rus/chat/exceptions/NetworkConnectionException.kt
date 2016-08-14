package com.rus.chat.exceptions

/**
 * Created by RUS on 14.07.2016.
 */
class NetworkConnectionException(detailMessage: String = "No internet connection",
                                 throwable: Throwable? = null) : Exception(detailMessage, throwable)