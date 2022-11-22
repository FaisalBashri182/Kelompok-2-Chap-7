package tsm.bdg.ch6group.ui.auth.signup

import android.content.Context
import android.util.Log
import android.widget.ImageButton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.data.local.Db
import tsm.bdg.ch6group.data.model.User
import tsm.bdg.ch6group.databinding.ActivitySignUpBinding

class SignUpPresenter(
    context: Context,
    private val view: SignUpView,
    private var binding: ActivitySignUpBinding,
    private var dataAvatar: String = "1"
) {

    val db = Db.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun signUp(dataUser: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val status = db?.userDao()?.insert(dataUser) ?: 0

            Log.e("status", status.toString())

            val dataName = binding.etUserNameActivitySignUp.text.trim().toString()
            val dataEmail = binding.etEmailActivitySignUp.text.trim().toString()
            val dataPassword = binding.etEnterPasswordActivitySignUp.text.trim().toString()
            val dataPassword1 = binding.etReEnterPasswordActivitySignUp.text.trim().toString()

            if (status >= 1 && dataName.isNotEmpty() && dataEmail.isNotEmpty() && dataPassword.isNotEmpty() && dataPassword1.isNotEmpty() && dataAvatar.isNotEmpty() && dataPassword == dataPassword1) {
                launch(Dispatchers.Main) {
                    view.onSuccess()
                }
            }

            if (dataPassword != dataPassword1 && dataName.isNotEmpty() && dataEmail.isNotEmpty() && dataPassword.isNotEmpty() && dataPassword1.isNotEmpty() && dataAvatar.isNotEmpty())
                launch(Dispatchers.Main) {
                    view.onErrorPassNotMatch()
                }

            if (dataName.isEmpty() || dataEmail.isEmpty() || dataPassword.isEmpty() || dataPassword1.isEmpty() || dataAvatar.isEmpty())
                launch(Dispatchers.Main) {
                    view.onErrorNotFilled()
                }

        }
    }
}