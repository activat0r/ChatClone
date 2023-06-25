package com.activator.chatclone.main.chats

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.activator.chatclone.ChatCloneAppController
import com.activator.chatclone.ChatCloneDatabase
import com.activator.chatclone.ChatCloneDatabaseImpl
import com.activator.chatclone.customviews.CustomItemDecorator
import com.activator.chatclone.R
import com.activator.chatclone.contacts.ContactsActivity
import com.activator.chatclone.databinding.FragmentChatBinding
import com.activator.chatclone.main.FABClickInterface
import com.activator.chatclone.main.chats.main.ChatActivity
import com.activator.chatclone.main.entities.ChatScreen
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
class ChatFragment() : Fragment(), FABClickInterface, ChatsAdapter.ChatItemClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var list: MutableList<ChatsModel>
    private val chatViewModel: ChatsViewModel by viewModels {
        ChatsViewModel.ChatsViewModelFactory((requireActivity().application as ChatCloneAppController).chatRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        Log.d("Debug","OnFab onAttach $activity")
        super.onAttach(context)
    }

    override fun onDetach() {
        Log.d("Debug","OnFab onDetach")
        super.onDetach()
    }

    override fun onFABClick(context: Context) {
        Log.d("Debug","OnFab click")
        val intent = Intent(context, ContactsActivity::class.java).also {
            it.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        context.startActivity(intent)
    }

    override fun onDestroy() {
        Log.d("Debug","OnFab onDestroy")
        super.onDestroy()

    }

    override fun onResume() {
        super.onResume()

        val toolbar = activity?.findViewById<Toolbar>(R.id.mainToolbar)
        toolbar?.inflateMenu(R.menu.chats_toolbar_navigation)
        val fab = activity?.findViewById<FloatingActionButton>(R.id.floatButton)
        fab?.setImageResource(R.drawable.ic_baseline_message_24)

    }

    override fun onPause() {
        Log.d("Debug","Pausing fragment")
        val toolbar = activity?.findViewById<Toolbar>(R.id.mainToolbar)
        toolbar?.menu?.clear()

        val fab = activity?.findViewById<FloatingActionButton>(R.id.floatButton)
        //fab.setOnClickListener(null)

        super.onPause()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        list = generateSampleList()
        val binding = FragmentChatBinding.inflate(layoutInflater)
        val recyclerView = binding.chatsRecyclerView
        context?.let { CustomItemDecorator(it, R.layout.item_chats) }
            ?.let { recyclerView.addItemDecoration(it) }
        val adapter = ChatsAdapter(list, this, requireActivity().baseContext)
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        return binding.root
    }

    private fun generateSampleList(): MutableList<ChatsModel> {
        val list = mutableListOf<ChatsModel>()
        var chatList: List<ChatScreen> = listOf()

        if (activity != null) {
            val dbInstance = ChatCloneDatabaseImpl().getDatabase(
                requireActivity().applicationContext,
                CoroutineScope(Dispatchers.IO)
            )

            runBlocking(Dispatchers.IO) {
                chatList = dbInstance.chatDao().getMessagedContacts()
            }
        }

        for (i in chatList.indices) {
            list.add(
                ChatsModel(
                    "url",
                    chatList[i].roomName, chatList[i].stringMessage, true, false,
                    chatList[i].time
                )
            )
        }
        return list
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ChatFragment()
    }

    override fun onItemClick(position: Int) {
        val item = list[position]
        Toast.makeText(requireContext(), "Clicked on ${item.chat_title}", Toast.LENGTH_SHORT).show()
        val chatIntent = Intent(requireContext(), ChatActivity::class.java).also {
            it.putExtra("name", item.chat_title)

        }
        startActivity(chatIntent)
    }
}