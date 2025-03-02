package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.BalanceHelper
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.databinding.FragmentEncashmentMenuBinding

class EncashmentMenuFragment : Fragment() {
    private lateinit var balanceHelper: BalanceHelper //инициализация класса баланса
    private val binding: FragmentEncashmentMenuBinding by lazy {
        FragmentEncashmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balanceHelper = BalanceHelper(requireContext())
        applyClick()
    }

    private fun applyClick() {
        if (balanceHelper.getBalance() == 0) {
            balanceHelper.saveBalance(1488524271)
        }
        with(binding) {
            tvLogOut.setOnClickListener { findNavController().navigate(R.id.mainMenuFragment) }
            tvFindBalance.setOnClickListener { findNavController().navigate(R.id.atmBalanceFragment2) }
            tvDepositFunds.setOnClickListener { findNavController().navigate(R.id.encashmentDepositFragment2) }
            tvWithdrawFunds.setOnClickListener { findNavController().navigate(R.id.encashmentWithdrawFragment) }
        }
    }
}