package tsm.bdg.ch6group.ui.menu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import tsm.bdg.ch6group.databinding.ActivityHalamanMenuBinding
import tsm.bdg.ch6group.ui.main.MainActivity
import tsm.bdg.ch6group.ui.main.MainActivity2

class HalamanMenuActivity : AppCompatActivity(),HalamanMenuView {


    private lateinit var binding: ActivityHalamanMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHalamanMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val player = intent.getStringExtra("name")

        val presenter = HalamanMenuPresenter(binding,this)
        presenter.click()

        if (binding.tvNamaPlayer.text == "") binding.tvNamaPlayerInCPUMode.text = player
        if (binding.tvNamaPlayer.text == "") binding.tvNamaPlayer.text = player

        val snackBar =
            Snackbar.make(binding.textView, "Selamat Datang " + player, Snackbar.LENGTH_INDEFINITE)
        snackBar.setActionTextColor(Color.parseColor("#FFFF9800"))
        snackBar.setAction("Tutup") {
            snackBar.dismiss()
        }
        snackBar.show()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onPlayer() {
        val player = intent.getStringExtra("name")
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("nama", player)
        startActivity(intent)
    }

    override fun onCPU() {
        val player = intent.getStringExtra("name")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nama", player)
        startActivity(intent)
    }
}