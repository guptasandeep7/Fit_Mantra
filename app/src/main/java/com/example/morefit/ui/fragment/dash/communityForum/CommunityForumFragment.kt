package com.example.morefit.ui.fragment.dash.communityForum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.adapter.CommunityForumInterface
import com.example.morefit.adapter.CommunityForumRecyclerAdapter
import com.example.morefit.databinding.FragmentCommunityForumBinding
import com.example.morefit.sealedClass.Response
import com.example.morefit.view_models.CommunityForumViewModel
import com.example.morefit.view_models.GenerateMealPlanViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class CommunityForumFragment : Fragment(R.layout.fragment_community_forum),
    CommunityForumInterface {
    private lateinit var binding: FragmentCommunityForumBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CommunityForumViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommunityForumBinding.bind(view).apply {
            progressBar.visibility = View.VISIBLE
            viewModel.getAllPosts()
            viewModel.getPostsLiveData.observe(viewLifecycleOwner) {
                progressBar.visibility = View.INVISIBLE
                if (it is Response.Success) {
                    forumRecyclerView.adapter =
                        CommunityForumRecyclerAdapter(this@CommunityForumFragment, it.data!!)
                }
                else Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
            }

            addPostBtn.setOnClickListener {
                findNavController().navigate(R.id.action_communityForumFragment_to_createPostFragment)
            }
        }
    }

    override fun likeBtnClickListener() {

    }
}