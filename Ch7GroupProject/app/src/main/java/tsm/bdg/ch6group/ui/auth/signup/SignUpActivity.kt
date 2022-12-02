package tsm.bdg.ch6group.ui.auth.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.data.local.model.RegisterResponse
import tsm.bdg.ch6group.data.local.model.User
import tsm.bdg.ch6group.databinding.ActivitySignUpBinding
import tsm.bdg.ch6group.databinding.CustomRegisterDialogBinding
import tsm.bdg.ch6group.ui.auth.login.LoginActivity


class SignUpActivity : AppCompatActivity() {

//    private lateinit var view: CustomRegisterDialogBinding

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var viewModel: SignUpViewModel

    private var dataAvatar: String = "1"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]


        binding.btnSignUpActivitySignUp.setOnClickListener {

            val email = binding.etEmailActivitySignUp.text.toString()
            val username = binding.etUserNameActivitySignUp.text.toString()
            val password = binding.etEnterPasswordActivitySignUp.text.toString()

            when {
                email.isEmpty() -> {
                    binding.etEmailActivitySignUp.error = "Email tidak boleh kosong"
                    binding.etEmailActivitySignUp.requestFocus()
                }
                username.isEmpty() -> {
                    binding.etUserNameActivitySignUp.error = "Username tidak boleh kosong"
                    binding.etUserNameActivitySignUp.requestFocus()
                }
                password.isEmpty() -> {
                    binding.etEnterPasswordActivitySignUp.error = "Password tidak boleh kosong"
                    binding.etEnterPasswordActivitySignUp.requestFocus()
                }
                else -> {
                    viewModel.register(email, username, password)
                }
            }
        }

        viewModel.resultRegister.observe(this) { state ->
            when (state) {
                is ResultState.Loading -> {
                    binding.progressBar.isVisible = state.loading
                }
                is ResultState.Success<*> -> {
                    val response = state.data as RegisterResponse
                    if (response.success) {
                        Intent(this, LoginActivity::class.java).apply {
                            startActivity(this)
                        }
                        finish()
                    } else {
                        Toast.makeText(this@SignUpActivity, "gagal login}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is ResultState.Error -> {
                    Toast.makeText(
                        this@SignUpActivity, state.e.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

}