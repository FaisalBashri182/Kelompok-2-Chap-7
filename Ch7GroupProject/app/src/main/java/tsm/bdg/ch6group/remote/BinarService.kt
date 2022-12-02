package tsm.bdg.ch6group.remote

import okhttp3.RequestBody
import retrofit2.http.*
import tsm.bdg.ch6group.data.local.model.*


interface BinarService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body body: LoginBody): LoginResponse

    @PUT("/api/v1/users")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body body: RequestBody
    ): EditProfileResponse

    @POST("/api/v1/auth/register")
    suspend fun register(@Body body: RegisterBody): RegisterResponse

    @GET("/api/v1/users")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): GetUserResponse
}