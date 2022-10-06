package com.cookandroid.sopt1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignupBinding

class SignupActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signupButton.setOnClickListener {
            //아이디, 비밀번호 모두 조건을 만족 할 경우
            if(binding.signupId.length()>=6&&binding.signupId.length()<=10&&
                binding.signupPasswd.length()>=8&&binding.signupPasswd.length()<=12){
                val intent= Intent(this,SigninActivity::class.java)
                intent.putExtra("signup_id",binding.signupId.text.toString())
                intent.putExtra("signup_passwd",binding.signupPasswd.text.toString())
                intent.putExtra("signup_mbti",binding.signupMbti.text.toString())
                setResult(RESULT_OK,intent)
                if(!isFinishing) finish()
            }

            //아이디 or 비밀번호 or 둘 다 조건을 만족하지 않는 경우
            else{
                //비밀번호만 조건 만족X
                if(binding.signupId.length()>=6&&binding.signupId.length()<=10){
                    Snackbar.make(
                        binding.root, "비밀번호는 8글자 이상 12글자 이내로 작성하세요.", Snackbar.LENGTH_SHORT
                    ).show()
                }
                //아이디만 조건 만족X
                else if(binding.signupPasswd.length()>=8&&binding.signupPasswd.length()<=12){
                    Snackbar.make(
                        binding.root, "ID는 6글자 이상 10글자 이내로, 비밀번호는 8글자 이상 12글자 이내로 작성하세요.", Snackbar.LENGTH_SHORT
                    ).show()
                }
                //비밀번호,아이디 모두 조건 만족X
                else{
                    Snackbar.make(
                        binding.root, "ID는 6글자 이상 10글자 이내로, 비밀번호는 8글자 이상 12글자 이내로 작성하세요.", Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}