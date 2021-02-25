package com.example.tmbd_api.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TVDetailViewModelFactory(private val id:Long ,private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (
            modelClass.isAssignableFrom(TVDetailViewModel::class.java)
        ) {
            @Suppress("UNCHECKED_CAST")
            return TVDetailViewModel(id, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel classjpofk;s")
    }
}