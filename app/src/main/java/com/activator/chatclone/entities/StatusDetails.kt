package com.activator.chatclone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatusDetails(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userId: String,
    val isRead: Boolean,
    val date: Long,
    val image: ByteArray
)

