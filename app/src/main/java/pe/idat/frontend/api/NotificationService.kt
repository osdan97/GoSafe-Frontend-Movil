package pe.idat.frontend.api

import pe.idat.frontend.api.models.Notification
import retrofit2.Call
import retrofit2.http.GET

interface NotificationService {
    @GET("api/notification/list")
    fun getNotifications(): Call<List<Notification>>
}