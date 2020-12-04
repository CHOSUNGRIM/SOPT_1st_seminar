package com.example.sopt_1st_seminar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_SignUp.setOnClickListener {
            val call : Call<SignupResponseData> = SoptServiceImpl.service.postSignup(
                SignupRequestData(
                    SignUp_id_edt.text.toString(),
                    SignUp_pw_edt.text.toString(),
                    SignUp_name_edt.text.toString()
                )
            )

            call.enqueue(object : Callback<SignupResponseData> {

                override fun onFailure(call: Call<SignupResponseData>, t: Throwable) {
                    // 통신 실패 로직
                }

                override fun onResponse(
                    call: Call<SignupResponseData>,
                    response: Response<SignupResponseData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {

                            Log.d("status",response.body()!!.status.toString())
                            val intent = Intent()
                            intent.putExtra("id",SignUp_id_edt.text.toString())
                            intent.putExtra("pw",SignUp_pw_edt.text.toString())
                            setResult(Activity.RESULT_OK,intent)
                            finish()


                            if (SignUp_name_edt.text.isNullOrBlank() || SignUp_id_edt.text.isNullOrBlank() || SignUp_pw_edt.text.isNullOrBlank()) {
                                Toast.makeText(this@SignUpActivity, "모든 칸에 내용을 입력해 주세요", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()

                            }
                        } ?: showError(response.errorBody())
                }

                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(this@SignUpActivity, "이미 존재하는 이메일이거나 빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
                }
            })
            }
        }
    }