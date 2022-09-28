package com.racpan.gp_assessment.network

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.lifecycle.ViewModel
import com.racpan.gp_assessment.R
import java.net.ConnectException
import java.net.HttpRetryException
import java.net.SocketTimeoutException
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class NetworkCalls : ViewModel() {

    suspend fun getLaunchData(urlApi : String, context : Context) : String {
        return try {
            URL(urlApi).readText()
        } catch(e : Exception) {
            when(e) {
                is UnknownHostException, is SocketTimeoutException, is SSLHandshakeException, is ConnectException, is HttpRetryException, is NetworkErrorException -> {
                    context.getString(R.string.network_error)
                } else -> context.getString(R.string.general_error)
            }
        }
    }
}