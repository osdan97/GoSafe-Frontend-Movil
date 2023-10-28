package pe.idat.frontend.api.models

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("accountUuid") val accountUuid: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("address") @Nullable val address: String?,
    @SerializedName("documentNumber") @Nullable val documentNumber: String?
)
