package tsm.bdg.ch6group.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.Database1
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityProfileBinding
import tsm.bdg.ch6group.editprofile2.EditProfile2Activity
import tsm.bdg.ch6group.ui.editprofile.EditProfileActivity
import tsm.bdg.ch6group.ui.setting.AbcActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private var pathImage: String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val player = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val token = intent.getStringExtra("token")
        val foto = intent.getStringExtra("foto")


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

        binding.ivAvatar.load(foto)

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
        binding.btnEditProfile.setOnClickListener {

            val intent = Intent(this, EditProfile2Activity::class.java)
            intent.putExtra("username", player)
            intent.putExtra("email", email)
            intent.putExtra("token", token)

            startActivity(intent)

        }
        binding.btnWa.setOnClickListener {

            val message = "$player " +
                    " level :  ${binding.tvLevel.text} " +
                    " , jumlah menang : ${binding.tvNumberWin.text}" +
                    " , jumlah kalah : ${binding.tvNumberLose.text}"
            // Calling the function
            sendMessage(message)

        }

    }

    private fun sendMessage(message :String) {
        // Creating intent with action send
        val intent = Intent(Intent.ACTION_SEND)

        // Setting Intent type
        intent.type = "text/plain"

        // Setting whatsapp package name
        intent.setPackage("com.whatsapp")

        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, message)

        // Checking whether whatsapp is installed or not
        if (intent.resolveActivity(packageManager) == null) {
            Toast.makeText(this,
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Starting Whatsapp
        startActivity(intent)
    }
}