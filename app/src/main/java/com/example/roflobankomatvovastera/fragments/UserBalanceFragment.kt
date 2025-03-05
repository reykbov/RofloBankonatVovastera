package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.SharedViewModel
import com.example.roflobankomatvovastera.UserHelper
import com.example.roflobankomatvovastera.databinding.FragmentUserBalanceBinding

class UserBalanceFragment : Fragment() {
    private val binding: FragmentUserBalanceBinding by lazy { FragmentUserBalanceBinding.inflate(layoutInflater) }
    private lateinit var viewModel: SharedViewModel
    private lateinit var userHelper: UserHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userHelper = UserHelper(requireContext())
        showInfo()
        applyClick()
    }

    private fun applyClick() {
        with(binding) {
            tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.userActionsFragment) }
        }
    }

    private fun showInfo() {
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.sharedData.observe(viewLifecycleOwner) { data ->
            val userKeyList = data.split("_")
            val user = userHelper.getUser(userKeyList[0], userKeyList[1], userKeyList[2].toInt())
            val balance = userHelper.getUserBalance(user)
            binding.tvBalance.text = "Ваш баланс:\n\n" + balance
        }
    }
}