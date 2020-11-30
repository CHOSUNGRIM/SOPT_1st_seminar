package com.example.sopt_1st_seminar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    val SignUpCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Login_btn.setOnClickListener {
            val email = login_id_edt.text.toString()
            val password = login_pw_edt.text.toString()
            val call : Call<LoginResponseData> = SoptServiceImpl.service.postLogin(
                LoginRequestData(email = email, password = password)
            )

            call.enqueue(object : Callback<LoginResponseData> {
                override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                    // 통신 실패 로직
                }

                override fun onResponse(
                    call: Call<LoginResponseData>,
                    response: Response<LoginResponseData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->
                            saveData()

                            val intent = Intent(this@LoginActivity, ViewPagerActivity::class.java)
                            startActivity(intent)

                            if (!(login_id_edt.text.isNullOrBlank() || login_pw_edt.text.isNullOrBlank())){
                                Toast.makeText(this@LoginActivity, "반갑습니다.", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, ViewPagerActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                            loadData()
                        } ?: showError(response.errorBody())
                }

                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(this@LoginActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        loadData()

        SignUp_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SignUpCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==SignUpCode && resultCode== Activity.RESULT_OK){
            val id = data?.getStringExtra("id") ?: return
            val pw = data.getStringExtra("pw")

            login_id_edt.setText(id)
            login_pw_edt.setText(pw)

        }
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref",0)
        login_id_edt.setText(pref.getString("saveId",""))
        login_pw_edt.setText(pref.getString("savePw",""))
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("saveId", login_id_edt.text.toString())
        edit.putString("savePw", login_pw_edt.text.toString())
        edit.apply()
    }

    override fun onDestroy() {
        super.onDestroy()

        saveData()
    }




}