package com.example.morefit.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.morefit.databinding.ActivityVideoCallBinding
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig
import io.agora.rtc2.video.VideoCanvas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.abs

class VideoCallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoCallBinding

    private val appId = "c33858c359d240c988b0057a0b1d6b1f"
    private val channelName = "CHANNEL_NAME"
    private val token =
        "007eJxTYChgY/k1+Z6UVO+C1RerPrxafUV0isiTjZ+eH7zTWyOacidagSHZ1MLA0iDR1DgtzdzEwtjUwtAs0dLU2CTF3CDZzCjJfEf99ZSGQEYGo47ZTIwMEAji8zA4ezj6+bn6xPs5+royMAAAB7ojWg=="
    private val uid = 0
    private var isJoined = false
    private var agoraEngine: RtcEngine? = null
    private var localSurfaceView: SurfaceView? = null
    private var remoteSurfaceView: SurfaceView? = null

    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionsToRequest = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
        requestMultiplePermissions.launch(permissionsToRequest)

        countDownTimer = object : CountDownTimer(30 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minute = millisUntilFinished / (60 * 1000)
                val second =
                    abs((millisUntilFinished % (60 * 1000) - millisUntilFinished / (60 * 1000))).toString()
                        .substring(0, 2)
                binding.timer.text = "$minute:$second Left"
            }

            override fun onFinish() {}
        }
        countDownTimer.start()

        binding.LeaveButton.setOnClickListener {
            leaveChannel()
            finish()
        }

        setupVideoSDKEngine()
        joinChannel()
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allPermissionsGranted = permissions.all { it.value }
            if (!allPermissionsGranted) {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }

    private fun setupVideoSDKEngine() {
        try {
            val config = RtcEngineConfig()
            config.mContext = baseContext
            config.mAppId = appId
            config.mEventHandler = mRtcEventHandler
            agoraEngine = RtcEngine.create(config)
            // By default, the video module is disabled, call enableVideo to enable it.
            agoraEngine?.enableVideo()
        } catch (e: Exception) {
            Log.d("video_call", e.message.toString())
        }
    }

    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        // Listen for the remote host joining the channel to get the uid of the host.
        override fun onUserJoined(uid: Int, elapsed: Int) {
            Log.d("video_call", "Remote user joined $uid")

            // Set the remote video view
//            runOnUiThread {
            lifecycleScope.launch(Dispatchers.Main) {
                setupRemoteVideo(uid)
            }
        }

        override fun onJoinChannelSuccess(channel: String, uid: Int, elapsed: Int) {
            isJoined = true
            Log.d("video_call", "Joined Channel $channel")
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            Log.d("video_call", "Remote user offline $uid $reason")
//            runOnUiThread {
            lifecycleScope.launch(Dispatchers.Main) {
                remoteSurfaceView!!.isVisible = false
            }
        }
    }

    private fun setupRemoteVideo(uid: Int) {
        remoteSurfaceView = SurfaceView(baseContext)
        remoteSurfaceView!!.setZOrderMediaOverlay(true)
        binding.remoteVideoViewContainer.addView(remoteSurfaceView)
        agoraEngine!!.setupRemoteVideo(
            VideoCanvas(
                remoteSurfaceView,
                VideoCanvas.RENDER_MODE_ADAPTIVE,
                uid
            )
        )
        // Display RemoteSurfaceView.
        remoteSurfaceView!!.isVisible = true
        binding.icAccount.isVisible = false
    }

    private fun setupLocalVideo() {
        // Create a SurfaceView object and add it as a child to the FrameLayout.
        localSurfaceView = SurfaceView(baseContext)
        binding.localVideoViewContainer.addView(localSurfaceView)
        // Call setupLocalVideo with a VideoCanvas having uid set to 0.
        agoraEngine!!.setupLocalVideo(
            VideoCanvas(
                localSurfaceView,
                VideoCanvas.RENDER_MODE_ADAPTIVE,
                0
            )
        )
    }

    private fun joinChannel() {
        val options = ChannelMediaOptions()

        // For a Video call, set the channel profile as COMMUNICATION.
        options.channelProfile = Constants.CHANNEL_PROFILE_COMMUNICATION
        // Set the client role as BROADCASTER or AUDIENCE according to the scenario.
        options.clientRoleType = Constants.CLIENT_ROLE_BROADCASTER
        // Display LocalSurfaceView.
        setupLocalVideo()
        localSurfaceView!!.isVisible = true
        // Start local preview.
        agoraEngine!!.startPreview()
        // Join the channel with a temp token.
        // You need to specify the user ID yourself, and ensure that it is unique in the channel.
        agoraEngine!!.joinChannel(token, channelName, uid, options)
    }

    private fun leaveChannel() {
        if (!isJoined) {
            Log.d("video_call", "Join a channel first")
        } else {
            agoraEngine!!.leaveChannel()
            Log.d("video_call", "You left the channel")
            // Stop remote video rendering.
            if (remoteSurfaceView != null) remoteSurfaceView!!.visibility = View.GONE
            // Stop local video rendering.
            if (localSurfaceView != null) localSurfaceView!!.visibility = View.GONE
            isJoined = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        agoraEngine!!.stopPreview()
        agoraEngine!!.leaveChannel()

        Thread {
            RtcEngine.destroy()
            agoraEngine = null
        }.start()

        countDownTimer.cancel()
    }

}