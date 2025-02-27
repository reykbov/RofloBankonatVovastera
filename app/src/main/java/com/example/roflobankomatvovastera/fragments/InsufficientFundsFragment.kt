package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.databinding.FragmentInsufficientFundsBinding

class InsufficientFundsFragment : Fragment() {
    private val binding: FragmentInsufficientFundsBinding by lazy { FragmentInsufficientFundsBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyClick()
    }

    private fun applyClick() {
        with(binding) {
            tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.encashmentMenuFragment) }
        }
    }

}