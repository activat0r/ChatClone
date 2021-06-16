package com.activator.chatclone.main.calls

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.*
import com.activator.chatclone.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CallsViewModel(application: Application) : AndroidViewModel(application) {

    private val callsListMutableLiveData: MutableLiveData<List<CallsModel>> = MutableLiveData()
    private val callsList: MutableList<CallsModel> = mutableListOf()


init {
    generateSampleList()
    callsListMutableLiveData.value = callsList
}

    fun getCallsList() : LiveData<List<CallsModel>> {
        return callsListMutableLiveData
    }

    private fun generateSampleList() {
        for (i in 0..10){
            Log.d("Main"," added $i th item ")

            viewModelScope.launch {

                withContext(Dispatchers.Main){
                    callsList.add(CallsModel("Name_$i",
                        Calendar.getInstance().timeInMillis - 100000,isIncoming = true,isPicked = false,isVoiceCall = true,null))
                }
            }
        }
    }

}