package com.activator.chatclone.main.chats

data class ChatsModel(val image_url:String, val chat_title:String, val chat_text: String, var isRead: Boolean, var isMuted:Boolean, val date: Long)
