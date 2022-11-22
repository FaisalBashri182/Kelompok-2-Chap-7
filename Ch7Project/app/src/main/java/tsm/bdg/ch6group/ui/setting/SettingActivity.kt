package tsm.bdg.ch6group.ui.setting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import tsm.bdg.ch6group.databinding.ActivitySettingBinding

@Suppress("UNUSED_EXPRESSION")
class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences("Setting", MODE_PRIVATE)
        val sharedEdit = sharedPreference.edit()
        var checked = sharedPreference.getBoolean("checked",false)

        binding.swcTheme.isChecked = checked

        binding.swcTheme.setOnCheckedChangeListener { _, isChecked ->
            checked = if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                false
            }

        }
        binding.ivBack.setOnClickListener {
            finish()
            sharedEdit.putBoolean("checked",checked)
        }
    }
}