package com.example.sopt_1st_seminar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.lang.IllegalStateException

class ProfileViewPagerAdapter(fm : FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position){
        0 -> ProfileFirstFragment()
        1 -> ProfileSecondFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = fragments.size
}