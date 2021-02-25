package com.example.tmbd_api.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmbd_api.LoadingStatus
import com.example.tmbd_api.TV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TVListViewModel(application: Application):AndroidViewModel(application) {
    private val repo:TVListRepository= TVListRepository(application)
    val tvs: LiveData<List<TV>> = repo.getTvs()

    private val _loadingStatus= MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    fun fetchFromNetwork(){
        _loadingStatus.value = LoadingStatus.loading()
        viewModelScope.launch {
            _loadingStatus.value =  withContext(Dispatchers.IO){
                //delay(5000)
                repo.fetchFromNetwork()
            }
        }
    }
//    fun refreshData(){
//        viewModelScope.launch(Dispatchers.IO){
//            repo.deleteAllData()
//        }
//        fetchFromNetwork()
//    }
}