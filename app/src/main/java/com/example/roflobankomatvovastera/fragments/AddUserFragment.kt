package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.databinding.FragmentAddUserBinding

class AddUserFragment : Fragment() {
    private val binding: FragmentAddUserBinding by lazy { FragmentAddUserBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}