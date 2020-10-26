# 💚SOPT_27th_Android💚
* [1차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar/blob/master/README.md#1%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)
* [2차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#2%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)

---
## 🤍1차 세미나 과제🤍
### 📲 구현 화면
#### 필수 과제 & 성장 과제 1 
<img src="https://user-images.githubusercontent.com/72273531/97198951-c9cb4b00-17f2-11eb-94ee-b6b63882e95e.gif" width="230" height="500">


#### 🟩 필수 과제 ( setOnClickListener & ToastMessage & 화면 이동 )  
*SignUpActivity* 에서 회원가입 버튼을 눌렀을 때,  
EditTextView에 데이터가 모두 들어있으면 회원가입이 완료되었다는 메시지 표시  
모두 들어있지 않으면 모든 칸에 내용을 입력하라는 메시지 표시  
```Kotlin
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
```



##### 🟩 성장 과제1 ( startActivityForResult() )  
회원 가입에 성공했을 때, *SignUpActivity* 에서 입력 받은 아이디와 비밀번호를 로그인 화면에 입력해준다.  

1. request code로 *SignUpCode* 를 100이라 한다.
```Kotlin
val SignUpCode = 100
```

2. *loginActivity*에서 **startAcrivityForResult**를 통해 *SignUpActivity*를 불러낸다.  
startActivityForResult는 불러낸 액티비티가 종료될 때 결과값을 가지고 돌아온다.
```Kotlin
SignUp_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SignUpCode)
}
```


3. 불러낸 액티비티인 *SignUpActivity*에서 회원가입에 성공하면  
**putExtra**를 통해 EditTextView를 통해 받은 데이터를 intent에 넣어주고  
**setResult**를 통해 *RESULT_OK* 와 데이터가 담긴 intent를 넣어준 후에
**finish**를 통해 불러낸 액티비티를 종료하고 *LoginActivity*로 돌아간다.
```Kotlin
val intent = Intent()
intent.putExtra("id",SignUp_id_edt.text.toString())
intent.putExtra("pw",SignUp_pw_edt.text.toString())
setResult(Activity.RESULT_OK,intent)
finish()
```


4. 돌아온 *LoginActivity*에서  
**onActivityResult**를 통해 requestCodedhk resultCode가 각각 *SignUpCode*와 *RESULT_OK*와 일치하면  
**getStringExtra**를 통해 변수에 데이터 값을 넣어주고,  
**setText**를 통해 EditTextView에 데이터를 넣어준다.
```Kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==SignUpCode && resultCode== Activity.RESULT_OK){
            val id = data?.getStringExtra("id") ?: return
            val pw = data.getStringExtra("pw")

            login_id_edt.setText(id)
            login_pw_edt.setText(pw)

        }
}
```
[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)

---
## 🤍2차 세미나 과제🤍
### 📲구현 화면
#### 필수 과제 & 성장 과제 1
<img src="https://user-images.githubusercontent.com/72273531/97203284-f2097880-17f7-11eb-95fd-28c49254e76d.jpg" width="600" height="400">

#### 🟩 필수 과제 ( RecyclerView )  
동일한 형태의 뷰 + 다른 데이터를 다량 보여줄 때 사용  
커스텀이 편함  
가로 / 세로 방향 지원 - LinearLayoutManager  
격자 방향 지원 - GridLayoutManager  
ItemAnimator를 이용한 애니메이션

1. 아이템 xml 작성  
-*profile_item_list.xml*을 만들었다.

2. 아이템에 대한 데이터 객체 만들기  
-*SampleDate.kt*을 만들었다.
```Kotlin
data class SampleData(
    val title : String,
    val subTitle : String,
    val date : String,
    val description : String
```
3. ViewHolder 만들기  
-데이터를 뷰에 뿌려주는 역할  
-*SampleViewHolder.kt*을 만들었다.
```Kotlin
class SampleViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
    private val title : TextView = itemView.findViewById(R.id.item_title)
    private val subTitle : TextView = itemView.findViewById(R.id.item_subtitle)

    fun onBind(data : SampleData){
        title.text = data.title
        subTitle.text = data.subTitle
    }
}
```
4. Adapter 만들기
-데이터를 각 아이템들에게 전달하는 역할
5. RecyclerView 배치
6. 배치 방향 확인
7. Adapter 갱신

#### 🟩 성장 과제1 ( GridLinearLayout )  
아이템을 격자 형태로 보여준다.  
GridLayoutManager(this, 가로줄 하나에 들어갈 아이템 수, RecyclerView.VERTICAL, false)
```Kotlin
main_rcv.layoutManager = GridLayoutManager(this,3,RecyclerView.VERTICAL,false)
```
[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)
