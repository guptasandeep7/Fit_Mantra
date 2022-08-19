package com.example.morefit.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.morefit.databinding.ActivityProfileBinding
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class ProfileActivity : AppCompatActivity() {
	private lateinit var binding: ActivityProfileBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		findViewById<View>(android.R.id.content).transitionName = "shared_element"
		setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
		val materialContainerTransform = MaterialContainerTransform().apply {
			addTarget(android.R.id.content)
			scrimColor = Color.TRANSPARENT
			pathMotion = MaterialArcMotion()
		}
		window.sharedElementEnterTransition = materialContainerTransform
		window.sharedElementReturnTransition = materialContainerTransform
		super.onCreate(savedInstanceState)
		binding = ActivityProfileBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}