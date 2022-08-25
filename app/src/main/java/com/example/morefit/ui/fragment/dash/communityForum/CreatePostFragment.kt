package com.example.morefit.ui.fragment.dash.communityForum

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentCreatePostBinding
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.sealedClass.Response
import com.example.morefit.utils.FileUtils
import com.example.morefit.utils.hideBottomNavigationView
import com.example.morefit.utils.showBottomNavigationView
import com.example.morefit.view_models.CommunityForumViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class CreatePostFragment : Fragment(R.layout.fragment_create_post) {
    private lateinit var binding: FragmentCreatePostBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CommunityForumViewModel::class.java]
    }
    private var imgUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePostBinding.bind(view).apply {
            val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
                it?.let { uri ->
                    imgUri = uri
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

                progressBar.visibility = View.VISIBLE

                sendDp("1", title.text.toString(), content.text.toString(), imgUri!!)

                viewModel.createPostLiveData.observe(viewLifecycleOwner) {
                    if (it is Response.Success) {
                        Toast.makeText(context, "Your post has been created!", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigateUp()
                    } else {
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

    private fun sendDp(account: String, title : String, content: String, uri: Uri) {
        val file = FileUtils.getFile(context, uri)
        Log.e("FILE_URI", file.path)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val imageFile = MultipartBody.Part.createFormData("image", file.name, requestFile)
        val accountMultiPart = account.toRequestBody(MultipartBody.FORM)
        val titleMultiPart = title.toRequestBody(MultipartBody.FORM)
        val contentMultiPart = content.toRequestBody(MultipartBody.FORM)

        viewModel.createPost(accountMultiPart, titleMultiPart, contentMultiPart, imageFile)
    }
}