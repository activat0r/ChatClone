package com.activator.chatclone.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.activator.chatclone.main.calls.CallsFragment
import com.activator.chatclone.main.chats.ChatFragment
import com.activator.chatclone.main.status.StatusFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    private val fragmentList = listOf<Fragment>(ChatFragment(),StatusFragment(),CallsFragment())

    fun getFragmentList(): List<Fragment>{
        return fragmentList
    }

    override fun createFragment(position: Int): Fragment {
      return  fragmentList[position]
    }

}