package com.example.tmbd_api.list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmbd_api.TV

@Dao
interface TVListDao {
    @Query("SELECT * FROM tv ORDER BY first_air_date DESC")
    fun getTvs(): LiveData<List<TV>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTvs(tvs:List<TV>)
//    @Query("DELETE FROM tv")
//    suspend fun deleteAllData()
}