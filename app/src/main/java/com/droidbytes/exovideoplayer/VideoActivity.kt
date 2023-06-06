package com.droidbytes.exovideoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.droidbytes.exovideoplayer.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer

class VideoActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoBinding
    lateinit var player : SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player=SimpleExoPlayer.Builder(this).build()

        var uri = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        binding.playerview.player=player

        player.addListener(object : Player.Listener{
            override fun onPlaybackStateChanged(playbackState: Int) {
                when(playbackState){
                    Player.STATE_BUFFERING->{
                        binding.progressbar.visibility=View.VISIBLE
                    }
                    Player.STATE_READY,Player.STATE_ENDED->{
                        binding.progressbar.visibility=View.GONE
                    }
                }
            }
        })
        var mediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }
}