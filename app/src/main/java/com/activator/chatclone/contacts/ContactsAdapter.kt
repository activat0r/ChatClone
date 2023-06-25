package com.activator.chatclone.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.R
import com.squareup.picasso.Picasso

class ContactsAdapter(private val list : MutableList<ContactsModel>, val context: Context) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val contactImage : ImageView = itemView.findViewById(R.id.item_contact_image)
         val contactName : TextView = itemView.findViewById(R.id.item_contact_name)
         val contactStatus : TextView = itemView.findViewById(R.id.item_contact_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacts, parent, false)

        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        //todo set values
        val contact : ContactsModel = list[position]
        holder.contactName.text = contact.name
        Picasso.get().load(R.drawable.gojo_avatar).into(holder.contactImage)
        // sync with room to get status
        holder.contactStatus.text = contact.status
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}