package tsm.bdg.ch6group.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.Database1
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.data.model.Game
import tsm.bdg.ch6group.databinding.ActivityMain2Binding
import tsm.bdg.ch6group.databinding.CustomDialogBinding
import java.util.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var view: CustomDialogBinding

    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerName = intent.getStringExtra("nama")

        if (binding.textView2.text == "") binding.textView2.text = playerName

        view = CustomDialogBinding.inflate(LayoutInflater.from(this), null, false)

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setView(view.root)

        val dialog = dialogBuilder.create()


        val controller = MainPresenter2(this)

        var player2Input = ""
        var playerInput = ""

        val player = arrayListOf(
            binding.ivBatuP, binding.ivKertasP, binding.ivGuntingP
        )

        val player2 = arrayListOf(
            binding.ivBatuP2, binding.ivKertasP2, binding.ivGuntingP2
        )

        player2.forEach { imageView ->
            imageView.isEnabled = false
        }

        player.forEach { imageView ->
            imageView.setOnClickListener {
                when (imageView.contentDescription) {
                    "gunting" -> Toast.makeText(
                        this, "$playerName Memilih Gunting", Toast.LENGTH_SHORT
                    ).show()
                    "kertas" -> Toast.makeText(
                        this, "$playerName Memilih Kertas", Toast.LENGTH_SHORT
                    ).show()
                    "batu" -> Toast.makeText(this, "$playerName memilih Batu", Toast.LENGTH_SHORT)
                        .show()
                }

                imageView.selected()
                buttonStatusPlayerFalse()
                buttonStatusPlayer2True()
                imageView.contentDescription.toString()
                playerInput = imageView.contentDescription.toString()
                when (player2Input) {
                    "batu", "gunting", "kertas" -> controller.banding(playerInput, player2Input)
                }
            }
        }

        player2.forEach { imageView ->
            imageView.setOnClickListener {
                when (imageView.contentDescription) {
                    "gunting" -> Toast.makeText(
                        this, "Player 2 Memilih Gunting", Toast.LENGTH_SHORT
                    ).show()
                    "kertas" -> Toast.makeText(this, "Player 2 Memilih Kertas", Toast.LENGTH_SHORT)
                        .show()
                    "batu" -> Toast.makeText(this, "Player 2 memilih Batu", Toast.LENGTH_SHORT)
                        .show()
                }

                imageView.selected()
                buttonStatusPlayer2False()
                imageView.contentDescription.toString()
                player2Input = imageView.contentDescription.toString()



                view.btnBackToMenu.setOnClickListener {
                    finish()
                }

                view.btnMainLagi.setOnClickListener {
                    buttonStatusPlayerTrue()
                    buttonStatusPlayer2False()
                    dialog.dismiss()
                    player.forEach { imageView ->
                        imageView.unselected()
                    }
                    player2.forEach { imageView ->
                        imageView.unselected()
                    }
                    playerInput = ""
                    player2Input = ""
                }
                dialog.show()

                when (playerInput) {
                    "batu", "kertas", "gunting" -> controller.banding(playerInput, player2Input)
                }
            }
        }

        binding.ivRefresh.setOnClickListener {
            buttonStatusPlayerTrue()
            buttonStatusPlayer2False()
            player.forEach { imageView ->
                imageView.unselected()
            }
            player2.forEach { imageView ->
                imageView.unselected()
            }
            playerInput = ""
            player2Input = ""
        }
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun buttonStatusPlayerFalse() {
        binding.ivGuntingP.isEnabled = false
        binding.ivBatuP.isEnabled = false
        binding.ivKertasP.isEnabled = false
    }

    private fun buttonStatusPlayer2False() {
        binding.ivGuntingP2.isEnabled = false
        binding.ivBatuP2.isEnabled = false
        binding.ivKertasP2.isEnabled = false
    }

    private fun buttonStatusPlayerTrue() {
        binding.ivGuntingP.isEnabled = true
        binding.ivBatuP.isEnabled = true
        binding.ivKertasP.isEnabled = true
    }

    private fun buttonStatusPlayer2True() {
        binding.ivGuntingP2.isEnabled = true
        binding.ivBatuP2.isEnabled = true
        binding.ivKertasP2.isEnabled = true
    }

    private fun ImageView.selected() {
        setBackgroundResource(R.drawable.bg_rounded)
    }

    private fun ImageView.unselected() {
        setBackgroundResource(R.drawable.bg_default)
    }

    fun showResult(result: String) {
        view.result.text = result

        val playerName = intent.getStringExtra("nama")

        var dataWin: Int = 0
        var dataLose: Int = 0
        var dataDraw: Int = 0


        when (result) {
            "Pemain 1 MENANG!" -> {
                dataWin = 1
            }
            "Pemain 2 MENANG!" -> {
                dataLose = 1
            }
            "Draw" -> {
                dataDraw = 1
            }

        }


        // set database
        val dbGame = Database1.getInstance(this)

        var name = playerName

        if (name == null) {
            name = "the PLAYER"
        }


        val dataDb = Game(
            name = name.toString(),
            status = result,
            date = Date().toString(),
            win = dataWin,
            lose = dataLose,
            draw = dataDraw
        )

        GlobalScope.launch(Dispatchers.IO) {
            val status = dbGame?.gameDao()?.insert(dataDb) ?: 0

            Log.e("status", status.toString())
            if (status > 1) {
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity2, "Data Game saved to Database", Toast.LENGTH_LONG
                    ).show()

                }
            }
        }


    }
}