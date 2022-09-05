package com.activator.chatclone.main.status

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.activator.chatclone.customviews.CustomItemDecorator
import com.activator.chatclone.R
import com.activator.chatclone.databinding.FragmentStatusBinding
import com.activator.chatclone.main.FABClickInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatusFragment : Fragment(),FABClickInterface {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onFABClick(context: Context) {
        Toast.makeText(context,"Status Fragment",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.inflateMenu(R.menu.status_toolbar_navigation)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatButton)
        fab.setImageResource(R.drawable.ic_round_photo_camera_24)

    }

    override fun onPause() {
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.menu.clear()
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatButton)
        super.onPause()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        var list = mutableListOf<StatusModel>()
        for (i in 0..5){
            val statusImageList = mutableListOf<StatusImageModel>()
            val image: Bitmap = BitmapFactory.decodeResource(this.activity?.resources,R.drawable.gojo_avatar)
            statusImageList.add(StatusImageModel(image,Calendar.getInstance().timeInMillis-10000,true))
            statusImageList.add(StatusImageModel(image,Calendar.getInstance().timeInMillis,false))

            list.add(StatusModel("Name_$i",statusImageList))
        }



        val binding = FragmentStatusBinding.inflate(layoutInflater, container, false)
        val recyclerView = binding.statusRecyclerView
        val adapter = StatusAdapter(list, requireActivity().baseContext)
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        context?.let { CustomItemDecorator(it,R.layout.item_status) }?.let { recyclerView.addItemDecoration(it) }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        return binding.root


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StatusFragment()

    }
}