package pe.idat.frontend.api.models

data class SignUpResponse(
    val email: String,
    val uuid: String,
    val name: String,
    val lastname: String
)
