package com.example.morefit.ui.fragment.dash.communityForum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.DialogCreatePostBinding
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.sealedClass.Response
import com.example.morefit.utils.hideBottomNavigationView
import com.example.morefit.utils.showBottomNavigationView
import com.example.morefit.view_models.CommunityForumViewModel

class CreatePostFragment : Fragment(R.layout.dialog_create_post) {
    private lateinit var binding: DialogCreatePostBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CommunityForumViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogCreatePostBinding.bind(view).apply {
            val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
                it?.let { uri ->
                    postImg.setImageURI(uri)
                }
            }

            selectImgBtn.setOnClickListener {
                launcher.launch("image/*")
            }

            createPostBtn.setOnClickListener {
                if (title.text.isNullOrBlank()) {
                    textInputLayout.helperText = "Please enter title"
                    return@setOnClickListener
                }

                if (content.text.isNullOrBlank()) {
                    textInputLayout2.helperText = "Please enter content"
                    return@setOnClickListener
                }

                progressBar.visibility = View.VISIBLE
                viewModel.createPost(
                    CreateForum(
                        account = 1,
                        content = content.text.toString(),
                        title = title.text.toString()
                    )
                )

                viewModel.createPostLiveData.observe(viewLifecycleOwner) {
                    if (it is Response.Success) {
                        Toast.makeText(context, "Your post has been created!", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }

                    else {
                        Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                        progressBar.hide()
                    }
                }
            }



            icBack.setOnClickListener { findNavController().navigateUp() }
        }
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