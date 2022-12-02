package tsm.bdg.ch6group.ui.loginhistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityLoginHistoryBinding
import tsm.bdg.ch6group.ui.history.HistoryPresenter

class LoginHistoryActivity : AppCompatActivity(), LoginHistoryView {

    private lateinit var binding: ActivityLoginHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val presenter =LoginHistoryPresenter(this, this, binding)

        presenter.show()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

     override fun showLoginHistory() {
        val layoutManagerLoginHistoryActivity = LinearLayoutManager(
            this@LoginHistoryActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvLoginHistory.layoutManager = layoutManagerLoginHistoryActivity
    }
}