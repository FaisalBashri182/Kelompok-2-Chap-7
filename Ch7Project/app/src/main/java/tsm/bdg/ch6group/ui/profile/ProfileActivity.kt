package tsm.bdg.ch6group.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.Database1
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private var pathImage: String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val player = intent.getStringExtra("name")

        pathImage = intent.getStringExtra("imageAvatar").toString()

        binding.tvUserNameActivityProfile.text = player

        when (pathImage) {
            "1" -> {
                binding.ivAvatar.setImageResource(R.drawable.avatar1_svgrepo_com)

            }
            "2" -> {
                binding.ivAvatar.setImageResource(R.drawable.avatar2_svgrepo_com)

            }
            "3" -> {
                binding.ivAvatar.setImageResource(R.drawable.avatar3_svgrepo_com)

            }
            "4" -> {
                binding.ivAvatar.setImageResource(R.drawable.avatar4_svgrepo_com)

            }

        }

        val db = Database1.getInstance(this)

        GlobalScope.launch(Dispatchers.IO) {
            val win = db?.gameDao()?.getWin(player.toString())
            val lose = db?.gameDao()?.getLose(player.toString())
            launch(Dispatchers.Main) {

                if (win != null) {

                    val adapterProfileActivity = RvAdapterWin(win)

                    val layoutManagerProfileActivity = LinearLayoutManager(
                        this@ProfileActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )

                    if (lose != null) {

                        val adapterProfileActivity1 = RvAdapterLose(lose)

                        val layoutManagerProfileActivity1 = LinearLayoutManager(
                            this@ProfileActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )

                        val winNumber = adapterProfileActivity.itemCount

                        val loseNumber = adapterProfileActivity1.itemCount

                        binding.rvWinProfileActivity.layoutManager = layoutManagerProfileActivity
                        binding.rvWinProfileActivity.adapter = adapterProfileActivity
                        binding.tvNumberWin.text = winNumber.toString()
                        binding.rvLoseProfileActivity.layoutManager = layoutManagerProfileActivity1
                        binding.rvLoseProfileActivity.adapter = adapterProfileActivity1
                        binding.tvNumberLose.text = loseNumber.toString()


                        when (winNumber - loseNumber) {
                            in -1..7 -> binding.tvLevel.text = "PEMULA"
                            in 8..10 -> binding.tvLevel.text = "SEMI PRO"
                            in 11..15 -> binding.tvLevel.text = "PRO"
                        }
                    }
                }
            }
        }
        binding.btnSignOut.setOnClickListener {
            finish()
            onDestroy()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}