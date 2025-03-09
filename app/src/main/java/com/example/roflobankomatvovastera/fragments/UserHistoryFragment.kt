package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.SharedViewModel
import com.example.roflobankomatvovastera.UserHelper
import com.example.roflobankomatvovastera.databinding.FragmentUserHistoryBinding

class UserHistoryFragment : Fragment() {
    private val binding: FragmentUserHistoryBinding by lazy { FragmentUserHistoryBinding.inflate(layoutInflater) }
    private lateinit var userHelper: UserHelper
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userHelper = UserHelper(requireContext())
        showHistory()
        applyClick()
    }

    private fun showHistory() {
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.sharedData.observe(viewLifecycleOwner) { data ->
            val userKeyList = data.split("_")
            val user = userHelper.getUser(userKeyList[0], userKeyList[1], userKeyList[2].toInt())
            val history = user.history
            // Настроить LayoutManager
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = MyAdapter(history)
        }
    }

    private fun applyClick() {
        binding.tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.userActionsFragment) }
    }
}