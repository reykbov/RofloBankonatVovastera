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
import com.example.roflobankomatvovastera.databinding.FragmentGreetingBinding

class GreetingFragment : Fragment() {
    private val binding: FragmentGreetingBinding by lazy { FragmentGreetingBinding.inflate(layoutInflater) }
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyClick()
        showInfo()
    }

    private fun applyClick() {
        with(binding) {
            tvGoToActions.setOnClickListener { findNavController().navigate(R.id.userActionsFragment) }
        }
    }

    private fun showInfo() {
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.sharedData.observe(viewLifecycleOwner) { data ->
            val userKeyList = data.split("_")
            binding.tvSuccessfulAuthorization.text = "Авторизация успешна.\n\nЗдравствуйте, " + userKeyList[1]
        }
    }
}