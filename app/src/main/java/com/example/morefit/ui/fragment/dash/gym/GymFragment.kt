package com.example.morefit.ui.fragment.dash.gym

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.adapter.PageAdapter
import com.example.morefit.databinding.FragmentGymBinding
import com.example.morefit.ui.activity.ProfileActivity
import com.example.morefit.utils.Datastore
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialFadeThrough
//import com.google.android.material.transition.platform.MaterialFadeThrough
import kotlinx.coroutines.launch

class GymFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentGymBinding? = null
    private val binding get() = _binding!!
    lateinit var datastore: Datastore

    companion object {
        lateinit var muscleName: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        exitTransition = Hold()
        datastore = Datastore(requireContext())
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGymBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            binding.username.text = datastore.getUserDetails(Datastore.NAME_KEY).toString()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pageAdapter = PageAdapter(this)

        binding.viewpagerProduct.adapter = pageAdapter

        TabLayoutMediator(binding.tabProduct, binding.viewpagerProduct) { tab, position ->
            when (position) {
                0 -> tab.text = "Front"
                1 -> tab.text = "Back"
            }
        }.attach()
        super.onViewCreated(view, savedInstanceState)

        binding.userImage.setOnClickListener(this)
//        binding.calBtn.setOnClickListener(this)
//        binding.settingBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
//            R.id.cal_btn -> bottomCal()
//            R.id.setting_btn -> bottomSetting()
            R.id.user_image -> {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    requireActivity(), binding.userImage, "shared_element"
                )
                startActivity(
                    Intent(requireContext(), ProfileActivity::class.java),
                    options.toBundle()
                )
            }
        }
    }

    private fun bottomSetting() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.setting_option, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()

        val exitBtn = view.findViewById<MaterialButton>(R.id.exit_btn)
        val aboutBtn = view.findViewById<MaterialButton>(R.id.about_btn)
        val helpBtn = view.findViewById<MaterialButton>(R.id.help_btn)

        helpBtn.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_homefragment_to_helpFragment)
        }

        aboutBtn.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_homefragment_to_aboutFragment)
        }

        exitBtn.setOnClickListener {
            dialog.dismiss()
            activity?.finish()
        }

    }

    private fun bottomCal() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.cal_option, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()

        val calorieBtn = view.findViewById<MaterialButton>(R.id.calorie_meter_btn)
        val macroBtn = view.findViewById<MaterialButton>(R.id.macro_cal_btn)
        val bmiCal = view.findViewById<MaterialButton>(R.id.bmi_cal_btn)

        calorieBtn.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_homefragment_to_calorieFragment)
        }
        macroBtn.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_homefragment_to_macroFragment)
        }
        bmiCal.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_homefragment_to_bmiFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}