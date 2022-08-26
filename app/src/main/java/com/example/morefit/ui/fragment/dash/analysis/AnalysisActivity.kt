package com.example.morefit.ui.fragment.dash.analysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.morefit.R
import com.example.morefit.database.ContentApplication
import com.example.morefit.databinding.ActivityAnalysisBinding
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.ui.activity.MlActivity
import com.example.morefit.ui.activity.RepCounterActivity
import com.example.morefit.ui.fragment.dash.gym.ExerciseFragment
import com.example.morefit.utils.Datastore
import com.example.morefit.view_models.ContentViewModel
import com.example.morefit.view_models.WordViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class AnalysisActivity : AppCompatActivity() {
    lateinit var binding:ActivityAnalysisBinding
    private val contentViewModel: ContentViewModel by viewModels {
        WordViewModelFactory((this as ContentApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityAnalysisBinding.inflate(layoutInflater)
       binding.totalrepcount.text=RepCounterActivity.repcounter.toString()
        binding.timer.text=timer(RepCounterActivity.timer)
        binding.heading2.text=ExerciseFragment.name.toString()
        binding.correctPosture.text=(0.32*RepCounterActivity.repcounter).toString()
        setContentView(binding.root)
        binding.incorrectPosture2.text=RepCounterActivity.repcounter.toString()
        binding.icBack.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun timer(seconds:Long) :String{
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        val secs = seconds % 60

        // Format the seconds into hours, minutes,
        // and seconds.
        val time: String = java.lang.String
            .format(
                Locale.getDefault(),
                "%d:%02d:%02d", hours,
                minutes, secs
            )
        return time
    }
}