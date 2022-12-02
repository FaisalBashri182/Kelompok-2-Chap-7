package tsm.bdg.ch6group.ui.auth.login

import android.content.Context

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.data.local.model.LoginBody
import tsm.bdg.ch6group.remote.ApiClient

class LoginViewModel : ViewModel() {

    lateinit var context: Context

    val resultLogin = MutableLiveData<ResultState>()

    fun login(email: String, password: String) {
        val bodyLogin = LoginBody(email, password)

        resultLogin.value = ResultState.Loading(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.serviceBinar.login(bodyLogin)
                launch(Dispatchers.Main) {
                    resultLogin.value = ResultState.Success(response)
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    resultLogin.value = ResultState.Error(e)
                }
            } finally {
                resultLogin.postValue(ResultState.Loading(false))
            }
        }
    }

}


