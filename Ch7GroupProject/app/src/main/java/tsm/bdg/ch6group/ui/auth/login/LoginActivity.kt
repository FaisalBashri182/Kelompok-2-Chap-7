package tsm.bdg.ch6group.ui.auth.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.data.local.Dblogin
import tsm.bdg.ch6group.data.local.model.Login
import tsm.bdg.ch6group.data.local.model.LoginResponse
import tsm.bdg.ch6group.databinding.ActivityLoginBinding
import tsm.bdg.ch6group.ui.home.HomeActivity
import tsm.bdg.ch6group.ui.auth.signup.SignUpActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class LoginActivity : AppCompatActivity(), LoginView {


    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnSignIn.setOnClickListener {

            val email = binding.etUserNameActivityLogin.text.toString()
            val password = binding.etPasswordActivityLogin.text.toString()

            when {
                email.isEmpty() -> {
                    binding.etUserNameActivityLogin.error = "Username tidak boleh kosong"
                    binding.etUserNameActivityLogin.requestFocus()
                }
                password.isEmpty() -> {
                    binding.etPasswordActivityLogin.error = "Password tidak boleh kosong"
                    binding.etPasswordActivityLogin.requestFocus()
                }
                else -> {
                    viewModel.login(email, password)
                }
            }

            val presenter = LoginPresenter(this, this, binding)

            presenter.login()

            saveLoginDb()


        }

        viewModel.resultLogin.observe(this) { state ->
            when (state) {
                is ResultState.Loading -> {
                    binding.progressBar.isVisible = state.loading
                }
                is ResultState.Success<*> -> {
                    val response = state.data as LoginResponse
//
                    if (response.success) {
//                        Intent(this, ProfileActivity::class.java).apply {
                        Intent(this, HomeActivity::class.java).apply {
                            // token ini best practicenya adalah disimpan di sharedpreferce
                            putExtra("token", response.data.token)
                            putExtra("username", response.data.username)
                            putExtra("email", response.data.email)

                            startActivity(this)
                        }
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "gagal login}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is ResultState.Error -> {
                    Toast.makeText(
                        this@LoginActivity, state.e.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val clickSignUp = binding.tvSignUpActivityLogin

        clickSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveLoginDb() {
        // set database
        val dbLogin = Dblogin.getInstance(this)

        val email = binding.etUserNameActivityLogin.text.toString()
        val avatar = "R.drawable.landing_page3"

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)

        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formattedTime = current.format(formatterTime)

        val dataDb = Login(
            avatar = avatar,
            name = email,
            date = formatted,
            time = formattedTime
        )

        GlobalScope.launch(Dispatchers.IO) {
            val status = dbLogin?.loginDao()
                ?.insert(dataDb) ?: 0

            Log.e("status", status.toString())
            if (status > 1) {
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Data saved to Database",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onSuccess() {
//        val nameInput = binding.etUserNameActivityLogin.text.toString()
//        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
//
//        intent.putExtra("name", nameInput)
//        startActivity(intent)
    }

    override fun onErrorNotFilled() {
        Toast.makeText(
            this@LoginActivity,
            "Please fill all the requirement",
            Toast.LENGTH_SHORT
        ).show()
//        binding.etUserNameActivityLogin.text.clear()
//        binding.etPasswordActivityLogin.text.clear()
    }

    override fun onErrorNoAccount() {
        Toast.makeText(
            this@LoginActivity,
            "Account is not registered",
            Toast.LENGTH_SHORT
        ).show()
//        binding.etUserNameActivityLogin.text.clear()
//        binding.etPasswordActivityLogin.text.clear()
    }
}




