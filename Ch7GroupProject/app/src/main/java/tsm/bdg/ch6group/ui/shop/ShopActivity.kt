package tsm.bdg.ch6group.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var point = 1000 // Dinamis data (didapat dari API)
        var itemKey : String // Dinamis data (dikirim ke API)

        val piratesPrice = 100 // Statis data
        val spartanPrice = 500 // Statis data
        val alienPrice = 300 // Statis data

        binding.tvShowPoint.text = point.toString()
        binding.tvPrice1.text = piratesPrice.toString()
        binding.tvPrice2.text = spartanPrice.toString()
        binding.tvPrice3.text = alienPrice.toString()

        if (point>=piratesPrice){
            binding.ivAvatarSkinPirates.setOnClickListener {
                binding.ivShowAvatar.setImageResource(R.drawable.avatar1_svgrepo_com)
                it.isEnabled = false
                binding.tvStatusText1.isVisible = true
                point -= piratesPrice
                binding.tvShowPoint.text = point.toString()

                itemKey = "avatar-pirates"
            }

        } else

        if (point>=spartanPrice){
            binding.ivAvatarSkinSpartan.setOnClickListener {
                binding.ivShowAvatar.setImageResource(R.drawable.avatar2_svgrepo_com)
                it.isEnabled = false
                binding.tvStatusText2.isVisible = true
                point -= spartanPrice
                binding.tvShowPoint.text = point.toString()

                itemKey = "avatar-spartan"
            }
        }

        if (point>=alienPrice){
            binding.ivAvatarSkinAlien.setOnClickListener {
                binding.ivShowAvatar.setImageResource(R.drawable.avatar3_svgrepo_com)
                it.isEnabled = false
                binding.tvStatusText3.isVisible = true
                point -= alienPrice
                binding.tvShowPoint.text = point.toString()

                itemKey = "avatar-alien"
            }
        }

        if (point<piratesPrice||point<spartanPrice||point<alienPrice)
            Toast.makeText(this@ShopActivity,"Not Enough Point", Toast.LENGTH_SHORT).show()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}