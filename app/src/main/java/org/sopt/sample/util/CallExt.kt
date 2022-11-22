package org.sopt.sample.util

import android.util.Log
import org.sopt.sample.LoginActivity.Companion.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                Log.e(TAG, "onResponse: ${response.body()}")
                onSuccess.invoke(response.body() ?: return)
            } else {
                Log.e(TAG, "onResponse: ${response.body()}")
                onError?.invoke(response.code())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Timber.d("error : $t")
        }
    })
}
