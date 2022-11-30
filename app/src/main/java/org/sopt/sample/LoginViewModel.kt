package org.sopt.sample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.LoginActivity.Companion.TAG
import org.sopt.sample.data.dto.response.DummyResponse
import org.sopt.sample.data.dto.response.RequestLoginDTO
import org.sopt.sample.data.dto.response.ResponseLoginDTO
import org.sopt.sample.remote.ApiFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<ResponseLoginDTO>()
    val loginResult: LiveData<ResponseLoginDTO>
        get() = _loginResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val loginService = ApiFactory.ServicePool.loginService

    fun login(email: String, password: String) {
        loginService.login(RequestLoginDTO(email, password))
            .enqueue(object : Callback<DummyResponse<ResponseLoginDTO>> {
                override fun onResponse(
                    call: Call<DummyResponse<ResponseLoginDTO>>,
                    response: Response<DummyResponse<ResponseLoginDTO>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        Log.e(TAG, "로그인 성공: $data")
                        _loginResult.value = data!!.result!!
                    }
                }

                override fun onFailure(call: Call<DummyResponse<ResponseLoginDTO>>, t: Throwable) {
                    val code = t.message
                    Log.e(TAG, "로그인 실패: $code")
                }
            })
    }
//            .enqueueUtil({ result ->
//                _loginResult.value = result.data!!
//                Log.e(TAG, "로그인 성공: $result")
//            }, { code ->
//                Log.e(TAG, "로그인 실패: $code")
//            })
}
