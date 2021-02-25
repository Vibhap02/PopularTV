package com.example.tmbd_api.detail

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.tmbd_api.TV
import com.example.tmbd_api.TVDatabase

class TVDetailRepository(context: Application) {
    private val tvDetailDao:TVDetailDao=TVDatabase.getDatabase(context).tvDetailDao()
    fun getTV(id:Long):LiveData<TV>{
        return tvDetailDao.getTv(id)
    }

}