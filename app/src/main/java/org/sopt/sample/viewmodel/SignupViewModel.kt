package org.sopt.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.data.dto.response.ResponseSignupDto
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.util.enqueueUtil
import timber.log.Timber
import java.util.regex.Pattern

class SignupViewModel : ViewModel() {
    private val signupService = ApiFactory.ServicePool.signupService
    private val _signupResult = MutableLiveData<ResponseSignupDto?>()
    val signupResult: LiveData<ResponseSignupDto?>
        get() = _signupResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val inputPw = MutableLiveData<String>()
    val inputId = MutableLiveData<String>()


    val inputPwcheck: LiveData<Boolean> = Transformations.map(inputPw) { pw ->
        validPwcheck(pw)
    }
    val inputIdcheck: LiveData<Boolean> get() = Transformations.map(inputId) { id ->
        validIdcheck(id)
    }


    fun signUp(email: String, name: String, password: String) {
        signupService.signup(RequestSignupDto(email, name, password))
            .enqueueUtil({ result ->
                _signupResult.value = result
                Timber.i("회원가입 성공: $result")
            }, { code ->
                Timber.d("회원가입 실패: $code")
            })
    }


    // 아이디 형식 검증
    private fun validIdcheck(Id: String): Boolean {
        val pattern = Pattern.compile("""^(?=.*[0-9])(?=.*[a-zA-Z]).{6,10}$""")
        Log.d("hhk", pattern.matcher(Id).find().toString())
        return pattern.matcher(Id).find()
    }
    // 비밀번호 형식 검증
    private fun validPwcheck(Pw: String): Boolean {
        val pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$")
        Log.d("hhh", pattern.matcher(Pw).find().toString())
        return pattern.matcher(Pw).find()
    }

}