package pe.idat.frontend.api.models

import com.google.gson.annotations.SerializedName

data class Gym(
    @SerializedName("gymUuid") val gymUuid: String,
    @SerializedName("description") val description: String,
    @SerializedName("district") val district: String,
    @SerializedName("tradename") val tradename: String,
    @SerializedName("direction") val direction: String,
    @SerializedName("createdDate") val createdDate: String
)