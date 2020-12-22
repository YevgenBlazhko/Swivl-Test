package com.example.swivl.ui.main.users

class UserItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}