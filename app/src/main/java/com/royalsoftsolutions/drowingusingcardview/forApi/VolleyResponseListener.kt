package com.royalsoftsolutions.drowingusingcardview.forApi

interface VolleyResponseListener {

    fun onResponse(response: String?)

    fun onError(errorMessage: String?)

}