package com.activator.chatclone.main.chats.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val recyclerView:RecyclerView = binding.activityChatsRecyclerView
        val chatTitleTextView :TextView = binding.chatActivityActionBarTitle
        chatTitleTextView.text = intent.getStringExtra("name").toString()
        Log.d("ChatActivity","clicked on ${intent.getStringExtra("name").toString()}")
        val editText : EditText = binding.chatActivityEditText

        


    }




}