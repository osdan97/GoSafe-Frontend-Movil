package pe.idat.frontend.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://mzgym2-production.up.railway.app"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val membershipService: MembershipService by lazy {
        retrofit.create(MembershipService::class.java)
    }

    val gymService: GymService by lazy {
        retrofit.create(GymService::class.java)
    }

    val notificationService: NotificationService by lazy {
        retrofit.create(NotificationService::class.java)
    }

    val customerService: CustomerService by lazy{
        retrofit.create(CustomerService::class.java)
    }

}