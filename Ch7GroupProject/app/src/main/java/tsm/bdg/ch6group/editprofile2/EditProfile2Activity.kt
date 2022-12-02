package tsm.bdg.ch6group.editprofile2


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import tsm.bdg.ch6group.ResultState
import tsm.bdg.ch6group.databinding.ActivityEditProfile2Binding
import tsm.bdg.ch6group.ui.home.HomeActivity


class EditProfile2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfile2Binding
    private var uriFilePath = ""
    private lateinit var viewModel: EditProfile2ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityEditProfile2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EditProfile2ViewModel::class.java]
        // token harusnya disimpan di sharedprefrence
        val token = intent.getStringExtra("token") ?: ""

        binding.btnSubmit.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            viewModel.editProfile(token, uriFilePath, username, email)
            intent = Intent(this@EditProfile2Activity, HomeActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("token", token)
            startActivity(intent)
            finish()

        }

        binding.image.setOnClickListener {
            startCrop()
        }

        viewModel.resultProfile.observe(this) {
            when (it) {
                is ResultState.Success<*> -> {
                    Toast.makeText(this, "Success Save Profile", Toast.LENGTH_SHORT).show()
                }
                is ResultState.Error -> {
                    Toast.makeText(this, it.e.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is ResultState.Loading -> {
                    binding.progressBar.isVisible = it.loading
                }
            }
            binding.ivBack.setOnClickListener {
                finish()
            }
        }
    }


    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            val uriContent = result.uriContent
            uriFilePath = result.getUriFilePath(this).toString()
            binding.image.setImageURI(uriContent)
        } else {
            val exception = result.error
            Toast.makeText(this, exception?.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCrop() {
        cropImage.launch(options {
            setGuidelines(CropImageView.Guidelines.ON)
        })
    }
}