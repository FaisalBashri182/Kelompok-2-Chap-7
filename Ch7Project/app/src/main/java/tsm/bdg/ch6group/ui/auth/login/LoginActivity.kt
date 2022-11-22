package tsm.bdg.ch6group.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tsm.bdg.ch6group.databinding.ActivityLoginBinding
import tsm.bdg.ch6group.ui.home.HomeActivity
import tsm.bdg.ch6group.ui.auth.signup.SignUpActivity

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val clickSignUp = binding.tvSignUpActivityLogin

        clickSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        val presenter = LoginPresenter(this,this, binding)

        binding.btnSignIn.setOnClickListener {
            presenter.login()
        }
    }

    override fun onSuccess() {
        val nameInput = binding.etUserNameActivityLogin.text.toString()
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)

        intent.putExtra("name", nameInput)
        startActivity(intent)
    }

    override fun onErrorNotFilled() {
        Toast.makeText(
            this@LoginActivity,
            "Please fill all the requirement",
            Toast.LENGTH_SHORT
        ).show()
        binding.etUserNameActivityLogin.text.clear()
        binding.etPasswordActivityLogin.text.clear()
    }

    override fun onErrorNoAccount() {
        Toast.makeText(
            this@LoginActivity,
            "Account is not registered",
            Toast.LENGTH_SHORT
        ).show()
        binding.etUserNameActivityLogin.text.clear()
        binding.etPasswordActivityLogin.text.clear()
    }
}