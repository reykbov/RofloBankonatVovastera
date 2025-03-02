package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.BalanceHelper
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.databinding.FragmentEncashmentWithdrawBinding

class EncashmentWithdrawFragment : Fragment() {
    private lateinit var balanceHelper: BalanceHelper
    private val binding: FragmentEncashmentWithdrawBinding by lazy {
        FragmentEncashmentWithdrawBinding.inflate(
            layoutInflater
        )
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
        with(binding) {
            tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.encashmentMenuFragment) }
            tvDepositFunds.setOnClickListener {
                if (etEnterAmount.text.toString() == "") findNavController().navigate(R.id.encashmentInvalidFormatFragment) //если поле для ввода пустое, то формат неверный
                else {
                    if (etEnterAmount.text.toString().toInt() <= balanceHelper.getBalance()) {
                        balanceHelper.updateBalance(etEnterAmount.text.toString().toInt(), false)
                        findNavController().navigate(R.id.encashmentTakeFundsFragment)
                    } else if (etEnterAmount.text.toString().toInt() > balanceHelper.getBalance()) findNavController().navigate(R.id.encashmentInsufficientFundsFragment)
                    else findNavController().navigate(R.id.encashmentInvalidFormatFragment)
                }
            }
        }
    }
}