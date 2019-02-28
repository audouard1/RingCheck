package iut.clermont.pm.ringcheck.data.persistence

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import iut.clermont.pm.ringcheck.data.model.Cat
import iut.clermont.pm.ringcheck.service.WebCatService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.R.attr.data
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData






class CatRepository {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: WebCatService = retrofit.create(WebCatService::class.java)

    @WorkerThread
    fun getCat() : LiveData<List<Cat>> {
        val data = MutableLiveData<List<Cat>>()
            service.getRandCat().enqueue(object : Callback<List<Cat>> {
                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {

                }

                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    data.value = response.body()
                }
            })
            return data
    }
}