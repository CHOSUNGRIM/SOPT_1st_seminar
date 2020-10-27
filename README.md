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
**onActivityResult**를 통해 requestCode resultCode가 각각 *SignUpCode*와 *RESULT_OK*와 일치하면  
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
반복적으로 보여질 list item 의 모양을 만들어준다.  

2. 아이템에 대한 데이터 객체 만들기  
-*SampleDate.kt*을 만들었다.  
정보를 담을 변수를 선언해준다.  
```Kotlin
data class SampleData(
    val title : String,
    val subTitle : String,
    val date : String,
    val description : String
```

3. ViewHolder 만들기  
-Adapter에서 전달받은 데이터를 layout에 Bind 시켜준다.  
즉, itemLayout에 데이터를 넣어준다  
-*SampleViewHolder.kt*을 만들었다.  
**ProfileViewHolder** 클래스는 RecyclerView.ViewHolder을 상속 받고,  
**findViewById**를 통해 *profile_item_list.xml* 에서 정의한 View/ViewGroup을 요소로 가진다.  
**onBind** 함수는 실질적으로 데이터를 넣어주는 함수로 Adapter에서 호출할 예정이다.
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
-*SampleAdapter*라는 Adapter를 만들었다.  
RecyclerViewAdapter는 **context** 객체가 필요하므로 선언과 동시에 초기화 해준다.  
RecyclerView.Adapter를 상속 받으면서 <> 안에 해당 Adapter가 데이터를 전달할 ViewHolder를 작성한다. 여기서는 *SampleViewHolder*로 데이터를 전달한다.  
Adapter는 data를 가지고 있고 **onCreateViewHolder, getItemCount, onBindViewHolder**를 반드시 오버라이드 해줘야 한다.  
-**onCreateViewHolder** : 각 Item 마다 layout을 inflate 시키고 ViewHolder를 생성  
-**getItemCount** : RecyclerView로 보여줄 데이터의 전체 길이를 리턴  
-**onBindViewHolder** : ViewHolder의 onBind 함수를 호출하여 ViewHolder로 데이터를 전달    
```Kotlin
class SampleAdapter (private val context : Context) : RecyclerView.Adapter<SampleViewHolder>(){
    var data = listOf<SampleData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_item_list, parent, false)

        return SampleViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}
```

5. RecyclerView 배치  
-리스트가 보여질 RecyclerView 영역 설정  
```Kotlin
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/main_rcv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

6. 실제 적용  
-*SampleAdapter*를 lateinit으로 선언  
-adapter에 context 객체를 파라미터로 전달  
-RecyclerView의 adapter를 *SampleAdapter*로 세팅  
-배치 방향을 LinearLayoutManager로 설정 (세로 방향)  
```Kotlin
class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var sampleAdapter: SampleAdapter

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

         sampleAdapter = SampleAdapter(this)

         main_rcv.adapter = sampleAdapter
         main_rcv.layoutManager = LinearLayoutManager(this)
     }
}
```

7. Adapter 갱신  
-*sampleAdapter*에 데이터를 넣어주고  
**notifyDataSetChanged**를 이용해 데이터가 갱신된 것을 adapter에 알려준다.  
```Kotlin
sampleAdapter.data = mutableListOf(
             SampleData("이름","조성림","작성 날짜 : 2020.10.17","안녕하세요, 팟장님"),
             SampleData("나이","22","작성 날짜 : 2020.10.17","항상 유익한 세미나 감사합니다"),
             SampleData("파트","안드로이드","작성 날짜 : 2020.10.17","아주 조금... 어렵지만"),
             SampleData("GitHub","https://github.com/CHOSUNGRIM","작성 날짜 : 2020.10.17","열심히 할게요"),
             SampleData("SOPT","www.sopt.org","작성 날짜 : 2020.10.17","안드로이드 짱")
         )
         
sampleAdapter.notifyDataSetChanged()
```

##### 아이템을 클릭하면 아이템의 정보를 가지고 있는 상세화면으로 이동하기 위해  
1. 위에서 오버라이드 해준 onBindViewHolder 함수에서 itemView를 클릭했을 때 data를 Intent에 넣어준다  
-*model*이라는 변수를 선언하여 adapterdml data를 넣어주고, *gTitle, gSubtitle, gDate, gDescription* 변수에 각각 *model*에 있는 데이터를 넣어주었다.  
- **val intent = Intent(context, DetailActivity::class.java)** 를 이용해 액티비티를 전환해주고, **putExtra**를 통해 intent로 데이터 값을 전달해준다.  
```Kotlin
override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener {
            val model = data.get(position)
            var gTitle : String = model.title
            var gSubtitle : String = model.subTitle
            var gDate : String = model.date
            var gDescription : String = model.description

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("iTitle", gTitle)
            intent.putExtra("iSubtitle", gSubtitle)
            intent.putExtra("iDate", gDate)
            intent.putExtra("iDescription", gDescription)

            context.startActivity(intent)
        }
}
```

2. *DetailActivity.kt* 을 만들고 *aTitle, aSubtitle, aDate, aDescription* 이라는 변수를 선언한 후, **getStringExtra**를 이용해서 intent의 데이터 값을 넣어준다.  
-이 값들을 *activity_detail.xml*의 TextView에 **setText**를 이용하여 넣어준다.  
```Kotlin
val aTitle = intent.getStringExtra("iTitle")
val aSubtitle = intent.getStringExtra("iSubtitle")
val aDate = intent.getStringExtra("iDate")
val aDescription = intent.getStringExtra("iDescription")

detail_title_txt.setText(aTitle)
detail_subtitle_txt.setText(aSubtitle)
detail_date_txt.setText(aDate)
detail_description_txt.setText(aDescription)
```

#### 🟩 성장 과제1 ( GridLinearLayout )  
아이템을 격자 형태로 보여준다.  
GridLayoutManager(this, 가로줄 하나에 들어갈 아이템 수, RecyclerView.VERTICAL, false)
```Kotlin
main_rcv.layoutManager = GridLayoutManager(this,3,RecyclerView.VERTICAL,false)
```
[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)
