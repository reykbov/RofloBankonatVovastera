package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.MainActivity
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {
    private val binding: FragmentMainMenuBinding by lazy {
        FragmentMainMenuBinding.inflate(layoutInflater)
    }

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
            tvEncashment.setOnClickListener { findNavController().navigate(R.id.encashmentMenuFragment) }
            tvAddUser.setOnClickListener { findNavController().navigate(R.id.addUserFragment) }
            tvLogIn.setOnClickListener { findNavController().navigate(R.id.logInSystemFragment) }
            tvEndOfWork.setOnClickListener {  requireActivity().finishAffinity() }
        }
    }
}