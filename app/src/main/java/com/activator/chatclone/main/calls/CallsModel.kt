package com.activator.chatclone.main.calls

import android.graphics.Bitmap


data class CallsModel(
    val name: String,
    val date: Long,
    val isIncoming: Boolean,
    val isPicked: Boolean,
    val isVoiceCall: Boolean,
    val image: Bitmap?
)
