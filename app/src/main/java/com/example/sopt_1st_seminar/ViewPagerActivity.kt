package com.example.sopt_1st_seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlin.properties.Delegates

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
    }
}