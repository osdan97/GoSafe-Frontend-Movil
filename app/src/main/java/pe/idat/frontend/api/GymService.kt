package pe.idat.frontend.api

import pe.idat.frontend.api.models.Gym
import retrofit2.Call
import retrofit2.http.GET

interface GymService {

    @GET("api/gym/list")
    fun getGyms(): Call<List<Gym>>

}