package com.activator.chatclone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactDetails(
    @PrimaryKey
    val contactId : Int,
    val contactIdFromAndroid : Int,
    val contactName : String,
    val contactStatus : String?,
    val contactNumber : Long,
    val contactAvatar : String?
) {
}