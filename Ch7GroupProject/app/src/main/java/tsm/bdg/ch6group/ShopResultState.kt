package tsm.bdg.ch6group

sealed class ShopResultState{
    class Loading(val loading: Boolean) : ShopResultState()
    class Success<T>(val avatarKey: String) : ShopResultState()
    class Error(val e: Throwable) : ShopResultState()
}
