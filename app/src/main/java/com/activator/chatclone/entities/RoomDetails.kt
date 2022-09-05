package com.activator.chatclone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomDetails(
        @PrimaryKey
        val roomId: String,
        val isIndividual:Boolean,
        val isMuted: Boolean,
        val userId: String?,
        val roomName: String
)
