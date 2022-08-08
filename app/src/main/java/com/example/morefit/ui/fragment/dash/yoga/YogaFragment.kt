package com.example.morefit.ui.fragment.dash.yoga

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.morefit.R
import com.example.morefit.adapter.YogaPoseInterface
import com.example.morefit.adapter.YogaPoseRecyclerAdapter
import com.example.morefit.databinding.FragmentYogaBinding
import com.example.morefit.databinding.ItemYogaPoseDetailsBinding
import com.example.morefit.model.Pose
import com.example.morefit.model.YogaPoses
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.gson.Gson

class YogaFragment : Fragment(R.layout.fragment_yoga), YogaPoseInterface {
    private lateinit var binding: FragmentYogaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialFadeThrough()
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentYogaBinding.bind(view).apply {
            val file = requireContext().assets.open("yoga_data.json").bufferedReader()
                .use { it.readText() }
            val data = Gson().fromJson(file, YogaPoses::class.java)
            yogaRecyclerView.adapter = YogaPoseRecyclerAdapter(this@YogaFragment, data.poses)

            icBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun onPoseClickListener(yogaPoseDetail: Pose) {
        ModalBottomSheet(yogaPoseDetail).show(parentFragmentManager, ModalBottomSheet.TAG)
    }
}

class ModalBottomSheet(private val yogaPoseDetail: Pose): BottomSheetDialogFragment() {
    companion object {
        const val TAG = "Modal_Sheet_Dialog"
    }
    private lateinit var bottomSheetBinding : ItemYogaPoseDetailsBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.item_yoga_pose_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBinding = ItemYogaPoseDetailsBinding.bind(view).apply {
            icClose.setOnClickListener { dismiss() }
            englishName.text = yogaPoseDetail.english_name + " Pose"
            sanskritName.text = yogaPoseDetail.sanskrit_name
            yogaDescription.text = yogaPoseDetail.yoga_description
            poseImg.load(yogaPoseDetail.image_url) {
                crossfade(true)
                placeholder(R.drawable.yoga)
            }

            var txt = ""
            for (i in yogaPoseDetail.yoga_instruction.indices) {
                txt += "${i+1}. ${yogaPoseDetail.yoga_instruction[i]}\n\n"
            }
            yogaInstruction.text = txt
        }
    }
}