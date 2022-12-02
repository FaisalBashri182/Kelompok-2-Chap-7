package tsm.bdg.ch6group

sealed class ResultState {
    class Loading(val loading: Boolean) : ResultState()
    class Success<T>(val data: T) : ResultState()
    class Error(val e: Throwable) : ResultState()
}