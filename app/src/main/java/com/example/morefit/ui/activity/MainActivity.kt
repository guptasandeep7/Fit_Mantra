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
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.morefit.R
import com.example.morefit.databinding.ActivityMainBinding
import com.example.morefit.model.YogaPoses
import com.example.morefit.ui.fragment.dash.yoga.YogaFragment
import com.example.morefit.ui.fragment.dash.yoga.YogaFragmentDirections
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.gson.Gson
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("meralog", "onCreate: $viewModel.backPressed.value")
        viewModel.backPressed.observe(this) {
            if (it == true) {
                finish()
            }
        }
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

    }

}