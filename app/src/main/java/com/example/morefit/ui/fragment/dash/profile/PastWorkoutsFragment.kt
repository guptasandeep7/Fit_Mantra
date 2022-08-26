package com.example.morefit.ui.fragment.dash.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.adapter.PastWorkoutsInterface
import com.example.morefit.adapter.PastWorkoutsRecyclerAdapter
import com.example.morefit.database.ContentApplication
import com.example.morefit.databinding.FragmentPastWorkoutsBinding
import com.example.morefit.view_models.ContentViewModel
import com.example.morefit.view_models.WordViewModelFactory
import com.google.android.material.transition.MaterialSharedAxis

class PastWorkoutsFragment : Fragment(R.layout.fragment_past_workouts), PastWorkoutsInterface {
    private lateinit var binding: FragmentPastWorkoutsBinding
    private val contentViewModel: ContentViewModel by viewModels {
        WordViewModelFactory((activity?.application as ContentApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPastWorkoutsBinding.bind(view).apply {
            contentViewModel.allWords.observe(viewLifecycleOwner) {
//            val data = listOf(
//                Content(1220227200L, "Push-Ups", 120L, 12),
//                Content(1220227211L, "Pull-Ups", 20L, 1256565656),
//                Content(1220627200L, "Tree Pose", 1200000L, 1),
//                Content(1220220200L, "Chin-Ups", 12000L, 33),
//                Content(
//                    1210227200L,
//                    "sffufgdfadfgesucfnseguiescgraugauagdudgaufgfaufgfuagfaufga",
//                    120L,
//                    12
//                ),
//                Content(66220227200L, "a", 10L, 122),
//                Content(1226666666601027200L, "Vraksasana", 1201L, 92),
//            )
                Log.e("ROOM_DATA_SIZE", it.size.toString())
                workoutsRecyclerView.adapter =
                    PastWorkoutsRecyclerAdapter(this@PastWorkoutsFragment, it)
            }

            topBar.setOnClickListener { findNavController().navigateUp() }
        }
    }
}