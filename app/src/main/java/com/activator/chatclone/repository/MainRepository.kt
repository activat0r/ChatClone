package com.activator.chatclone.repository

import android.content.Context
import androidx.room.Room
import com.activator.chatclone.ChatCloneDatabase
import com.activator.chatclone.dao.ChatCloneDao
import com.activator.chatclone.main.entities.ChatScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


class MainRepository(private val chatCloneDao: ChatCloneDao) {
    val contactList : List<ChatScreen> = chatCloneDao.getMessagedContacts()
}