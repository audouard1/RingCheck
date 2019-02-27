package iut.clermont.pm.ringcheck.data.persistence

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import iut.clermont.pm.ringcheck.data.model.Cat
import iut.clermont.pm.ringcheck.service.WebCatService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CatRepository {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: WebCatService = retrofit.create(WebCatService::class.java)

    @WorkerThread
    fun GetCat() : Cat? {
        service.getRandCat().execute().body()?.let {
            return it
        }
        return null
    }
}