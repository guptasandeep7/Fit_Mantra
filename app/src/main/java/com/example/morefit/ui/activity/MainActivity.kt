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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.morefit.R
import com.example.morefit.databinding.ActivityMainBinding
import com.example.morefit.model.YogaPoses
import com.example.morefit.ui.fragment.ChatbotBottomSheet
import com.example.morefit.ui.fragment.dash.yoga.YogaFragmentDirections
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var speechRecognizerIntent: Intent

    private var isFabActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        checkAudioPermission()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

//        if(navController.currentDestination?.id == R.id.dietPlan){
//            binding.bottomNavigationView.selectedItemId = R.id.dietFragment
//        }

//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            if (destination.id == R.id.dietPlan) {
//                binding.bottomNavigationView.menu.clear()
//                binding.bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu2)
//            }
//        }

        binding.fab.setOnClickListener {
            isFabActive = !isFabActive
            onFabClick(isFabActive)
        }

        binding.videoChat.setOnClickListener {
            startActivity(Intent(this, VideoCallActivity::class.java))
            lifecycleScope.launch {
                delay(100)
                isFabActive = false
                onFabClick(false)
            }
        }

        binding.bot.setOnClickListener {
            isFabActive = false
            onFabClick(false)
            val modalBottomSheet = ChatbotBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ChatbotBottomSheet.TAG)
        }
    }

    private fun onFabClick(fabActive: Boolean) {
        if (fabActive) {
            binding.viewLayer.isVisible = true
            binding.bot.show()
            binding.videoChat.show()
            binding.videoChatTv.isVisible = true
            binding.botTv.isVisible = true
            binding.icTrainer.isVisible = true
        } else {
            binding.viewLayer.isVisible = false
            binding.bot.hide()
            binding.videoChat.hide()
            binding.videoChatTv.isVisible = false
            binding.botTv.isVisible = false
            binding.icTrainer.isVisible = false
        }
    }

    override fun onResume() {
        super.onResume()
//        startSpeechToText()
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

    fun speechResult(result: String) {
        try {
            Toast.makeText(this, result + "Main Activity", Toast.LENGTH_SHORT).show()
            val file = this.assets.open("yoga_data.json").bufferedReader()
                .use { it.readText() }
            val data = Gson().fromJson(file, YogaPoses::class.java).poses

            when {
                result.contains("open") ->
                    when {
                        result.contains("chair") || result.contains("utkatasana") -> {
                            val yoga = data.find {
                                it.english_name == "Chair"
                            }
                            if (yoga != null) {
                                YogaFragmentDirections.actionYogaFragmentToYogaPoseDetailsFragment(
                                    yoga
                                )
                                    .also {
                                        navController.navigate(it)
                                    }
                            }
                        }

                        result.contains("tree") || result.contains("vrksasana") -> {
                            val yoga = data.find {
                                it.english_name == "Tree"
                            }
                            if (yoga != null) {
                                YogaFragmentDirections.actionYogaFragmentToYogaPoseDetailsFragment(
                                    yoga
                                )
                                    .also {
                                        navController.navigate(it)
                                    }
                            }
                        }

                        result.contains("cobra") || result.contains("bhujangasana") -> {
                            val yoga = data.find {
                                it.english_name == "Cobra"
                            }
                            if (yoga != null) {
                                YogaFragmentDirections.actionYogaFragmentToYogaPoseDetailsFragment(
                                    yoga
                                )
                                    .also {
                                        navController.navigate(it)
                                    }
                            }
                        }

                        result.contains("warrior") || result.contains("virabhadrasana") -> {
                            val yoga = data.find {
                                it.english_name == "Warrior"
                            }
                            if (yoga != null) {
                                YogaFragmentDirections.actionYogaFragmentToYogaPoseDetailsFragment(
                                    yoga
                                )
                                    .also {
                                        navController.navigate(it)
                                    }
                            }
                        }

                        result.contains("yoga") -> {
                            navController.navigate(R.id.action_gymFragment_to_yogaFragment)
                        }
                    }

                result.contains("back") -> navController.navigateUp()
                result.contains("perform") || result.contains("do") -> {
                    when {
                        result.contains("chair") || result.contains("utkatasana") -> {
                            val yogaPose = data.find {
                                it.english_name == "Chair"
                            }
                            if (yogaPose != null) {
                                PoseClassifier.MODEL_FILENAME = yogaPose.file_name
                                MlActivity.correct_label = yogaPose.correct_label
                                PoseClassifier.labels = yogaPose.labels
                                val intent = Intent(this, MlActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        result.contains("tree") || result.contains("vrksasana") -> {
                            val yogaPose = data.find {
                                it.english_name == "tree"
                            }
                            if (yogaPose != null) {
                                PoseClassifier.MODEL_FILENAME = yogaPose.file_name
                                MlActivity.correct_label = yogaPose.correct_label
                                PoseClassifier.labels = yogaPose.labels
                                val intent = Intent(this, MlActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        result.contains("cobra") || result.contains("bhujangasana") -> {
                            val yogaPose = data.find {
                                it.english_name == "cobra"
                            }
                            if (yogaPose != null) {
                                PoseClassifier.MODEL_FILENAME = yogaPose.file_name
                                MlActivity.correct_label = yogaPose.correct_label
                                PoseClassifier.labels = yogaPose.labels
                                val intent = Intent(this, MlActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }

                result.contains("start") -> {
                    //start exercise
                }

                result.contains("exit") || result.contains("complete") || result.contains("completed") || result.contains(
                    "stop"
                ) -> {
                    startActivity(Intent(this, AuthActivity::class.java))
                }

                result.contains("pause") -> {
                    //pause exercise
                }

                result.contains("start") -> {
                    //start exercise
                    RepCounterActivity().startFunc()
                }

                result.contains("exit") || result.contains("complete rep") || result.contains("rep completed") -> {
                    RepCounterActivity().complete()
                }

                result.contains("pause") -> {
                    //pause exercise
                    RepCounterActivity().startFunc()
                }
            }
        } catch (e: Exception) {

        }
    }


    fun checkAudioPermission() {
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

    override fun onPause() {
        super.onPause()
//        speechRecognizer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
//        speechRecognizer.destroy()
    }
}