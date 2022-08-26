package com.example.morefit.ui.fragment.dash.communityForum

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentCreatePostBinding
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.sealedClass.Response
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.hideBottomNavigationView
import com.example.morefit.utils.showBottomNavigationView
import com.example.morefit.view_models.CommunityForumViewModel
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.launch


class CreatePostFragment : Fragment(R.layout.fragment_create_post) {
    private lateinit var binding: FragmentCreatePostBinding
    lateinit var datastore: Datastore
    private val viewModel by lazy {
        ViewModelProvider(this)[CommunityForumViewModel::class.java]
    }
    private var imgUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datastore = Datastore(requireContext())
        binding = FragmentCreatePostBinding.bind(view).apply {
            val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
                it?.let { uri ->
                    imgUri = uri
                    postImg.visibility = View.VISIBLE
                    postImg.setImageURI(uri)
                    binding.selectImgBtn.text = "Selected √"
                    binding.selectImgBtn.elevation = 0f
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

                Toast.makeText(context, "Post created successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
//                progressBar.visibility = View.VISIBLE
//                imgUri?.let { uri ->
//                    lifecycleScope.launch {
//                        viewModel.uploadPic(uri, datastore.getUserDetails(Datastore.ID)!!.toInt())
//                    }
//
//                    viewModel.uploadImageMutableLiveData.observe(viewLifecycleOwner) {
//                        if (it.substring(0, 8) == "Uploaded") {
//                            viewModel.createPost(
//                                CreateForum(
//                                    1, content.text.toString(), title.text.toString(),
//                                    it.substring(9)
//                                )
//                            )
//                        }
//                        else Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//                    }
//                }

//                viewModel.createPostLiveData.observe(viewLifecycleOwner) {
//                    if (it is Response.Success) {
//                        Toast.makeText(context, "Your post has been created!", Toast.LENGTH_SHORT)
//                            .show()
//                        findNavController().navigateUp()
//                    } else {
//                        Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
//                        progressBar.hide()
//                    }
//                }
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

//    private fun sendDp(account: String, title : String, content: String, uri: Uri) {
//        val file: File = File(uri.path)
//        Log.e("FILE_URI", file.path)
//        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val imageFile = MultipartBody.Part.createFormData("image", file.name, requestFile)
//        val accountMultiPart = account.toRequestBody(MultipartBody.FORM)
//        val titleMultiPart = title.toRequestBody(MultipartBody.FORM)
//        val contentMultiPart = content.toRequestBody(MultipartBody.FORM)
//
//        viewModel.createPost(accountMultiPart, titleMultiPart, contentMultiPart, imageFile)
//    }
}