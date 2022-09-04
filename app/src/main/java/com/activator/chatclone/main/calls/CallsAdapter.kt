package com.activator.chatclone.main.calls

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class CallsAdapter(private var listItems: MutableList<CallsModel>): RecyclerView.Adapter<CallsAdapter.CallsAdapterViewHolder>() {
    class CallsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val call_name = itemView.findViewById<TextView>(R.id.item_calls_name)
        val call_date = itemView.findViewById<TextView>(R.id.item_calls_date)
        val call_count = itemView.findViewById<TextView>(R.id.item_calls_count)
        val call_type = itemView.findViewById<ImageView>(R.id.item_calls_type)
        val call_avatar = itemView.findViewById<ImageView>(R.id.item_calls_image)
        val call_goingType = itemView.findViewById<ImageView>(R.id.item_calls_goingType)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calls, parent, false)
        return CallsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CallsAdapterViewHolder, position: Int) {
        val callItem = listItems[position]
        holder.call_name.text = callItem.name

        Picasso.get().load(R.drawable.gojo_avatar).into(holder.call_avatar)

        if(callItem.isVoiceCall)
            holder.call_type.setImageResource(R.drawable.ic_round_call_24)
        else
            holder.call_type.setImageResource(R.drawable.ic_round_videocam_24)

        if (callItem.isIncoming)
            holder.call_goingType.setImageResource(R.drawable.ic_baseline_call_received_24)
        else
            holder.call_goingType.setImageResource(R.drawable.ic_baseline_call_made_24)

        if(callItem.isPicked)
            holder.call_goingType.setColorFilter(Color.GREEN)
        else
            holder.call_goingType.setColorFilter(Color.GREEN)

        val sdf = SimpleDateFormat("dd/MM/yy, hh:mm a")
        holder.call_date.text = sdf.format(callItem.date).toString()


    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun setData(newData: MutableList<CallsModel>) {
        this.listItems = newData
        notifyDataSetChanged()
    }
    class CallsDiffCallback(val oldItems:List<CallsModel>, val newItems:List<CallsModel>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition]==newItems[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            TODO("Not yet implemented")
        }

    }

}