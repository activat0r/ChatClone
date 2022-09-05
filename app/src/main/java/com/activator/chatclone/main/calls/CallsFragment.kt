package com.activator.chatclone.main.calls

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.activator.chatclone.R
import com.activator.chatclone.customviews.CustomItemDecorator
import com.activator.chatclone.databinding.FragmentCallsBinding
import com.activator.chatclone.main.FABClickInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CallsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CallsFragment : Fragment(), FABClickInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onFABClick(context: Context) {
        Toast.makeText(context,"Calls Fragment",Toast.LENGTH_SHORT).show()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }
    override fun onResume() {
        super.onResume()

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.inflateMenu(R.menu.calls_toolbar_navigation)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatButton)
        fab.setImageResource(R.drawable.ic_round_add_ic_call_24)
    }

    override fun onPause() {

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.menu.clear()
        super.onPause()

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentCallsBinding.inflate(layoutInflater, container, false)
        val root = binding.root

        val viewModel: CallsViewModel by viewModels()

        val list = mutableListOf<CallsModel>()
        val adapter = CallsAdapter(list)

        val recyclerView = binding.callsRecyclerView
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        context?.let { CustomItemDecorator(it, R.layout.item_calls) }?.let { recyclerView.addItemDecoration(it) }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val observer = Observer<List<CallsModel>>(){

            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.getCallsList().observe(requireActivity(), observer)
        return root
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            CallsFragment()

    }
}