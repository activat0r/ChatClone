package com.activator.chatclone.main.status

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.activator.chatclone.R
import com.activator.circleimageview.SegmentedCircularProgressBar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StatusAdapter(private val listItems: MutableList<StatusModel>, val context: Context): RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    class StatusViewHolder(view: View):RecyclerView.ViewHolder(view){
        val statusName:TextView = view.findViewById<TextView>(R.id.item_status_name)
        val statusImage: SegmentedCircularProgressBar = view.findViewById<SegmentedCircularProgressBar>(
            R.id.item_status_image
        )
        val statusDate:TextView = view.findViewById<TextView>(R.id.item_status_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_status, parent, false)

        return StatusViewHolder(view)
    }

    private fun getReadCount(statusItem: StatusModel): Int{
        var count = 0;
        for (i in 0 until statusItem.statusList.size){
            if(statusItem.statusList[i].isRead)
                count++
        }

        return count
    }

    private fun getLatestDate(statusItem: StatusModel):Long{
        var maxDate = statusItem.statusList[0].date

        for(i in 1 until statusItem.statusList.size){
            if(maxDate < statusItem.statusList[i].date){
                maxDate = statusItem.statusList[i].date
            }
        }
         return maxDate
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val statusItem = listItems[position]

        holder.statusName.text = statusItem.name

        holder.statusImage.setImageResource(R.drawable.gojo_avatar)

        val readCount = getReadCount(statusItem)
        holder.statusImage.setReadcount(readCount)
        holder.statusImage.setUnreadCount(statusItem.statusList.size - readCount)

        val sdf: DateFormat = SimpleDateFormat("dd/MM/yy")
        val date = Date(getLatestDate(statusItem))
        val formattedDate = sdf.format(date)
        holder.statusDate.text = formattedDate.toString()
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}