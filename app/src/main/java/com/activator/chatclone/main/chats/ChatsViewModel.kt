package com.activator.chatclone.main.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.activator.chatclone.main.entities.ChatScreen
import com.activator.chatclone.repository.MainRepository

class ChatsViewModel(private val repo: MainRepository) : ViewModel() {
    val contactList: List<ChatScreen> = repo.contactList

    class ChatsViewModelFactory(private val repository: MainRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ChatsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}