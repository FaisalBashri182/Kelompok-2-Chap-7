package tsm.bdg.ch6group.data.local.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("_id")
        val id: String,
        @SerializedName("username")
        val username: String,
        @SerializedName("email")
        val email: String
    )
}