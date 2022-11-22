package tsm.bdg.ch6group.ui.auth.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.data.model.User
import tsm.bdg.ch6group.databinding.ActivitySignUpBinding
import tsm.bdg.ch6group.databinding.CustomRegisterDialogBinding

class SignUpActivity : AppCompatActivity(), SignUpView {

    private lateinit var view: CustomRegisterDialogBinding

    private lateinit var binding: ActivitySignUpBinding

    private var dataAvatar: String = "1"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val presenter = SignUpPresenter(this, this, binding)

        val srcImage1: ImageButton = binding.ivAvatar1ActivitySignUp
        val srcImage2: ImageButton = binding.ivAvatar2ActivitySignUp
        val srcImage3: ImageButton = binding.ivAvatar3ActivitySignUp
        val srcImage4: ImageButton = binding.ivAvatar4ActivitySignUp

        srcImage1.setOnClickListener {
            srcImage1.setBackgroundResource(R.drawable.bg_rounded) //selected
            srcImage2.setBackgroundResource(R.drawable.bg_default) //unselected
            srcImage3.setBackgroundResource(R.drawable.bg_default)
            srcImage4.setBackgroundResource(R.drawable.bg_default)
            dataAvatar = "1"
        }
        srcImage2.setOnClickListener {
            srcImage2.setBackgroundResource(R.drawable.bg_rounded)//selected
            srcImage1.setBackgroundResource(R.drawable.bg_default)//unselected
            srcImage3.setBackgroundResource(R.drawable.bg_default)
            srcImage4.setBackgroundResource(R.drawable.bg_default)
            dataAvatar = "2"
        }
        srcImage3.setOnClickListener {
            srcImage3.setBackgroundResource(R.drawable.bg_rounded)//selected
            srcImage2.setBackgroundResource(R.drawable.bg_default)//unselected
            srcImage1.setBackgroundResource(R.drawable.bg_default)
            srcImage4.setBackgroundResource(R.drawable.bg_default)
            dataAvatar = "3"
        }
        srcImage4.setOnClickListener {
            srcImage4.setBackgroundResource(R.drawable.bg_rounded)//selected
            srcImage2.setBackgroundResource(R.drawable.bg_default)//unselected
            srcImage3.setBackgroundResource(R.drawable.bg_default)
            srcImage1.setBackgroundResource(R.drawable.bg_default)
            dataAvatar = "4"
        }

        binding.btnSignUpActivitySignUp.setOnClickListener {
            val dataName = binding.etUserNameActivitySignUp.text.trim().toString()
            val dataEmail = binding.etEmailActivitySignUp.text.trim().toString()
            val dataPassword = binding.etEnterPasswordActivitySignUp.text.trim().toString()

            val dataUser = User(
                name = dataName,
                email = dataEmail,
                password = dataPassword,
                avatar = dataAvatar
            )
            presenter.signUp(dataUser)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun clearText() {
        binding.etUserNameActivitySignUp.text.clear()
        binding.etEmailActivitySignUp.text.clear()
        binding.etEnterPasswordActivitySignUp.text.clear()
        binding.etReEnterPasswordActivitySignUp.text.clear()
    }

    override fun onSuccess() {
        view = CustomRegisterDialogBinding.inflate(LayoutInflater.from(this), null, false)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(view.root)
        val dialog = dialogBuilder.create()
        dialog.show()

        view.btnNext.setOnClickListener {
            finish()
            dialog.dismiss()
        }
    }

    override fun onErrorNotFilled() {
        Toast.makeText(
            this@SignUpActivity,
            "Please fill all the requirement",
            Toast.LENGTH_SHORT
        ).show()
        this.clearText()
    }

    override fun onErrorPassNotMatch() {
        Toast.makeText(
            this@SignUpActivity,
            "Make sure both password are the same !",
            Toast.LENGTH_SHORT
        ).show()
    }
}