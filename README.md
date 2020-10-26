# SOPT_1st_seminar
## 1차 세미나 과제
### 구현 화면
#### 필수 과제 & 성장 과제 1
<img src="https://user-images.githubusercontent.com/72273531/97198951-c9cb4b00-17f2-11eb-94ee-b6b63882e95e.gif" width="300" height="620">

<pre>
<code>
btn_SignUp.setOnClickListener {
            if (SignUp_name_edt.text.isNullOrBlank() || SignUp_id_edt.text.isNullOrBlank() || SignUp_pw_edt.text.isNullOrBlank()) {
                Toast.makeText(this, "모든 칸에 내용을 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.putExtra("id",SignUp_id_edt.text.toString())
                intent.putExtra("pw",SignUp_pw_edt.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            } 
</code>
</pre>
