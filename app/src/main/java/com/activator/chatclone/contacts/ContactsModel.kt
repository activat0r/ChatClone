package com.activator.chatclone.contacts

import android.graphics.Bitmap

data class ContactsModel(
    val name: String,
    val profilePictureUrl: String?,
    val phoneNumber : String,
    val status : String
)
