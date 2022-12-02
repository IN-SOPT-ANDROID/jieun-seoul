package org.sopt.sample.presentation

import android.content.Intent
import android.graphics.Color.blue
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        observeId()
        observePw()

        binding.btnSignup.setOnClickListener {
            viewModel.signUp(
                binding.etEmail.text.toString(),
                binding.etId.text.toString(),
                binding.etPwd.text.toString()
            )
        }
        viewModel.signupResult.observe(this) {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        }
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun observePw() {
        viewModel.inputPwcheck.observe(this){
            if(it==true)
                binding.layoutEtPwd.boxStrokeColor=getColor(R.color.purple_500)
            else
                binding.layoutEtPwd.boxStrokeColor=getColor(android.R.color.holo_red_dark)
        }
    }

    private fun observeId() {
        viewModel.inputIdcheck.observe(this){
            if(it==true)
                binding.layoutEtId.boxStrokeColor=getColor(R.color.purple_500)
            else
                binding.layoutEtId.boxStrokeColor=getColor(android.R.color.holo_red_dark)
        }

    }

}
