package com.example.roflobankomatvovastera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roflobankomatvovastera.BalanceHelper
import com.example.roflobankomatvovastera.R
import com.example.roflobankomatvovastera.SharedViewModel
import com.example.roflobankomatvovastera.UserHelper
import com.example.roflobankomatvovastera.databinding.FragmentUserWithdrawBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserWithdrawFragment : Fragment() {
    private val binding: FragmentUserWithdrawBinding by lazy {
        FragmentUserWithdrawBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var viewModel: SharedViewModel
    private lateinit var userHelper: UserHelper
    private lateinit var balanceHelper: BalanceHelper
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
        userHelper = UserHelper(requireContext())
        balanceHelper = BalanceHelper(requireContext())
        binding.tvDebitMoney.setOnClickListener {
            viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            viewModel.sharedData.observe(viewLifecycleOwner) { data ->
                val userKeyList = data.split("_")
                val user = userHelper.getUser(userKeyList[0], userKeyList[1], userKeyList[2].toInt())
                if(user.history.isEmpty()) {
                    user.history.add("История операций")
                    userHelper.saveUser(user)
                }
                // обработка того, что мы ввели в поле
                if (binding.etEnterAmount.text.toString() == "") findNavController().navigate(R.id.userInvalidFormatFragment)
                else if (binding.etEnterAmount.text.toString().toInt() > balanceHelper.getBalance()) findNavController().navigate(R.id.sumNotAvailableFragment)
                else {
                    if (binding.etEnterAmount.text.toString().toInt() <= userHelper.getUserBalance(user)) {
                        userHelper.updateBalance(binding.etEnterAmount.text.toString().toInt(), false, user)
                        val date = getCurrentDateTime()
                        user.history.add("- снятие " + binding.etEnterAmount.text.toString() + " " + date)
                        userHelper.saveUser(user)
                        findNavController().navigate(R.id.userTakeMoneyFragment2)
                    }
                else findNavController().navigate(R.id.userInsufficientFundsFragmentFragment)
                }
            }
        }
        binding.tvBackToMenu.setOnClickListener { findNavController().navigate(R.id.userActionsFragment) }
    }

    private fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return currentDateTime.format(formatter)
    }
}