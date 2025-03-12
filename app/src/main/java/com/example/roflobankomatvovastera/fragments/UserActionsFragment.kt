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
import com.example.roflobankomatvovastera.databinding.FragmentUserActionsBinding

class UserActionsFragment : Fragment() {
    private val binding: FragmentUserActionsBinding by lazy { FragmentUserActionsBinding.inflate(layoutInflater) }
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
        applyClick()
    }

    private fun applyClick() {
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.sharedData.observe(viewLifecycleOwner) { data ->
            val userKeyList = data.split("_")
            val user = userHelper.getUser(userKeyList[0], userKeyList[1], userKeyList[2].toInt())
            binding.tvGetHistory.setOnClickListener {
                if(user.history.isEmpty()) {
                    user.history.add("История операций")
                    userHelper.saveUser(user)
                    findNavController().navigate(R.id.userHistoryFragment)
                }
                else findNavController().navigate(R.id.userHistoryFragment)
            }
        }
        binding.tvFindBalance.setOnClickListener { findNavController().navigate(R.id.userBalanceFragment) }
        binding.tvLogOut.setOnClickListener { findNavController().navigate(R.id.mainMenuFragment) }
        binding.tvDepositMoney.setOnClickListener {  findNavController().navigate(R.id.userDepositFragment)}
        binding.tvWithdrawMoney.setOnClickListener {  findNavController().navigate(R.id.userWithdrawFragment)}
    }
}