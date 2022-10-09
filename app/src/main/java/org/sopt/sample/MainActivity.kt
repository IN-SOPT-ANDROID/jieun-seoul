package org.sopt.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.sopt1.SignupActivity
import org.sopt.sample.databinding.ActivityIntroduceBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewId.text= intent.getStringExtra(SignupActivity.id)
        binding.textViewMbti.text= intent.getStringExtra(SignupActivity.mbti)
    }
}
