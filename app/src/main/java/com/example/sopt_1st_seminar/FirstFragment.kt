package com.example.sopt_1st_seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_profile_view_pager.*
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {
    private lateinit var viewpagerAdapter: ProfileViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpagerAdapter = ProfileViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments = listOf(
            ProfileFirstFragment(),
            ProfileSecondFragment()
        )

        profile_tab_viewpager.adapter = viewpagerAdapter

        profile_tab.setupWithViewPager(profile_tab_viewpager)
        profile_tab.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }

    }



}