package com.example.morefit.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.morefit.R
import com.example.morefit.databinding.ActivityProfileBinding
import com.google.android.material.transition.platform.MaterialSharedAxis

class ProfileActivity : AppCompatActivity() {
	private lateinit var binding: ActivityProfileBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		val enter = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
			addTarget(android.R.id.content)
		}
		window.enterTransition = enter
		window.allowEnterTransitionOverlap = true
		super.onCreate(savedInstanceState)
		binding = ActivityProfileBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}