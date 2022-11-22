package org.sopt.sample.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult=MutableLiveData<ResponseLoginDTO>()
    val loginResult:LiveData<ResponseLoginDTO>
        get()=_loginResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val loginService = ApiFactory.ServicePool.loginService

    fun login(email: String, password: String){
        loginService.login(RequestLoginDTO(email,password))
            .enqueue(object : Callback<ResponseLoginDTO>{
                override fun onResponse(
                    call: Call<ResponseLoginDTO>,
                    response: Response<ResponseLoginDTO>
                ) {
                    if(response.isSuccessful){
                        _loginResult.value=response.body()
                    }else{
                        _errorMessage.value="기본값"
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                    Log.e("LOGIN_FAIL","cause : "+t.cause)
                    Log.e("LOGIN_FAIL","cause : "+t.message)
                }
            })
    }
}