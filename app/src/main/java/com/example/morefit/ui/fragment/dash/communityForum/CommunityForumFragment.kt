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
import com.example.morefit.model.communityForum.Comment
import com.example.morefit.sealedClass.Response
import com.example.morefit.view_models.CommunityForumViewModel
import com.example.morefit.view_models.GenerateMealPlanViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialFadeThrough

class CommunityForumFragment : Fragment(R.layout.fragment_community_forum),
    CommunityForumInterface {
    private lateinit var binding: FragmentCommunityForumBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CommunityForumViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommunityForumBinding.bind(view).apply {
            progressBar.visibility = View.VISIBLE
            viewModel.getAllPosts()
            val comments = listOf(
                Comment("Sandeep Gupta", "Need to work hard more!"),
                Comment("Aakash Verma", "Nice!"),
                Comment("Deekha Sharma", "Greetings to all :)")
            )
            viewModel.getPostsLiveData.observe(viewLifecycleOwner) {
                progressBar.visibility = View.INVISIBLE
                if (it is Response.Success) {
                    forumRecyclerView.adapter =
                        CommunityForumRecyclerAdapter(this@CommunityForumFragment, it.data!!, comments)
                }
                else Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
            }

            addPostBtn.setOnClickListener {
//                findNavController().navigate(R.id.action_communityForumFragment_to_createPostFragment)
            }

            icBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun likeBtnClickListener(id: Int, account: Int) {
        viewModel.likePost(id, account)
//        viewModel.likeLiveData.observe(viewLifecycleOwner) {
//            if (it is Response.Error) {
//                Toast.makeText()
//            }
//        }
    }
}