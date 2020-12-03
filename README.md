# 💚SOPT_27th_Android💚
* [1차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar/blob/master/README.md#1%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)
* [2차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#2%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)
* [3차 세미나 과제](https://github.com/CHOSUNGRIM/SOPT_1st_seminar/blob/master/README.md#3%EC%B0%A8-%EC%84%B8%EB%AF%B8%EB%82%98-%EA%B3%BC%EC%A0%9C)

---
## 🤍1차 세미나 과제🤍
### 📲 구현 화면
#### 필수 과제 & 성장 과제 1 & 성장 과제 2
<img src="https://user-images.githubusercontent.com/72273531/97657964-8d2b7800-1aae-11eb-8927-66d0e42eb47a.gif" width="230" height="500">


#### 🟩 필수 과제 ( setOnClickListener & ToastMessage & 화면 이동 ) - 2020.10.18 완료  
*SignUpActivity* 에서 회원가입 버튼을 눌렀을 때,  
-EditTextView에 데이터가 모두 들어있으면 회원가입이 완료되었다는 메시지 표시  
-모두 들어있지 않으면 모든 칸에 내용을 입력하라는 메시지 표시  
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



#### 🟩 성장 과제1 ( startActivityForResult() )  - 2020.10.25 완료  
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


#### 🟩 성장 과제2 ( 자동 로그인 )  - 2020.10.30 완료  
회원 가입 시 작성한 아이디와 로그인을 저장했다가 다음 로그인 때 자동로그인이 되게 한다.  

1. 아이디와 로그인을 저장하는 함수인 saveData() 와 저장한 데이터를 불러오는 loadData() 라는 함수를 만든다.  
```Kotlin
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
```

2. *LoginActivity*의 *Login_btn*을 눌렀을 때, saveData()를 호출해서 데이터를 저장하고 다음 액티비티로 넘어가게 한다.  
```Kotlin
Login_btn.setOnClickListener {
    saveData()

    val intent = Intent(this, RecyclerViewActivity::class.java)
    startActivity(intent)
}
```

3. *LoginActivity*가 실행될 때, 아이디와 비밀번호 EditTextView에 값이 저장되어 있으면 자동 로그인이 되고 다음 액티비티로 넘어가게 한다.  
```Kotlin
if (!(login_id_edt.text.isNullOrBlank() || login_pw_edt.text.isNullOrBlank())){
    Toast.makeText(this, "자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, RecyclerViewActivity::class.java)
    startActivity(intent)
    finish()
}
```

[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)



---
## 🤍2차 세미나 과제🤍
### 📲구현 화면
#### 필수 과제 & 성장 과제 1  
<img src="https://user-images.githubusercontent.com/72273531/97203284-f2097880-17f7-11eb-95fd-28c49254e76d.jpg" width="600" height="400">

#### 🟩 필수 과제 ( RecyclerView ) - 2020.10.18 완료  
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

#### 🟩 성장 과제1 ( GridLinearLayout ) - 2020.10.18 완료  
아이템을 격자 형태로 보여준다.  
GridLayoutManager(this, 가로줄 하나에 들어갈 아이템 수, RecyclerView.VERTICAL, false)
```Kotlin
main_rcv.layoutManager = GridLayoutManager(this,3,RecyclerView.VERTICAL,false)
```
[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)



---
## 🤍3차 세미나 과제🤍
### 📲 구현 화면
#### 필수 과제
<img src="https://user-images.githubusercontent.com/72273531/98011336-0e7e6400-1e3b-11eb-85b0-a3f9d3f3686f.gif" width="230" height="500">

#### 🟩 필수 과제 ( Fragment & ViewPager & BottomNavigation & TabLayout ) - 2020.11.04 완료  
* **Fragment**  
-하나의 액티비티가 여러 개의 화면을 가지도록 함    
-다른 액티비티에서도 사용 가능  
-액티비티가 관리  

* **ViewPager**  
-하나의 화면 안에서 여러가지 화면(프래그먼트로 만들어주면 됨)을 슬라이드 형식으로 보여줄 때 사용  
-하단 탭, 상단 탭과 연동하여 사용  
1. *ViewPagerActivity*를 만들고 해당 xml 파일에서 ViewPager가 보여질 영역을 설정해준다.  
2. ViewPager의 Adapter를 만들어준다.  
-ViewPager의 Adapter는 **FragmentManager**를 필요로 하고 **FragmentStatePagerAdapter**를 상속받는다. 그리고 **getItem**과 **getCount** 메소드를 오버라이드 해줘야 한다.  
```Kotlin
class ViewPagerAdapter (fm : FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position){
        0 -> FirstFragment()
        1 -> SecondFragment()
        2 -> ThirdFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = fragments.size
}
```
3. *ViewPagerActivity* 에서 *ViewPagerAdapter*를 선언하고 **supportFragment**로 프래그먼트 매니저를 불러온 후, Adapter의 fragments 변수에 프래그먼트를 생성해서 넣어준다. 그리고 선언한  Adapter를 장착해준다.  
```Kotlin
class ViewPagerActivity : AppCompatActivity() {
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        sample_bottom_viewpager.adapter = viewpagerAdapter
}
```

* **BottomNavigation**  
-ViewPager와 연동하여 서브 화면들을 전환  
-화면이 3개 이상일 때 사용하는 것을 권장  
1. 하단 탭에 사용할 아이콘을 **Vector Asset**을 통해 만든다. 
2. *menu* 이름으로 Directory를 생성하고 xml에서 item태그를 생성한다.  
3. 하단 탭을 사용할 액티비티의 xml파일에서 BottomNavigation이 사용될 영역을 설정하고 앞에서 만든 menu 파일을 적용해준다.  
```Kotlin
app:menu="@menu/bottom_menu"
```
4. *ViewPagerActivity*에서 각 탭을 클릭했을 때의 이벤트 처리 리스너를 설정해준다. **setOnNavigationItemSelectedListener**  
*menu.xml*의 item의 id를 통해 뷰페이저의 currentItem을 조작한다.  
```Kotlin
sample_bottom_navi.setOnNavigationItemSelectedListener {
   var index by Delegates.notNull<Int>()

   when(it.itemId){
       R.id.menu_account -> index = 0
       R.id.menu_comment -> index = 1
       R.id.menu_cloud -> index = 2
    }
    sample_bottom_viewpager.currentItem = index
    true
            }
```

5. 슬라이드 하고 나서도 하단 탭이 변경되도록 하기 위해 페이지 변경에 관한 리스너를 설정해준다. 
```Kotlin
sample_bottom_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
     ) {}

     override fun onPageSelected(position: Int) {
         sample_bottom_navi.menu.getItem(position).isChecked = true
     }
})
```

* **ViewPager의 *FirstFragment*에 TabLayout을 이용하여 프로필 화면 만들기**
1. 프로필 화면에 사용될 TabLayout에 연동할 *ProfileViewPagerActivity*와 *ProfileViewPagerAdapter*를 만들어준다.  
2. *fragment_first.xml*에 TabLayout과 ProfileViewPager영역을 설정해준다.  
3. *FirstFragment.kt*의 **onViewCreated**에서 **childFragmentManager**를 이용하여 *ProfileViewPagerAdapter*를 선언한다. Adapter에 프래그먼트를 넣어주고 ProfileViewPager에 Adapter를 적용해준다.  
```Kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpagerAdapter = ProfileViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments = listOf(
            ProfileFirstFragment(),
            ProfileSecondFragment()
        )

        profile_tab_viewpager.adapter = viewpagerAdapter
}
```
4. *FirstFragment.kt*의 **onViewCreated**에서 **setupWithViewPager**를 이용하여 탭레이아웃에 뷰페이저를 연동해준 후에, **getTabAt**을 이용하여 인덱스와 일치하는 탭 아이템 title을 작성해준다.  
```Kotlin
profile_tab.setupWithViewPager(profile_tab_viewpager)
        profile_tab.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
```
* **ViewPager의 *SecondFragment*에 RecyclerView 넣기**
1. *fragment_second.xml*에서 RecyclerView가 보여질 영역을 설정해준다.  
2. *SecondFragment.kt*에서 **initView**함수를 호출하여 RecyclerView와 RecyclerViewAdapter를 적용해준다.  
```Kotlin
fun initView(view:View){
    sampleAdapter = SampleAdapter(view.context)
    recyclerView = view.findViewById(R.id.main_rcv)
    main_rcv.layoutManager = LinearLayoutManager(context)
    main_rcv.adapter = sampleAdapter

    sampleAdapter.data = mutableListOf(
        SampleData("이름","조성림","작성 날짜 : 2020.10.17","안녕하세요, 팟장님"),
        SampleData("나이","22","작성 날짜 : 2020.10.17","항상 유익한 세미나 감사합니다"),
        SampleData("파트","안드로이드","작성 날짜 : 2020.10.17","아주 조금... 어렵지만"),
        SampleData("GitHub","https://github.com/CHOSUNGRIM","작성 날짜 : 2020.10.17","열심히 할게요"),
        SampleData("SOPT","www.sopt.org","작성 날짜 : 2020.10.17","안드로이드 짱")
    )

    sampleAdapter.notifyDataSetChanged()
}
```
3. **onViewCreated**에서 initview 함수를 실행해준다.  
```Kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initView(view)
}
```



[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)



---
## 🤍6차 세미나 과제🤍
### 📲 구현 화면
#### 필수 과제

[🔝](https://github.com/CHOSUNGRIM/SOPT_1st_seminar#sopt_27th_android)
