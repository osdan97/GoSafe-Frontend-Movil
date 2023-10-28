package pe.idat.frontend.api

import pe.idat.frontend.api.models.Customer
import pe.idat.frontend.api.models.Gym
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerService {
    @GET("api/customer/findbyid/{uuid}")
    fun getCustomerDetails(@Path("uuid") uuid: String): Call<Customer>

    @PUT("api/customer/update/{uuid}")
    fun updateCustomer(
        @Path("uuid") uuid: String,
        @Body updatedCustomer: Customer
    ): Call<Customer>
}