package com.activator.chatclone

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope

class ChatCloneDatabaseImpl {
    private var INSTANCE: ChatCloneDatabase? = null
    fun getDatabase(context: Context, coroutineScope: CoroutineScope): ChatCloneDatabase {
        return INSTANCE?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ChatCloneDatabase::class.java,
                "chatDatabase").build()
            INSTANCE = instance

            instance

        }
    }

}