package tsm.bdg.ch6group.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityHomeBinding
import tsm.bdg.ch6group.ui.help.HelpActivity
import tsm.bdg.ch6group.data.local.Db
import tsm.bdg.ch6group.ui.history.HistoryActivity
import tsm.bdg.ch6group.ui.menu.HalamanMenuActivity
import tsm.bdg.ch6group.ui.profile.ProfileActivity
import tsm.bdg.ch6group.ui.setting.SettingActivity
import tsm.bdg.ch6group.ui.shop.ShopActivity

class HomeActivity : AppCompatActivity(), HomeView {

    private lateinit var binding: ActivityHomeBinding

    private var dataImage : String = "1"

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("CommitPrefEdits", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPreference = getSharedPreferences("Setting", MODE_PRIVATE)
        val sharedEdit = sharedPreference.edit()
        sharedEdit.putBoolean("checked", false)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val player = intent.getStringExtra("name")

        binding.tvNameActivitySignUp.text = player

        val db = Db.getInstance(this)

        val presenter = HomePresenter(this,this)

        presenter.menu()

        GlobalScope.launch(Dispatchers.IO) {
            val avatar = db?.userDao()?.getName(player.toString())
            launch(Dispatchers.Main) {

                dataImage = avatar?.avatar.toString()

                when (dataImage) {
                    "1" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar1_svgrepo_com)
                    }
                    "2" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar2_svgrepo_com)
                    }
                    "3" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar3_svgrepo_com)
                    }
                    "4" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar4_svgrepo_com)
                    }
                }
            }
        }

        binding.btnExitActivityHome.setOnClickListener {
            finish()
            onDestroy()
        }

        binding.btnPlayGameActivityHome.setOnClickListener {
            val intent = Intent(this, HalamanMenuActivity::class.java)
            intent.putExtra("name", player)
            startActivity(intent)
        }

        binding.btnSettingActivityHome.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfileActivitySignUp.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("name", player)
            intent.putExtra("imageAvatar", dataImage)
            startActivity(intent)
        }

        binding.btnHelpActivityHome.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        binding.btnHistoryActivityHome.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("name", player)
            startActivity(intent)
        }

        binding.btnShopActivity.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            intent.putExtra("name", player)
            startActivity(intent)
        }
    }
    @SuppressLint("CommitPrefEdits")
    override fun onMenu() {}
}