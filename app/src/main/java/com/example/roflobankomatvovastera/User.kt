package com.example.roflobankomatvovastera

data class User(
    val secondName: String,
    val name: String,
    val pin: Int,
    var balance: Int,
    val history: MutableList<String>
)