package com.cookandroid.sopt1
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.MainActivity
import org.sopt.sample.databinding.ActivitySampleBinding

class SigninActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySampleBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private var rid:String ?= null
    private var rpasswd:String ?= null
    private var rmbti:String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SampleActivity에서 전달받은 id,비밀번호,mbti값 전달받아 변수에 저장
        getResultText=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode== RESULT_OK) {
                Snackbar.make(
                    binding.root,"회원가입이 완료되었습니다.",Snackbar.LENGTH_SHORT).show()
                rid = result.data?.getStringExtra("signup_id")
                rpasswd = result.data?.getStringExtra("signup_passwd")
                rmbti = result.data?.getStringExtra("signup_mbti")
            }


        }
        binding.btnlogin.setOnClickListener {
            //SampleActivity에서 전달받은 id,비밀번호 값과 입력한 id,비밀번호가 일치하는 경우
            if(binding.txtIdEdttext.text.toString()==rid && binding.txtPaswdEdttext.text.toString()==rpasswd){
                //로그인 성공 Toast 띄우기
                Toast.makeText(this,"로그인에 성공했습니다.",Toast.LENGTH_SHORT).show()
                //메인화면으로 화면 전환 + id, mbti값 전달
                val intent=Intent(this, MainActivity::class.java)
                intent.putExtra("sample_id",rid)
                intent.putExtra("sample_mbti",rmbti)
                startActivity(intent)
                if(!isFinishing) finish()
            }
            else{
                Snackbar.make(
                    binding.root,"ID와 비밀번호를 확인하세요.",Snackbar.LENGTH_SHORT).show()

            }
        }
        binding.btnsignin.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            getResultText.launch(intent)
        }
    }
}