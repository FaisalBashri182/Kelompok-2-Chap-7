package tsm.bdg.ch6group.ui.auth.login

interface LoginView {
    fun onSuccess ()
    fun onErrorNotFilled()
    fun onErrorNoAccount()
}