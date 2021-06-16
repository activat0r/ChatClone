package com.activator.chatclone.main.chats

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.activator.chatclone.CatCloneApp
import com.activator.chatclone.customviews.CustomItemDecorator
import com.activator.chatclone.R
import com.activator.chatclone.databinding.FragmentChatBinding
import com.activator.chatclone.main.FABClickInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment() : Fragment(),FABClickInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val chatViewModel: ChatsViewModel by viewModels {
        ChatsViewModel.ChatsViewModelFactory((requireActivity().application as CatCloneApp).chatRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Chat", " onAttach")

    }


    override fun onDetach() {
        super.onDetach()
        Log.d("Chat", " onDetach")
    }
    override fun onFABClick(context: Context) {
            Toast.makeText(context,"Chats Fragment",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.inflateMenu(R.menu.chats_toolbar_navigation)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatButton)
        fab.setImageResource(R.drawable.ic_baseline_message_24)

    }

    override fun onPause() {
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.menu.clear()

        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatButton)
        //fab.setOnClickListener(null)

        super.onPause()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        var list = generateSampleList()
        val binding = FragmentChatBinding.inflate(layoutInflater)
        val recyclerView = binding.chatsRecyclerView
        context?.let { CustomItemDecorator(it,R.layout.item_chats) }?.let { recyclerView.addItemDecoration(it) }
        val adapter = ChatsAdapter(list, requireActivity().baseContext)
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        Log.d("Main", "items in recycler ${recyclerView.adapter?.itemCount.toString()}")

        return binding.root
    }

    private fun generateSampleList(): MutableList<ChatsModel> {
        val list = mutableListOf<ChatsModel>()
        for (i in 0..30){
            Log.d("Main"," added $i th item ")
            list.add(ChatsModel("url","${resources.getString(R.string.short_text)}_$i","${resources.getString(R.string.short_text)}_$i",true,false,
                Calendar.getInstance().timeInMillis))
        }
        return list
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ChatFragment()
    }
}