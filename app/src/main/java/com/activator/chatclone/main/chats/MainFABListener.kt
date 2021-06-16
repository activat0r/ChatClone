package com.activator.chatclone.main.chats

import android.content.Context
import android.content.Intent
import android.view.View
import com.activator.chatclone.contacts.ContactsActivity

class MainFABListener(context: Context) : View.OnClickListener {
    private val mContext = context

    override fun onClick(v: View?) {
        val intent:Intent = Intent(mContext,ContactsActivity::class.java)
    }
}