package tsm.bdg.ch6group.ui.history

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.Database1
import tsm.bdg.ch6group.databinding.ActivityHistoryBinding

class HistoryPresenter(
    context: Context, private val view: HistoryActivity, private var binding: ActivityHistoryBinding
) {
    val db = Database1.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun show() {
        GlobalScope.launch(Dispatchers.IO) {
            val history = db?.gameDao()?.getHistory()

            launch(Dispatchers.Main) {

                if (history != null) {
                    val adapterHistoryActivity = RvAdapter(history)
                    binding.rvHistory.adapter = adapterHistoryActivity
                    view.showHistory()
                }
            }
        }
    }

}