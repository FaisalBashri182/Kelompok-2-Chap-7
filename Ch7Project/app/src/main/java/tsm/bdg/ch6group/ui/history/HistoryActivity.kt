package tsm.bdg.ch6group.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tsm.bdg.ch6group.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity(), HistoryView {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val presenter = HistoryPresenter(this, this, binding)

        presenter.show()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun showHistory() {
        val layoutManagerHistoryActivity = LinearLayoutManager(
            this@HistoryActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvHistory.layoutManager = layoutManagerHistoryActivity
    }
}