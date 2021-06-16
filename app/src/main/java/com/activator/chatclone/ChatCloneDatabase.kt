package com.activator.chatclone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.activator.chatclone.dao.ChatCloneDao
import com.activator.chatclone.entities.ChatMessages
import com.activator.chatclone.entities.RoomDetails
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ChatMessages::class, RoomDetails::class],version = 1)
abstract class ChatCloneDatabase :RoomDatabase(){

    companion object{
        @Volatile
        private var INSTANCE: ChatCloneDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope):ChatCloneDatabase{
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

    abstract fun chatDao():ChatCloneDao


}