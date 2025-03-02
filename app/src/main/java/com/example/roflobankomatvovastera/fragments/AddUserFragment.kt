package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.UserHelper
import com.example.roflobankomatvovastera.databinding.FragmentAddUserBinding

class AddUserFragment : Fragment() {
    private lateinit var userHelper: UserHelper
    private val binding: FragmentAddUserBinding by lazy { FragmentAddUserBinding.inflate(layoutInflater) }
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
        with(binding) {
            tvAddUser.setOnClickListener {
                if(etEnterSecondName.text.toString().isEmpty() || etEnterName.text.toString().isEmpty()) findNavController().navigate(R.id.addUserIncorrectlyDataFragment)
                else if(etEnterPin.text.toString().length < 4) findNavController().navigate(R.id.addUserIncorrectPinFragment)
                else {
                    if(userHelper.isUserExist(etEnterSecondName.text.toString(), etEnterName.text.toString(), etEnterPin.text.toString().toInt())) findNavController().navigate(R.id.userAlreadyAddedFragment)
                    else {
                        userHelper.addUser(etEnterSecondName.text.toString(), etEnterName.text.toString(), etEnterPin.text.toString().toInt())
                        findNavController().navigate(R.id.userAddedFragment)
                    }
                }
            }
        }
    }
}