package com.activator.chatclone.repository

import androidx.room.Room
import com.activator.chatclone.dao.ChatCloneDao
import com.activator.chatclone.main.entities.ChatScreen
import kotlinx.coroutines.flow.Flow


class MainRepository(private val chatCloneDao: ChatCloneDao) {
    val contactList : Flow<List<ChatScreen>> = chatCloneDao.getMessagedContacts()

}