package iut.clermont.pm.ringcheck.service

import iut.clermont.pm.ringcheck.data.model.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface WebCatService {
        /**
         * @GET declares an HTTP GET request
         */
        @GET("/v1/images/search")
        @Headers("x-api-key: f84b184e-f7f8-4b6f-af11-e4569b2c44dbs")
        fun getRandCat(): Call<List<Cat>>
    }

