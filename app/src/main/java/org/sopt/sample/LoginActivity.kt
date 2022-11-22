package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.remote.LoginViewModel
import org.sopt.sample.remote.RequestLoginDTO
import org.sopt.sample.remote.ResponseLoginDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginservice = ApiFactory.ServicePool.loginService
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginEvent()
        signupEvent()
    }

    private fun loginEvent() {
        binding.btnlogin.setOnClickListener() {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPasswd.text.toString()
            )
        }
        viewModel.loginResult.observe(this){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }
        viewModel.errorMessage.observe(this){
            Snackbar.make(binding.root,it,Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun signupEvent() { // 회원가입 Activity로 이동
        binding.btnsignin.setOnClickListener() {
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }

}