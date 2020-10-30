package com.example.sopt_1st_seminar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    val SignUpCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadData()

        SignUp_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SignUpCode)
        }

        Login_btn.setOnClickListener {
            saveData()

            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        if (!(login_id_edt.text.isNullOrBlank() || login_pw_edt.text.isNullOrBlank())){
            Toast.makeText(this, "자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
            finish()
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