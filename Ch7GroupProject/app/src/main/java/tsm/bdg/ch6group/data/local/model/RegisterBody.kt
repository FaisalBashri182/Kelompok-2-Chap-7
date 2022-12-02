package tsm.bdg.ch6group.data.local.model

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val password: String,
    @SerializedName("password")
    val username: String
)