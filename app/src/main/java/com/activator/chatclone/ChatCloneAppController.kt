package com.activator.chatclone

import android.app.Application
import android.content.Context
import android.util.Log
import com.activator.chatclone.entities.ChatMessages
import com.activator.chatclone.entities.RoomDetails
import com.activator.chatclone.repository.MainRepository
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class ChatCloneAppController :Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val TEST_PREF = "TEST_CHATCLONE"
    private val database by lazy {
        ChatCloneDatabaseImpl().getDatabase(this,applicationScope)
    }
    val chatRepository by lazy {
        MainRepository(database.chatDao())
    }

    override fun onCreate() {
        super.onCreate()
        setDBForTest(applicationContext)
    }

    private fun setDBForTest(context: Context){
        val isFirstTime = context.getSharedPreferences(TEST_PREF,Context.MODE_PRIVATE).getBoolean("isFirstInit",true)

        if(isFirstTime){
            CoroutineScope(Dispatchers.IO).launch{
                val dbInstance = ChatCloneDatabaseImpl().getDatabase(context, CoroutineScope(Dispatchers.IO))
                for (person in 0..3){
                    val rooms = RoomDetails(roomId = "1:1$person", isIndividual = true, userId = "1$person", roomName = "Person : $person", isMuted = false )
                    dbInstance.chatDao().insertNewRoomDetails(rooms)

                    for (message in 0..10){
                        val messages = ChatMessages(roomId = "1:1$person", userId = "1$person", isOutgoing = (message/2) == 0 , time = System.currentTimeMillis() - (1000 * 60  * 60 * 28), messageType = "STRING", stringMessage = "ROOM MESSAGE $message FOR CONTACT 1$person", imagePath = null )
                        dbInstance.chatDao().insertNewChat(messages)
                    }
                }
            }
        }

        applicationContext.getSharedPreferences(TEST_PREF,Context.MODE_PRIVATE).edit().putBoolean("isFirstInit",false).apply()


    }
}