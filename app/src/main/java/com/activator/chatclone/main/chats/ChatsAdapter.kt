package com.activator.chatclone.main.chats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.R
import java.text.SimpleDateFormat
import java.util.*

class ChatsAdapter(private val listItems: MutableList<ChatsModel>, private val onItemClick: ChatItemClick,val context: Context): RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>(){

    class ChatViewHolder(view: View, itemClickListener:ChatItemClick):RecyclerView.ViewHolder(view),View.OnClickListener{
        val chatTitle:TextView = view.findViewById<TextView>(R.id.item_contact_name)
        val chatImage:ImageView = view.findViewById<ImageView>(R.id.item_contact_image)
        val chatMessage:TextView = view.findViewById<TextView>(R.id.item_contact_status)
        val chatTime: TextView = view.findViewById(R.id.item_chat_date)
        private val onItemClickListener = itemClickListener
        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }
        init {
            view.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chats, parent, false)

        return ChatViewHolder(view, onItemClick )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = listItems[position]
        holder.chatMessage.text = chatItem.chat_text
        holder.chatTitle.text = chatItem.chat_title
        holder.chatImage.setImageResource(R.drawable.gojo_avatar)
        val sdf = SimpleDateFormat("dd/MM/yy")
        val sdf2 = SimpleDateFormat("hh:mm")

        val today = GregorianCalendar()
        today.time = Date(System.currentTimeMillis())
        today.set(Calendar.MINUTE,0)
        today.set(Calendar.HOUR,0)
        today.set(Calendar.SECOND,0)
        today.set(Calendar.MILLISECOND,0)

        val messageTime = GregorianCalendar()
        messageTime.time = Date(chatItem.date)
        messageTime.set(Calendar.MINUTE,0)
        messageTime.set(Calendar.HOUR,0)
        messageTime.set(Calendar.SECOND,0)
        messageTime.set(Calendar.MILLISECOND,0)

        var date = sdf.format(Date(chatItem.date))

        if(today.time < messageTime.time) {
            date = sdf2.format(Date(chatItem.date))
        }

        holder.chatTime.text = date
    }


    override fun getItemCount(): Int {
        return listItems.size
    }


    interface ChatItemClick {
       fun onItemClick(position: Int)
    }

}