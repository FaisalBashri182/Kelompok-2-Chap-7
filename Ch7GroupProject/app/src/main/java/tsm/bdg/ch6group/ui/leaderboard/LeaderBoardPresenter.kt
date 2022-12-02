package tsm.bdg.ch6group.ui.leaderboard

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.Database1
import tsm.bdg.ch6group.databinding.ActivityLeaderBoardBinding

class LeaderBoardPresenter(
    context: Context,
    private val view: LeaderBoardActivity,
    private var binding: ActivityLeaderBoardBinding
) {
    val db = Database1.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun show() {
        GlobalScope.launch(Dispatchers.IO) {
            val leaderBoardHistory = db?.gameDao()?.getTotalWin()

            launch(Dispatchers.Main) {

                if (leaderBoardHistory != null) {
                    val adapterLoginHistoryActivity = RvLeaderBoardAdapter(leaderBoardHistory)
                   binding.rvLeaderBoard.adapter = adapterLoginHistoryActivity
                    view.showLeaderBoard()
                }
            }
        }
    }

}