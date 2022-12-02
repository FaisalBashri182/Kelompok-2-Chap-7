package tsm.bdg.ch6group.ui.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.data.local.model.RegisterBody

import tsm.bdg.ch6group.remote.ApiClient

class SignUpViewModel : ViewModel() {

    val resultRegister = MutableLiveData<ResultState>()

    fun register(email: String, username: String, password: String) {

        val bodyRegister = RegisterBody(email, username, password)

        resultRegister.value = ResultState.Loading(true)

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = ApiClient.serviceBinar.register(bodyRegister)
                launch(Dispatchers.Main) {
                    resultRegister.value = ResultState.Success(response)
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    resultRegister.value = ResultState.Error(e)
                }
            } finally {
                resultRegister.postValue(ResultState.Loading(false))
            }
        }

    }


}