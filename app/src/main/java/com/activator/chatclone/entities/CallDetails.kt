package com.activator.chatclone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CallDetails(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userID: String,
    val time: Long,
    val isIncoming: Boolean,
    val isVoice: Boolean,
    val isPicked:Boolean
)
