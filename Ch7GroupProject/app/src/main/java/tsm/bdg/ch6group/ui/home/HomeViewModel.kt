package tsm.bdg.ch6group.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.remote.ApiClient

class HomeViewModel : ViewModel() {

    lateinit var context: Context

    val afterLogin = MutableLiveData<ResultState>()


    fun getUser(token: String) {


        afterLogin.value = ResultState.Loading(true)


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.serviceBinar.getUser("Bearer $token")
                launch(Dispatchers.Main) {
                    afterLogin.value = ResultState.Success(response)
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    afterLogin.value = ResultState.Error(e)
                }
            } finally {
                afterLogin.postValue(ResultState.Loading(false))
            }
        }
    }

}