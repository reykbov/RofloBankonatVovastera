package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.SharedViewModel
import com.example.roflobankomatvovastera.UserHelper
import com.example.roflobankomatvovastera.databinding.FragmentUserDepositBinding

class UserDepositFragment : Fragment() {
    private val binding: FragmentUserDepositBinding by lazy { FragmentUserDepositBinding.inflate(layoutInflater) }
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
        binding.tvDepositMoney.setOnClickListener {
            viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            viewModel.sharedData.observe(viewLifecycleOwner) { data ->
                val userKeyList = data.split("_")
                val user = userHelper.getUser(userKeyList[0], userKeyList[1], userKeyList[2].toInt())
                // обработка того, что мы ввели в поле
                if(binding.etEnterAmount.text.toString() == "") findNavController().navigate(R.id.userInvalidFormatFragment)
                else {
                    if(binding.etEnterAmount.text.toString().toInt() + userHelper.getUserBalance(user) in 0..2147483647) {
                        userHelper.updateBalance(binding.etEnterAmount.text.toString().toInt(), true, user)
                        findNavController().navigate(R.id.userFundsDepositedFragment)
                    }
                    else findNavController().navigate(R.id.userInvalidFormatFragment)
                }
            }
        }
        binding.tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.userActionsFragment) }
    }

}