package org.sopt.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.dto.request.RequestSignupDTO
import org.sopt.sample.data.dto.response.ResponseSignupDTO
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.util.enqueueUtil
import timber.log.Timber
import java.util.regex.Pattern

class SignupViewModel : ViewModel() {
    //TODO 코드 이해
    val Emailpattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
    val Pwpattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"

    private val _signupResult = MutableLiveData<ResponseSignupDTO?>()
    val signupResult: LiveData<ResponseSignupDTO?>
        get() = _signupResult
    private val signupService = ApiFactory.ServicePool.signupService
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    //TODO 코드 이해
    val inputEmail = MutableLiveData<String>().apply { value = "" }
    val inputPw = MutableLiveData<String>().apply { value = "" }
    val inputId = MutableLiveData<String>().apply { value = "" }

    val inputEmailcheck : LiveData<Boolean> = Transformations.map(inputEmail) { email ->
        Log.d("hi", email.toString())
        validEmailcheck(email)
    } // map을 활용해 실시간으로 변하는 input을 검증하여 Boolean 변수를 반환

    val inputPwcheck : LiveData<Boolean> = Transformations.map(inputPw) { pw ->
        Log.d("hi", pw.toString())
        validPwcheck(pw)
    }


    fun signUp(email: String, name: String, password: String) {
        signupService.signup(RequestSignupDTO(email, name, password))
            .enqueueUtil({ result ->
                _signupResult.value = result
                Timber.i("회원가입 성공: $result")
            }, { code ->
                Timber.d("회원가입 실패: $code")
            })
    }

    //TODO 코드 이해
    private fun validEmailcheck(Email: String): Boolean { // 이메일 형식 검증
        val pattern = Pattern.compile(Emailpattern)
        return pattern.matcher(Email).find()
    }

    private fun validPwcheck(Pw: String): Boolean { // 비밀번호 형식 검증
        val pattern = Pattern.compile(Pwpattern)
        return pattern.matcher(Pw).find()
    }
}