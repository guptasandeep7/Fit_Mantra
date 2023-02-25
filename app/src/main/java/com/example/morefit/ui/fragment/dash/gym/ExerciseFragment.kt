package com.example.morefit.ui.fragment.dash.gym

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.adapter.ExerciseAdapter
import com.example.morefit.databinding.FragmentExerciseBinding
import com.example.morefit.model.AllData
import com.example.morefit.model.Data
import com.example.morefit.ui.activity.MlActivity
import com.example.morefit.ui.activity.RepCounterActivity
import com.example.morefit.ui.fragment.dash.gym.GymFragment.Companion.muscleName
import com.example.morefit.utils.hideBottomNavigationView
import com.example.morefit.utils.showBottomNavigationView
import com.google.android.material.transition.MaterialSharedAxis
import com.google.gson.Gson
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier

class ExerciseFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    private val exerciseAdapter = ExerciseAdapter()
    private lateinit var data: List<Data>

    companion object {
        var name = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icBack.setOnClickListener(this)
        binding.stretches.setOnClickListener(this)
        binding.barbell.setOnClickListener(this)
        binding.bodyweight.setOnClickListener(this)
        binding.dumbbells.setOnClickListener(this)
        binding.kettlebells.setOnClickListener(this)

        binding.heading2.text = muscleName
        binding.headingSmall.text = "Gym $muscleName"

        loadData()
        exerciseAdapter.setOnItemClickListener(object : ExerciseAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val url = exerciseAdapter.addressList[position].video_tutorials[0]
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

            override fun onActivityCLick(position: Int) {
                //File Name here
                name = data[position].title
                PoseClassifier.MODEL_FILENAME = data[position].file_name.toString()
                PoseClassifier.labels = data[position].labels!!
                if (data[position].counter) {
                    RepCounterActivity.correct_label = data[position].correct_label.toString()
                    val intent = Intent(activity, RepCounterActivity::class.java)
                    startActivity(intent)
                } else {
                    MlActivity.correct_label = data[position].correct_label.toString()
                    MlActivity.name=data[position].title
                    val intent = Intent(activity, MlActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onExerciseClickListener(tutorial: Data) {
                val action = ExerciseFragmentDirections.actionExerciseFragment2ToExerciseDetailFragment(tutorial)
                findNavController().navigate(action)
            }
        })
//        exerciseAdapter.setOnItemClickListener(object : ExerciseAdapter.onItemClickListener {
//            override fun onItemClick(position: Int) {
//                val url = exerciseAdapter.addressList[position].video_tutorials[0]
//                val intent = Intent(activity,PoseDetectionActivity::class.java)
//                intent.data = Uri.parse(url)
//                startActivity(intent)
//            }
//        })
    }

    private fun filterData(category: String) {
        val newData = data.filter {
            it.category == category
        }
        exerciseAdapter.updateAddressList(newData)
    }

    private fun loadData() {
        val text = requireContext().assets.open("data.json").bufferedReader().use { it.readText() }
        val allData = Gson().fromJson(text, AllData::class.java)
        data = allData.data.filter {
            it.muscle == muscleName
        }
        binding.progressBar.visibility = View.GONE
        binding.productsRecyclerView.adapter = exerciseAdapter
        filterData("Stretches")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icBack -> findNavController().navigateUp()
            R.id.stretches -> filterData("Stretches")
            R.id.bodyweight -> filterData("Bodyweight")
            R.id.barbell -> filterData("Barbell")
            R.id.dumbbells -> filterData("Dumbbells")
            R.id.kettlebells -> filterData("Kettlebells")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        activity?.hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        activity?.showBottomNavigationView()
    }
}