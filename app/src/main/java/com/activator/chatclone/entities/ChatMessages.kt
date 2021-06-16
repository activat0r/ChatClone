package com.activator.chatclone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity
data class ChatMessages(
    @PrimaryKey (autoGenerate = true)
    val id:Int,
    val roomId: String,
    val userId: String? = "",
    val isOutgoing : Boolean,
    val time: Long,
    val messageType: String,
    val stringMessage: String?,
    val imageMessage: Blob?,
)
