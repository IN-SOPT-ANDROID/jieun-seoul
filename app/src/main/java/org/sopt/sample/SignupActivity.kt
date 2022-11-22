package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.remote.ApiFactory
import org.sopt.sample.data.dto.response.RequestSignupDTO
import org.sopt.sample.data.dto.response.ResponseBase
import org.sopt.sample.data.dto.response.ResponseSignupDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private val signupService = ApiFactory.ServicePool.signupService
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
        with(binding)
        {
            signupButton.setOnClickListener {
                // 회원가입이 성공한 id와 pw를 서버에 전달하고 loginActivity로 이동한다.
                signupService.signup(
                    RequestSignupDTO(
                        signupEmail.text.toString(),
                        signupPasswd.text.toString(),
                        signupId.text.toString()
                    )
                    //서버통신을 할 때, 메인 스레드가 아닌 별도의 스레드가 작업을 처리하도록 하는데,
                    //enqueue를 통해 큐에 넣어 다른 스레드가 처리하도록 한다.
                ).enqueue(object : Callback<ResponseBase<ResponseSignupDTO>>{

                    override fun onResponse(
                        call: Call<ResponseBase<ResponseSignupDTO>>,
                        response: Response<ResponseBase<ResponseSignupDTO>>
                    ) {
                        Toast.makeText(this@SignupActivity, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                    }

                    override fun onFailure(
                        call: Call<ResponseBase<ResponseSignupDTO>>,
                        t: Throwable
                    ) {
                        Toast.makeText(this@SignupActivity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }

        }
    }
}
