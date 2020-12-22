package com.example.swivl.mvvm

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}