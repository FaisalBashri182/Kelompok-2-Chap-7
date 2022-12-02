package tsm.bdg.ch6group.ui.loginhistory

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.data.local.Dblogin
import tsm.bdg.ch6group.databinding.ActivityLoginHistoryBinding

class LoginHistoryPresenter(
    context: Context,
    private val view: LoginHistoryActivity,
    private var binding: ActivityLoginHistoryBinding
) {
    val db = Dblogin.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun show() {
        GlobalScope.launch(Dispatchers.IO) {
            val loginHistory = db?.loginDao()?.getAllDataLogin()

            launch(Dispatchers.Main) {

                if (loginHistory != null) {
                    val adapterLoginHistoryActivity = RvLoginHistoryAdapter(loginHistory)
                   binding.rvLoginHistory.adapter = adapterLoginHistoryActivity
                    view.showLoginHistory()
                }
            }
        }
    }

}