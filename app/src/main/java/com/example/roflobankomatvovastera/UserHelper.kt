package com.example.roflobankomatvovastera

import android.content.Context
import com.google.gson.Gson

class UserHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("ATM_Users", Context.MODE_PRIVATE)


    fun addUser(secondName: String, name: String,  pin: Int) {
        val historyList: MutableList<String> = mutableListOf()
        val user = User(secondName, name,  pin, 0, historyList)
        val userKey: String = secondName + "_" + name + "_" + pin.toString()
        sharedPreferences.edit().putString(userKey, Gson().toJson(user)).apply()
    }

    fun saveUser(user: User) {
        val userKey: String = user.secondName + "_" + user.name + "_" + user.pin.toString()
        sharedPreferences.edit().putString(userKey, Gson().toJson(user)).apply()
    }

    fun isUserExist(secondName: String, name: String,  pin: Int): Boolean {
        val userKey: String = secondName + "_" + name + "_" + pin.toString()
        return sharedPreferences.contains(userKey)
    }

    fun getUser(secondName: String, name: String,  pin: Int): User {
        val userKey: String = secondName + "_" + name + "_" + pin.toString()
        return Gson().fromJson(sharedPreferences.getString(userKey, ""), User::class.java)
    }

    fun getUserBalance(user: User): Int {
        return user.balance
    }

    fun saveBalance(user: User, balance: Int) {
        user.balance = balance
        saveUser(user)
    }

    fun updateBalance(amount: Int, isDeposit: Boolean, user: User) {
        val currentBalance = getUserBalance(user)
        val newBalance = if (isDeposit) {
            currentBalance + amount
        } else {
            currentBalance - amount
        }
        saveBalance(user, newBalance)
    }
}