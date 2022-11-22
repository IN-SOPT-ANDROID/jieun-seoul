package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginEvent()
        signupEvent()
    }

    private fun loginEvent() {
        binding.btnlogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPasswd.text.toString()
            )
        }
        viewModel.loginResult.observe(this) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun signupEvent() { // 회원가입 Activity로 이동
        binding.btnsignin.setOnClickListener() {
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }

    companion object {
        const val TAG = "tag"
    }
}
