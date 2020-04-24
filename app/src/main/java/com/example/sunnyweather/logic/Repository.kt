package com.example.sunnyweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun serchPlaces(query: String) = liveData<Result<List<Place>>>(Dispatchers.IO) {
        val result = try {
            var placeResqonse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResqonse.status == "ok") {
                val place = placeResqonse.places
                Result.success(place)
            } else {
                Result.failure(RuntimeException("response status is ${placeResqonse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}