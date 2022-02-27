package com.example.morefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morefit.adapter.ExerciseAdapter
import com.example.morefit.databinding.FragmentExerciseBinding
import com.example.morefit.home.HomeFragment.Companion.muscleName
import com.example.morefit.model.AllData
import com.example.morefit.model.Data
import com.google.gson.Gson

class ExerciseFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    private val exerciseAdapter = ExerciseAdapter()
    private lateinit var data: List<Data>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener(this)
        binding.stretches.setOnClickListener(this)
        binding.barbell.setOnClickListener(this)
        binding.bodyweight.setOnClickListener(this)
        binding.dumbbells.setOnClickListener(this)
        binding.kettlebells.setOnClickListener(this)

        binding.toolbarText.text = muscleName

        loadData()

        exerciseAdapter.setOnItemClickListener(object : ExerciseAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })
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
            R.id.back_btn -> findNavController().navigateUp()
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

}