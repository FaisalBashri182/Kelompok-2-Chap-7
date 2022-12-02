package tsm.bdg.ch6group.ui.auth.login

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.data.local.Db
import tsm.bdg.ch6group.databinding.ActivityLoginBinding

class LoginPresenter(
    context: Context,
    private val view: LoginActivity,
    private var binding: ActivityLoginBinding
) {

    val db = Db.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun login() {
        GlobalScope.launch(Dispatchers.IO) {
            val nameInput = binding.etUserNameActivityLogin.text.toString()
            val passwordInput = binding.etPasswordActivityLogin.text.toString()
            val loginTest = db?.userDao()?.login(nameInput, passwordInput)

            if (loginTest == null && nameInput.isNotEmpty() && passwordInput.isNotEmpty())
                launch(Dispatchers.Main) {
                    view.onErrorNoAccount()
                }
            if (nameInput.isEmpty() || passwordInput.isEmpty())
                launch(Dispatchers.Main) {
                    view.onErrorNotFilled()
                }
            if (loginTest != null)
                launch(Dispatchers.Main) {
                    view.onSuccess()
                }

        }
    }
}