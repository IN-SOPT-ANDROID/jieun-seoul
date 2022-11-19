package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.remote.RequestLoginDTO
import org.sopt.sample.remote.ResponseLoginDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginservice = ApiFactory.ServicePool.loginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginEvent()
        signupEvent()
    }

    private fun loginEvent() {
        binding.btnlogin.setOnClickListener() {
            loginservice.login(
                RequestLoginDTO(
                    binding.etEmail.text.toString(),
                    binding.etPasswd.text.toString()
                )
            ).enqueue(object : Callback<ResponseLoginDTO> {
                override fun onResponse(
                    call: Call<ResponseLoginDTO>,
                    response: Response<ResponseLoginDTO>
                ) {
                    val result = response.body()
                    val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    Toast.makeText(this@LoginActivity, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(mainIntent)
                }

                override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.2", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun signupEvent() { // 회원가입 Activity로 이동
        binding.btnsignin.setOnClickListener() {
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }

}