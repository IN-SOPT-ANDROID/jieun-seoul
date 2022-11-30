package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    private val signupService = ApiFactory.ServicePool.signupService
    private val viewModel by viewModels<SignupViewModel>()
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupButton.isEnabled = false
        checkAllInputActivated()
        clickEvent()
    }

    // 3개의 input의 공백 여부를 판단하는 함수
    private fun inputNotEmpty(): Boolean {
        with(binding) {
            if (!signupEmail.text.toString().isBlank() && !signupId.text.toString()
                    .isBlank() && !signupPasswd.text.toString().isBlank()
            ) {
                return true
            }
            return false
        }
    }

    // 3개의 input이 모두 공백이 아닌 것이 확인되면 버튼 활성화
    private fun checkAllInputActivated() {
        with(binding)
        {
            signupId.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    signupButton.isEnabled = inputNotEmpty()
                }
            })
            signupEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    signupButton.isEnabled = inputNotEmpty()
                }
            })
            signupPasswd.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    signupButton.isEnabled = inputNotEmpty()
                }
            })
        }

    }

    //회원가입 버튼 클릭 이벤트
    private fun clickEvent() {

        binding.signupButton.setOnClickListener {
            viewModel.signUp(
                binding.signupEmail.text.toString(),
                binding.signupId.text.toString(),
                binding.signupPasswd.text.toString()
            )
        }
        viewModel.signupResult.observe(this) {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        }
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }

    }
}
