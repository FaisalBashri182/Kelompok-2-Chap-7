package tsm.bdg.ch6group.ui.help

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.databinding.ActivityVideoBinding


class VideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVideoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnPlayVideo.setOnClickListener {

            val videoUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.how_to_play_rock_paper_scissors)

            binding.video.setVideoURI(videoUri)
            binding.video.start()
        }
        binding.btnStopVideo.setOnClickListener {

            val videoUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.how_to_play_rock_paper_scissors)

            binding.video.stopPlayback()

            binding.video.setVideoURI(videoUri)
        }
        binding.btnPauseVideo.setOnClickListener {
            binding.video.pause()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}