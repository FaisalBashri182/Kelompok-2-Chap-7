package tsm.bdg.ch6group.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.media.SoundPool
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.Database1
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityMainBinding
import tsm.bdg.ch6group.databinding.CustomDialogBinding
import tsm.bdg.ch6group.data.local.model.Game
import tsm.bdg.ch6group.ui.setting.Constant
import tsm.bdg.ch6group.ui.setting.PreferencesHelper
import java.util.*

class MainActivity : AppCompatActivity(), SoundPool.OnLoadCompleteListener, MainView {

    private lateinit var view: CustomDialogBinding

    private lateinit var binding: ActivityMainBinding

    var mSoundPool: SoundPool? = null

    lateinit var sharedSound: PreferencesHelper

    var soundOn = 0
    var soundPlayerWin = 0
    var soundComWin = 0
    var soundDraw = 0
    var soundRestart = 0
    var numSoundsLoaded = 0



    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedSound = PreferencesHelper(this)

        val playerName = intent.getStringExtra("nama")

        if (binding.tvNameActivityMain.text == "") binding.tvNameActivityMain.text = playerName

        view = CustomDialogBinding.inflate(LayoutInflater.from(this), null, false)

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setView(view.root)

        val dialog = dialogBuilder.create()

        val controller = MainPresenter(this)

        val player = arrayListOf(
            binding.ivBatuP,
            binding.ivKertasP,
            binding.ivGuntingP
        )

        val com = arrayListOf(
            binding.ivBatuC,
            binding.ivKertasC,
            binding.ivGuntingC
        )

        com.forEach { it.isEnabled = false }
        player.forEach { imageView ->
            imageView.setOnClickListener {

                com.forEach { it.isEnabled = false }

                when (imageView.contentDescription) {
                    "gunting" -> Toast.makeText(
                        this,
                        "$playerName Memilih Gunting",
                        Toast.LENGTH_SHORT
                    ).show()
                    "kertas" -> Toast.makeText(
                        this,
                        "$playerName Memilih Kertas",
                        Toast.LENGTH_SHORT
                    ).show()
                    "batu" -> Toast.makeText(this, "$playerName memilih Batu", Toast.LENGTH_SHORT)
                        .show()
                }

                imageView.selected()

                val lawan = com.random()

                lawan.selected()
                when (lawan.contentDescription) {
                    "gunting" -> Toast.makeText(this, "CPU Memilih Gunting", Toast.LENGTH_SHORT)
                        .show()
                    "kertas" -> Toast.makeText(this, "CPU Memilih Kertas", Toast.LENGTH_SHORT)
                        .show()
                    "batu" -> Toast.makeText(this, "CPU memilih Batu", Toast.LENGTH_SHORT).show()
                }

                controller.banding(
                    imageView.contentDescription.toString(),
                    lawan.contentDescription.toString()
                )

                view.btnBackToMenu.setOnClickListener {
                    finish()
                }

                view.btnMainLagi.setOnClickListener {
                    buttonStatusTrue()
                    dialog.dismiss()
                    player.forEach { imageView ->
                        imageView.unselected()
                    }
                    com.forEach { imageView ->
                        imageView.unselected()
                    }
                }
                dialog.show()
                buttonStatusFalse()
            }
        }

        binding.ivRefresh.setOnClickListener {
            Log.d("Click", "Click button Refresh")
            player.forEach { imageView ->
                imageView.unselected()
            }
            com.forEach { imageView ->
                imageView.unselected()
            }
            buttonStatusTrue()

        }
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        numSoundsLoaded = 0


        if (sharedSound.getBoolean(Constant.PREF_IS_SOUND)) {

            soundOn = 1

            initializeSoundPool()

            Toast.makeText(
                this@MainActivity,
                "soundOn = 1",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroySoundPool()

    }

    private fun destroySoundPool() {
        if (mSoundPool != null) {
            mSoundPool!!.release()
            mSoundPool = null
        }
    }

    override fun onLoadComplete(soundPool: SoundPool?, sampleId: Int, status: Int) {
        // let us know that a sound has been loaded by the SoundPool
        numSoundsLoaded++
        Toast.makeText(this, "Sound $numSoundsLoaded Loaded", Toast.LENGTH_SHORT).show()
    }

    private fun initializeSoundPool() {
        // create a SoundPool with API 21 and up:
        val spb = SoundPool.Builder()
        spb.setMaxStreams(4)
        mSoundPool = spb.build()

        // use onLoadComplete Listener implemented by Activity
        mSoundPool!!.setOnLoadCompleteListener(this)

        // load sound files into SoundPool using res -> raw id
        // parameters: (context, file_id, priority)
        soundPlayerWin = mSoundPool!!.load(this, R.raw.sound_player_win, 1)
        soundComWin = mSoundPool!!.load(this, R.raw.sound_com_win, 1)
        soundDraw = mSoundPool!!.load(this, R.raw.sound_draw, 1)
        soundRestart = mSoundPool!!.load(this, R.raw.sound_select, 1)
    }

    private fun buttonStatusFalse() {
        binding.ivGuntingP.isEnabled = false
        binding.ivBatuP.isEnabled = false
        binding.ivKertasP.isEnabled = false
    }

    private fun buttonStatusTrue() {
        binding.ivGuntingP.isEnabled = true
        binding.ivBatuP.isEnabled = true
        binding.ivKertasP.isEnabled = true
    }

    private fun ImageView.selected() {
        setBackgroundResource(R.drawable.bg_rounded)
    }

    private fun ImageView.unselected() {
        setBackgroundResource(R.drawable.bg_default)
    }

    override fun showResult(result: String) {

        view.result.text = result

        val playerName = intent.getStringExtra("nama")

        var dataWin: Int = 0
        var dataLose: Int = 0
        var dataDraw: Int = 0


        when (result) {
            "MENANG" -> {
                dataWin = 1
                if (soundOn == 1) mSoundPool!!.play(soundPlayerWin, 1f, 1f, 1, 1, 1f)
            }
            "KALAH" -> {
                dataLose = 1
                if (soundOn == 1) mSoundPool!!.play(soundComWin, 1f, 1f, 1, 1, 1f)
            }
            "SERI" -> {
                dataDraw = 1
                if (soundOn == 1) mSoundPool!!.play(soundDraw, 1f, 1f, 1, 1, 1f)

            }
        }


        // set database
        val dbGame = Database1.getInstance(this)

        var name = playerName

        if (name == null) {
            name = "the PLAYER"
        }


        val dataDb = Game(
            name = name,
            status = result,
            date = Date().toString(),
            win = dataWin,
            lose = dataLose,
            draw = dataDraw
        )

        GlobalScope.launch(Dispatchers.IO) {
            val status = dbGame?.gameDao()
                ?.insert(dataDb) ?: 0

            Log.e("status", status.toString())
            if (status > 1) {
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Data Game saved to Database",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}