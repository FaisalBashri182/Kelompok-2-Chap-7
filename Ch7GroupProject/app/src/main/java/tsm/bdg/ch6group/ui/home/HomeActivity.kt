package tsm.bdg.ch6group.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import coil.load
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.databinding.ActivityHomeBinding
import tsm.bdg.ch6group.ui.help.HelpActivity
import tsm.bdg.ch6group.data.local.model.GetUserResponse
import tsm.bdg.ch6group.ui.history.HistoryActivity
import tsm.bdg.ch6group.ui.leaderboard.LeaderBoardActivity
import tsm.bdg.ch6group.ui.loginhistory.LoginHistoryActivity
import tsm.bdg.ch6group.ui.menu.HalamanMenuActivity
import tsm.bdg.ch6group.ui.profile.ProfileActivity
import tsm.bdg.ch6group.ui.setting.AbcActivity
import tsm.bdg.ch6group.ui.shop.ShopActivity


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewModel: HomeViewModel

    private var foto = ""


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        val player = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val token = intent.getStringExtra("token")

        val sharedPreference = getSharedPreferences("Setting", MODE_PRIVATE)
        val sharedEdit = sharedPreference.edit()
        sharedEdit.putBoolean("checked", false)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivAvatarActivitySignUp.load(R.drawable.landing_page3)
        binding.tvNameActivitySignUp.text = player

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        if (token != null) {
            viewModel.getUser(token)
        }

        viewModel.afterLogin.observe(this) { state ->
            when (state) {
                is ResultState.Loading -> {
                    binding.progressBar.isVisible = state.loading
                }
                is ResultState.Success<*> -> {

                    val response = state.data as GetUserResponse

                    foto = response.data.photo

                    if (response.success) {

                        binding.ivAvatarActivitySignUp.load(foto)
                        binding.tvNameActivitySignUp.text = player



                    } else {
                        Toast.makeText(this@HomeActivity, "gagal login}", Toast.LENGTH_SHORT)
                            .show()
                        binding.ivAvatarActivitySignUp.load(R.drawable.landing_page3)

                    }
                }
                is ResultState.Error -> {
                    Toast.makeText(
                        this@HomeActivity, state.e.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                    binding.ivAvatarActivitySignUp.load(R.drawable.landing_page3)
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
            val intent = Intent(this, AbcActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfileActivitySignUp.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", player)
            intent.putExtra("email", email)
            intent.putExtra("token", token)
            intent.putExtra("foto", foto)

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

        binding.btnLoginHistory.setOnClickListener {
            val intent = Intent(this, LoginHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.btnLeaderBoard.setOnClickListener {
            val intent = Intent(this, LeaderBoardActivity::class.java)
            startActivity(intent)
        }

    }

}