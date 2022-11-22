package tsm.bdg.ch6group.ui.intro

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import tsm.bdg.ch6group.databinding.ActivityFragmentSpaceBinding

class FragmentSpaceActivity : AppCompatActivity() {
    private val changeListenerVP2 = object : ViewPager2.OnPageChangeCallback() {
        @SuppressLint("LongLogTag")
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Log.e("sekarang di posisi ke ->", position.toString())
            binding.btnNext.isVisible = position != 2
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.VP2.unregisterOnPageChangeCallback(changeListenerVP2)
    }

    private lateinit var binding: ActivityFragmentSpaceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFragmentSpaceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val listFragment = mutableListOf(
            LandingPage(),
            LandingPage2(),
            LandingPage3(),
        )
        val wormDotsIndicator = binding.dotsIndicator
        val viewPager2 = binding.VP2
        val adapterViewPager2 = AdapterVP2(this,listFragment)

        binding.VP2.adapter = adapterViewPager2
        wormDotsIndicator.attachTo(viewPager2)

        binding.btnNext.setOnClickListener {
            val currentPosition = binding.VP2.currentItem
            binding.VP2.setCurrentItem(currentPosition+1,true)
        }
        binding.VP2.registerOnPageChangeCallback(changeListenerVP2)

    }
}