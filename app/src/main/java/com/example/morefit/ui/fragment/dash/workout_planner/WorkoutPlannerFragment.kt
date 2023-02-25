package com.example.morefit.ui.fragment.dash.workout_planner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.morefit.R
import com.example.morefit.adapter.ExerciseAdapter
import com.example.morefit.model.AllData
import com.example.morefit.model.Data
import com.example.morefit.model.PlannerData
import com.example.morefit.ui.activity.MlActivity
import com.example.morefit.ui.activity.RepCounterActivity
import com.example.morefit.ui.fragment.dash.gym.ExerciseFragment
import com.example.morefit.ui.fragment.dash.gym.ExerciseFragmentDirections
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.Datastore.Companion.WEIGHT_LOSE
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier
import kotlin.math.pow

class WorkoutPlannerFragment : Fragment(){

    lateinit var datastore:Datastore
    lateinit var height:String
    lateinit var weight:String
    lateinit var age:String
    lateinit var bmi:String
    lateinit var level:String
    lateinit var weightLose:String
    var bool = 0
    lateinit var text:String
    private val exerciseAdapter = ExerciseAdapter()
    lateinit var data:List<Data>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datastore  = Datastore(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout_planner, container, false)

        val exerciseRecyclerView = view.findViewById<RecyclerView>(R.id.exercise_recyclerView)

        text = requireContext().assets.open("workout_planner.json").bufferedReader().use { it.readText() }
        val allData = Gson().fromJson(text, PlannerData::class.java)

        lifecycleScope.launch {
            height= datastore.getUserDetails("height").toString()
            weight= datastore.getUserDetails("weight").toString()
            age= datastore.getUserDetails("age").toString()
            level=datastore.getUserDetails("level").toString()
            weightLose = datastore.getUserDetails(WEIGHT_LOSE.toString()).toString()

            bmi=(weight.toDouble() / (height.toDouble()).pow(2.0)).toString()

            if (bmi.toDouble()<18.5){
                bool=1
            }
            if (bmi.toDouble()>18.5 && bmi.toDouble()<24.9 ){
                bool=0
            }
            if (bmi.toDouble()>25 && bmi.toDouble()<29.9 ){
                bool=2
            }

        }

        if(bool==0){
            data = when(level){
                "beginner" -> {
                    allData.get(0).maitain.get(0).beginner
                }
                "intermediate" -> {
                    allData.get(0).maitain.get(0).intermediate
                }
                "advanced" -> {
                    allData.get(0).maitain.get(0).advanced
                }
                else -> {allData.get(0).maitain.get(0).intermediate}
            }
        }
        else if(bool==2){
            data = when(level){
                "beginner" -> if(weightLose.toDouble()>1){
                    allData.get(1).lose.get(0).fast.get(0).beginner
                }
                else{
                    allData.get(1).lose.get(0).slow.get(0).beginner
                }
                "intermediate" -> if(weightLose.toDouble()>1){
                    allData.get(1).lose.get(0).fast.get(0).intermediate
                }
                else{
                    allData.get(1).lose.get(0).slow.get(0).intermediate
                }
                "advanced" -> if(weightLose.toDouble()>1){
                    allData.get(1).lose.get(0).fast.get(0).advanced
                }
                else{
                    allData.get(1).lose.get(0).slow.get(0).advanced
                }
                else -> {allData.get(1).lose.get(0).fast.get(0).beginner}
            }
        }
        else{
            data = allData.get(0).maitain.get(0).beginner
        }

        exerciseRecyclerView.adapter = exerciseAdapter
        exerciseAdapter.updateAddressList(data)

        exerciseAdapter.setOnItemClickListener(object : ExerciseAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val url = exerciseAdapter.addressList[position].video_tutorials[0]
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

            override fun onActivityCLick(position: Int) {
                //File Name here
                ExerciseFragment.name = data[position].title
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
                Log.d("PLANNER", "onExerciseClickListener: $tutorial")
                val action = WorkoutPlannerFragmentDirections.actionWorkoutPlannerFragmentToExerciseDetailFragment(tutorial)
                findNavController().navigate(action)
            }
        })

        return view
    }


}
