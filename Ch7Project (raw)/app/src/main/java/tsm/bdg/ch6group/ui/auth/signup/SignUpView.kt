package tsm.bdg.ch6group.ui.auth.signup

interface SignUpView {
    fun onSuccess ()
    fun onErrorNotFilled ()
    fun onErrorPassNotMatch ()
}