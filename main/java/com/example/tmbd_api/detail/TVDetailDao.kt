package com.example.tmbd_api.detail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.tmbd_api.TV

@Dao
interface TVDetailDao {
    @Query("SELECT * FROM tv WHERE `id`= :id")
    fun getTv(id:Long):LiveData<TV>

}