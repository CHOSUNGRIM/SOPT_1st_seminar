# 💚SOPT_27th_Android💚
* [1차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar/blob/master/README.md#1%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)
* [2차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#2%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)


## ✔1차 세미나 과제
### 📲구현 화면
#### 필수 과제 & 성장 과제 1 
<img src="https://user-images.githubusercontent.com/72273531/97198951-c9cb4b00-17f2-11eb-94ee-b6b63882e95e.gif" width="230" height="500">


* ToastMessage  
  - *SignUpActivity* 에서 회원가입 버튼을 눌렀을 때,  
EditTextView에 데이터가 모두 들어있으면 회원가입이 완료되었다는 메시지 표시  
모두 들어있지 않으면 모든 칸에 내용을 입력하라는 메시지 표시  


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


* startActivityForResult()  
회원 가입에 성공했을 때, *SignUpActivity* 에서 입력 받은 아이디와 비밀번호를 로그인 화면에 입력해준다.  

  - request code로 *SignUpCode* 를 100이라 한다.

<pre>
<code>
val SignUpCode = 100
</code>
</pre>


  - *loginActivity*에서 *SignUpActivity*를 **startAcrivityForResult**를 이용하여 불러낸다.  
startActivityForResult는 불러낸 액티비티가 종료될 때 결과값을 가지고 돌아옴

<pre>
<code>
SignUp_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SignUpCode)
}
</code>
</pre>


  - override 해줌..
<pre>
<code>
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==SignUpCode && resultCode== Activity.RESULT_OK){
            val id = data?.getStringExtra("id") ?: return
            val pw = data.getStringExtra("pw")

            login_id_edt.setText(id)
            login_pw_edt.setText(pw)

        }
}
</code>
</pre>


## ✔2차 세미나 과제
### 📲구현 화면
#### 필수 과제 & 성장 과제 1
<img src="https://user-images.githubusercontent.com/72273531/97203284-f2097880-17f7-11eb-95fd-28c49254e76d.jpg" width="600" height="400">
