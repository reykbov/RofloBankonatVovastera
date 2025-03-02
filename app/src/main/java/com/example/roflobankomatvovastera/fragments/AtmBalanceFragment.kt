package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.BalanceHelper
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.databinding.FragmentAtmBalanceBinding

class AtmBalanceFragment : Fragment() {
    private lateinit var balanceHelper: BalanceHelper
    private val binding: FragmentAtmBalanceBinding by lazy {
        FragmentAtmBalanceBinding.inflate(layoutInflater)
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
        val balance = balanceHelper.getBalance().toString()
        with(binding) {
            tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.encashmentMenuFragment) }
            tvBalance.text = "Сумма средств в банкомате:\n\n" + balance
        }
    }

}