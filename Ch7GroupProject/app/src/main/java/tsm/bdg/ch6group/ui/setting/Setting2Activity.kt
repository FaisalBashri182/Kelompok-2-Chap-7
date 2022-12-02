package tsm.bdg.ch6group.ui.setting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import tsm.bdg.ch6group.databinding.ActivitySetting2Binding

@Suppress("UNUSED_EXPRESSION")
class Setting2Activity : AppCompatActivity() {
    private lateinit var bindingNew : ActivitySetting2Binding


    lateinit var sharedpref : PreferencesHelper

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNew = ActivitySetting2Binding.inflate(layoutInflater)
        setContentView(bindingNew.root)

        sharedpref = PreferencesHelper(this)

        val sharedPreference = getSharedPreferences("Setting", MODE_PRIVATE)
        val sharedEdit = sharedPreference.edit()

        var checked = sharedPreference.getBoolean("checked",false)

        val sharedPreferenceSound = getSharedPreferences("SOUND_PREF", MODE_PRIVATE)
        val sharedEditSound = sharedPreferenceSound.edit()

        var checkedSound = sharedPreferenceSound.getBoolean("CHECKED_SOUND", false)

        bindingNew.swcTheme.isChecked = checked

        bindingNew.swcTheme.setOnCheckedChangeListener { _, isChecked ->
            checked = if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                false
            }

        }

        bindingNew.swcSound.isChecked = checkedSound

        bindingNew.swcSound.setOnCheckedChangeListener { _, isChecked ->
            checkedSound = if (isChecked) {
                sharedpref.putBoolean(Constant.PREF_IS_SOUND, true)
                true
            }
            else {
                sharedpref.putBoolean(Constant.PREF_IS_SOUND, false)
                false
            }
        }

        bindingNew.ivBack.setOnClickListener {
            finish()
            sharedEdit.putBoolean("checked",checked)
            sharedEditSound.putBoolean("CHECKED_SOUND", checkedSound)
        }
    }
}

//class Setting2Activity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_setting2)
//    }
//}