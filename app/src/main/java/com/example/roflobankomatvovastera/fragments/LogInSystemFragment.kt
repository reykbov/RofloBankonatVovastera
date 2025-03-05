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
import com.example.roflobankomatvovastera.databinding.FragmentLogInSystemBinding

class LogInSystemFragment : Fragment() {
    //
    private lateinit var viewModel: SharedViewModel
    private lateinit var userHelper: UserHelper
    private val binding: FragmentLogInSystemBinding by lazy { FragmentLogInSystemBinding.inflate(layoutInflater) }
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
        with(binding) {
            tvEnter.setOnClickListener {
                if(etEnterSecondName.text.toString().isEmpty() || etEnterName.text.toString().isEmpty()) findNavController().navigate(R.id.logInIncorrecltlyDataFragment)
                else if(etEnterPin.text.toString().length < 4) findNavController().navigate(R.id.logInIncorrectPinFragment)
                else {
                    if(userHelper.isUserExist(etEnterSecondName.text.toString(), etEnterName.text.toString(), etEnterPin.text.toString().toInt())) {
                        viewModel.sharedData.value = etEnterSecondName.text.toString() + "_" + etEnterName.text.toString() + "_" + etEnterPin.text.toString()
                        findNavController().navigate(R.id.greetingFragment)
                    }
                    else findNavController().navigate(R.id.userNotFoundFragment)
                }
            }
        }
    }

}