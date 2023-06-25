package com.activator.chatclone.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.activator.chatclone.R
import com.activator.chatclone.customviews.CustomItemDecorator
import com.activator.chatclone.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {

    private val REQUEST_CODE_READ_CONTACTS = 17

    private val list = mutableListOf<ContactsModel>()
    private var adapter: ContactsAdapter? = null
    private val viewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //todo add appbar
        val binding = ActivityContactsBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        checkPermissionAndShow()


        val recyclerView = binding.contactsRecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        CustomItemDecorator(this, R.layout.item_calls).let { recyclerView.addItemDecoration(it) }
        recyclerView.layoutManager = layoutManager
        adapter = ContactsAdapter(list, this)
        recyclerView.adapter = adapter

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_CONTACTS) {
            checkPermissionAndShow()
        }
    }

    private fun checkPermissionAndShow() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Debug","Fetching list from viewmodel")
            val observer = Observer<List<ContactsModel>>() {
                Log.d("Debug","updating contact list with size ${it.size}")
                list.clear()
                list.addAll(it)
                adapter?.notifyDataSetChanged()
            }
            viewModel.getContactsList().observe(this, observer)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS
            )
        }
    }
}