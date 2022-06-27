package com.example.morefit.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.morefit.R
import com.example.morefit.databinding.ActivityProfileBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialSharedAxis

class ProfileActivity : AppCompatActivity() {
	private lateinit var binding: ActivityProfileBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		findViewById<View>(android.R.id.content).transitionName = "shared_element"
		setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
		window.sharedElementEnterTransition = MaterialContainerTransform().apply {
			addTarget(android.R.id.content)
			duration = 300L
		}
		window.sharedElementReturnTransition = MaterialContainerTransform().apply {
			addTarget(android.R.id.content)
			duration = 250L
		}
		super.onCreate(savedInstanceState)
		binding = ActivityProfileBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}