package com.example.morefit.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.morefit.R
import com.example.morefit.model.YogaPoses
import com.example.morefit.ui.fragment.dash.yoga.YogaFragmentDirections
import com.example.morefit.view_models.BaseViewModel
import com.google.gson.Gson
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier
import java.util.*

open class BaseActivity : AppCompatActivity() {

    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var speechRecognizerIntent: Intent
    lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAudioPermission()
        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]
    }

    private fun checkAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // M = 23
            if (ContextCompat.checkSelfPermission(
                    this,
                    "android.permission.RECORD_AUDIO"
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // this will open settings which asks for permission
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:com.example.morefit")
                )
                startActivity(intent)
                Toast.makeText(this, "Allow Microphone Permission", Toast.LENGTH_SHORT).show()
            } else {
                startSpeechToText()
            }
        }
    }

    private fun startSpeechToText() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray?) {}
            override fun onEndOfSpeech() {
                speechRecognizer.startListening(speechRecognizerIntent)
            }

            override fun onError(i: Int) {
                speechRecognizer.startListening(speechRecognizerIntent)
            }

            override fun onResults(bundle: Bundle) {
                val result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (result != null) {

                    speechResult(result[0].lowercase())
                }
                speechRecognizer.startListening(speechRecognizerIntent)

            }

            override fun onPartialResults(bundle: Bundle) {
                speechRecognizer.startListening(speechRecognizerIntent)
            }

            override fun onEvent(i: Int, bundle: Bundle?) {}
        })
        speechRecognizer.startListening(speechRecognizerIntent)
    }


    private fun speechResult(result: String) {
        try {
            Toast.makeText(this, "base $result", Toast.LENGTH_SHORT).show()
            val file = this.assets.open("yoga_data.json").bufferedReader()
                .use { it.readText() }
            val data = Gson().fromJson(file, YogaPoses::class.java).poses

            when {
                result.contains("bag") ->{

                    Log.d("meralog", "onCreate: $result")
                    viewModel.setBackPressed(true)
                }
            }
        } catch (e: Exception) {
            Log.d("meralog", "Error: ${e.localizedMessage}")

        }
    }


    override fun onPause() {
        super.onPause()
        speechRecognizer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}
