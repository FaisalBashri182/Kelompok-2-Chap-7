package tsm.bdg.ch6group.ui.home

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.data.local.Db
import tsm.bdg.ch6group.databinding.ActivityHomeBinding

class HomePresenter(context: Context, private val view: HomeView) {
    val db = Db.getInstance(context)
    val intent = Intent()

    @OptIn(DelicateCoroutinesApi::class)
    fun menu() {
        GlobalScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                view.onMenu()
            }
        }
    }
}