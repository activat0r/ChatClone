package com.activator.chatclone

import android.app.Application
import com.activator.chatclone.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CatCloneApp :Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        ChatCloneDatabase.getDatabase(this,applicationScope)
    }
    val chatRepository by lazy {
        MainRepository(database.chatDao())
    }

}