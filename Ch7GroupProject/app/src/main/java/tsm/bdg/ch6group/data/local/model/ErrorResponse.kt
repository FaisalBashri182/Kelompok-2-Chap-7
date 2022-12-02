package tsm.bdg.ch6group.data.local.model


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: String,
    @SerializedName("success")
    val success: Boolean
)