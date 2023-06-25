package com.activator.chatclone.contacts

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.activator.chatclone.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val contactsListMutableLiveData: MutableLiveData<List<ContactsModel>> =
        MutableLiveData()
    private val contactsList: MutableList<ContactsModel> = mutableListOf()
    private val app: Application = application

    init {
        contactsListMutableLiveData.value = contactsList
    }

    fun getContactsList(): LiveData<List<ContactsModel>> {
        getContactsListFromDevice(app)
        return contactsListMutableLiveData
    }

    @SuppressLint("MissingPermission")
    private fun getContactsListFromDevice(application: Application) {

        val list = application.applicationContext.retrieveAllContacts("", false, -1, -1)
        for (i in list.indices) {
            Log.d("Debug", "contact ${list[i]}")
            if (list[i].phoneNumber.isNotEmpty()) {
                for (j in 0 until list[i].phoneNumber.size) {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            Log.d("Debug", "adding contact ${list[i].name}")
                            contactsList.add(
                                ContactsModel(
                                    list[i].name,
                                    app.resources.getResourceName(R.drawable.gojo_avatar),
                                    list[i].phoneNumber[j],
                                    "Status for number ${list[i].phoneNumber[j]}"
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun generateSampleList() {
        for (i in 0..10) {

            viewModelScope.launch {

                withContext(Dispatchers.Main) {
                    contactsList.add(
                        ContactsModel(
                            "Name_$i",
                            app.resources.getResourceName(R.drawable.gojo_avatar),
                            "996903862$i",
                            "Status for person $i"
                        )
                    )
                }
            }
        }
    }

}