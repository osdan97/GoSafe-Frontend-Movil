package pe.idat.frontend.api.models

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("notificationUuid") val notificationUuid: String,
    @SerializedName("title") val title: String,
    @SerializedName("message") val message: String,
    @SerializedName("createdDate") val createdDate: String
)
