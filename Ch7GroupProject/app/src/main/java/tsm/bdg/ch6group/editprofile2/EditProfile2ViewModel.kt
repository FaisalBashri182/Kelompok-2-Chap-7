package tsm.bdg.ch6group.editprofile2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.data.local.model.ErrorResponse
import tsm.bdg.ch6group.remote.ApiClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File


//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//import retrofit2.HttpException
//import tsm.bdg.ch6group.ResultState
//import tsm.bdg.ch6group.data.local.model.ErrorResponse
//import tsm.bdg.ch6group.remote.ApiClient
//import java.io.File

class EditProfile2ViewModel : ViewModel() {

    val resultProfile = MutableLiveData<ResultState>()

    fun editProfile(
        token: String, path: String, username: String, email: String
    ) {
        resultProfile.value = ResultState.Loading(true)

        val imageFile = File(path)

        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("username", username)
            .addFormDataPart("email", email).addFormDataPart(
                "photo",
                "Image_${System.currentTimeMillis()}",
                imageFile.asRequestBody("image/*".toMediaType())
            ).build()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.serviceBinar.editProfile("Bearer $token", requestBody)

                launch(Dispatchers.Main) {
                    if (response.success) {
                        resultProfile.value = ResultState.Success(response)
                    } else {
                        resultProfile.value = ResultState.Error(Throwable("gagal"))
                    }
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    if (e is HttpException) {
                        val resError = e.response()
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        val errorResponse: ErrorResponse? =
                            Gson().fromJson(resError?.errorBody()!!.charStream(), type)

                        resultProfile.value =
                            ResultState.Error(Throwable(errorResponse?.errors))
                    } else {
                        resultProfile.value = ResultState.Error(Throwable("Terjadi Kesalahan"))
                    }
                }
            } finally {
                resultProfile.postValue(ResultState.Loading(false))
            }
        }
    }
}