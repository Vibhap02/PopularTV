package com.example.tmbd_api.list

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.tmbd_api.ErrorCode
import com.example.tmbd_api.LoadingStatus
import com.example.tmbd_api.TV
import com.example.tmbd_api.TVDatabase
import com.example.tmbd_api.list.TVListDao
import com.example.tmbd_api.data.network.TmdbService
import java.lang.Exception
import java.net.UnknownHostException

class TVListRepository(context: Application) {
    private val tvListDao:TVListDao= TVDatabase.getDatabase(context).tvListDao()
    private val tmdbService by lazy { TmdbService.getInstance()}

    fun getTvs():LiveData<List<TV>>{
       return tvListDao.getTvs()
    }
    suspend fun fetchFromNetwork()=
            try {
                val result = tmdbService.getTvs()
                if (result.isSuccessful){
                    val tvList = result.body()
                    tvList?.let { tvListDao.insertTvs(it.results) }
                    LoadingStatus.success()

                }else{
                    LoadingStatus.error(ErrorCode.NO_DATA)
                }

            }catch (ex:UnknownHostException){
                LoadingStatus.error(ErrorCode.NETWORK_ERROR)
            }catch (ex:Exception){
                LoadingStatus.error(ErrorCode.UNKNOWN_ERROR,ex.message)
            }
//    suspend fun deleteAllData(){
//        tvListDao.deleteAllData()
//    }

}