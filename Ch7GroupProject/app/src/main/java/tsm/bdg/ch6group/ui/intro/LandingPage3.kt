package tsm.bdg.ch6group.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tsm.bdg.ch6group.ui.auth.login.LoginActivity
import tsm.bdg.ch6group.databinding.FragmentLandingPage3Binding

class LandingPage3 : Fragment() {

    private lateinit var binding: FragmentLandingPage3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLandingPage3Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGetStarted.setOnClickListener {

            val intent = Intent(activity, LoginActivity::class.java)
            activity?.finish()
            activity?.startActivity(intent)

        }
    }
}