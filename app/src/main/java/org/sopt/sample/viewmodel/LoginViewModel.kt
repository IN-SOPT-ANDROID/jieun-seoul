package org.sopt.sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.dto.request.RequestLoginDTO
import org.sopt.sample.data.dto.response.ResponseLoginDTO
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.util.enqueueUtil
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<ResponseLoginDTO?>()
    val loginResult: LiveData<ResponseLoginDTO?>
        get() = _loginResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val loginService = ApiFactory.ServicePool.loginService

    fun login(email: String, password: String) {
        loginService.login(RequestLoginDTO(email, password))
            .enqueueUtil({ result ->
                _loginResult.value = result.result
                Timber.i("로그인 성공: $result")
            }, { code ->
                Timber.d("로그인 실패: $code")
            })
    }
}
