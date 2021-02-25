package com.example.tmbd_api.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tmbd_api.TV

class TVDetailViewModel(id:Long,application: Application):ViewModel() {
    private val repo:TVDetailRepository= TVDetailRepository(application)
    val tv:LiveData<TV> = repo.getTV(id)
}
