package com.activator.chatclone.main.chats

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.R

class ChatsAdapter(private val listItems: MutableList<ChatsModel>, val context: Context): RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View):RecyclerView.ViewHolder(view){
        val chatTitle:TextView = view.findViewById<TextView>(R.id.item_chat_name)
        val chatImage:ImageView = view.findViewById<ImageView>(R.id.item_chat_image)
        val chatMessage:TextView = view.findViewById<TextView>(R.id.item_chat_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chats, parent, false)

        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = listItems[position]
        holder.chatMessage.text = chatItem.chat_text
        holder.chatTitle.text = chatItem.chat_title
        holder.chatImage.setImageResource(R.drawable.gojo_avatar)

        Log.d("Adapter","text ${chatItem.chat_title}")
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}