package com.example.roflobankomatvovastera

import android.content.Context

class BalanceHelper(context: Context) {
    // Имя файла SharedPreferences
    private val sharedPreferences = context.getSharedPreferences("ATM_Balance", Context.MODE_PRIVATE)

    // Сохранить текущий баланс
    fun saveBalance(balance: Int) {
        sharedPreferences.edit().putInt("balance", balance).apply()
    }

    // Получить текущий баланс (значение по умолчанию = 0)
    fun getBalance(): Int {
        return sharedPreferences.getInt("balance", 0)
    }

    // Обновить баланс (при снятии или пополнении)
    fun updateBalance(amount: Int, isDeposit: Boolean) {
        val currentBalance = getBalance()
        val newBalance = if (isDeposit) {
            currentBalance + amount
        } else {
            currentBalance - amount
        }
        saveBalance(newBalance)
    }
}