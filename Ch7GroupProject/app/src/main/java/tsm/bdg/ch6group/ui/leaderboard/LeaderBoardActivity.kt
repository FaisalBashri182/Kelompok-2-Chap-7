package tsm.bdg.ch6group.ui.leaderboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import tsm.bdg.ch6group.databinding.ActivityLeaderBoardBinding

class LeaderBoardActivity : AppCompatActivity(), LeaderBoardView {

    private lateinit var binding : ActivityLeaderBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val presenter = LeaderBoardPresenter(this, this, binding)

        presenter.show()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun showLeaderBoard() {
        val layoutManagerLoginHistoryActivity = LinearLayoutManager(
            this@LeaderBoardActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvLeaderBoard.layoutManager = layoutManagerLoginHistoryActivity
    }


}