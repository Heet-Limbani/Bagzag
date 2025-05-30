package com.bagzag.app.ui.auth.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bagzag.app.ui.auth.fragment.WelcomeOneFragment
import com.bagzag.app.ui.auth.fragment.WelcomeThreeFragment
import com.bagzag.app.ui.auth.fragment.WelcomeTwoFragment

class AdapterWelcomeScreen(fragment:Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WelcomeOneFragment()
            1 -> WelcomeTwoFragment()
            2 -> WelcomeThreeFragment()
            else -> WelcomeOneFragment()
        }
    }
}