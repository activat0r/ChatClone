package com.activator.chatclone.entities

import androidx.room.Entity

@Entity
data class RoomDetails(
        val roomId: String,
        val isIndividual:Boolean,
        val isMuted: Boolean,
        val userId: String?,
        val roomName: String
)
