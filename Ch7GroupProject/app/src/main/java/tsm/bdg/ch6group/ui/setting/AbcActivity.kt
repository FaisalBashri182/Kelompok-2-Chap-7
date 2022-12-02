package tsm.bdg.ch6group.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import tsm.bdg.ch6group.databinding.ActivityAbcBinding

class AbcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbcBinding

    lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityAbcBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedpref = PreferencesHelper(this)

        val sharedPreference = getSharedPreferences("Setting", MODE_PRIVATE)
        val sharedEdit = sharedPreference.edit()

        var checked = sharedPreference.getBoolean("checked", false)

        val sharedPreferenceSound = getSharedPreferences("SOUND_PREF", MODE_PRIVATE)
        val sharedEditSound = sharedPreferenceSound.edit()

        var checkedSound = sharedPreferenceSound.getBoolean("CHECKED_SOUND", false)

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

        binding.swcMusic.isChecked = checkedSound

        binding.swcMusic.setOnCheckedChangeListener { _, isChecked ->
            checkedSound = if (isChecked) {
                sharedpref.putBoolean(Constant.PREF_IS_SOUND, true)
                true
            }
            else {
                sharedpref.putBoolean(Constant.PREF_IS_SOUND, false)
                false
            }
        }


        binding.ivBack.setOnClickListener {
            finish()
            sharedEdit.putBoolean("checked",checked)
            sharedEditSound.putBoolean("CHECKED_SOUND", checkedSound)
        }
    }

}
