package com.activator.chatclone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatMessages(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val roomId: String,
    val userId: String? = "1",
    val isOutgoing: Boolean,
    val time: Long,
    val messageType: String,
    val stringMessage: String?,
    val imagePath: String?,
){
}
