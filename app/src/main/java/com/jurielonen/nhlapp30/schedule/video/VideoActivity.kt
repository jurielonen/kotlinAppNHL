package com.jurielonen.nhlapp30.schedule.video

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView

    private var playbackPosition: Long = 0
    private var currentWindow: Int = 0
    private var playWhenReady = true
    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityVideoBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_video)
        playerView = binding.videoView
        url = intent.getStringExtra("URL")
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        hideSystemUi()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        playbackPosition = savedInstanceState?.getLong("PLAYBACK")?: 0
        currentWindow = savedInstanceState?.getInt("WINDOW")?: 0
        playWhenReady = savedInstanceState?.getBoolean("PLAYWHENREADY")?: true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putLong("PLAYBACK", playbackPosition)
            putInt("WINDOW", currentWindow)
            putBoolean("PLAYWHENREADY", playWhenReady)
        }
        super.onSaveInstanceState(outState)
    }

    private fun initializePlayer() {
        if (player == null)
        {
            player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(applicationContext),
                DefaultTrackSelector(), DefaultLoadControl())

            playerView.player = this.player
            player!!.playWhenReady = playWhenReady
            player!!.seekTo(currentWindow, playbackPosition)
        }
        val mediaSource = buildMediaSource(Uri.parse(url))
        this.player!!.prepare(mediaSource, false, true)
    }

    private fun releasePlayer() {
        if (player != null)
        {
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            playWhenReady = player!!.playWhenReady
            player!!.release()
            player = null
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(DefaultHttpDataSourceFactory("exoplayer-codelab"))
            .createMediaSource(uri)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun hideSystemUi() {
        playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

}
