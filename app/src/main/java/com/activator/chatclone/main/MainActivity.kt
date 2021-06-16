package com.activator.chatclone.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.activator.chatclone.databinding.ActivityMainBinding
import com.activator.chatclone.main.ViewPagerAdapter
import com.activator.chatclone.main.calls.CallsFragment
import com.activator.chatclone.main.chats.ChatFragment
import com.activator.chatclone.main.status.StatusFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        val toolbar = binding.mainToolbar
        val fab = binding.floatButton
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter =  adapter
        viewPager.offscreenPageLimit = adapter.itemCount-1
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position){
                0 -> tab.text = "Chats"
                1 -> tab.text = "Status"
                else -> tab.text = "Calls"
            }
        }.attach()

        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
            Toast.makeText(this, "Selected ${it.title}", Toast.LENGTH_SHORT).show()
            true
        })

        fab.setOnClickListener(View.OnClickListener {
            when (adapter.getFragmentList()[viewPager.currentItem]){
                is ChatFragment -> ChatFragment().onFABClick(this)
                is CallsFragment -> CallsFragment().onFABClick(this)
                is StatusFragment -> StatusFragment().onFABClick(this)
            }
        })

    }

}