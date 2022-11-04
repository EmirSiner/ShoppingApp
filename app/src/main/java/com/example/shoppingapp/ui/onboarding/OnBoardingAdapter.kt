package com.example.shoppingapp.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class OnBoardingAdapter(
    fragment: Fragment,
    private val layouts: List<Int>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return layouts.size
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardingSupportFragment.newInstance(layouts[position])

    }
}