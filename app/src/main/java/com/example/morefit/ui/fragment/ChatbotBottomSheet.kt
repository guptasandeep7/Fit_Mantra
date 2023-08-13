package com.example.morefit.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.morefit.R
import com.example.morefit.adapter.ChatRecyclerAdapter
import com.example.morefit.databinding.BottomSheetChatbotBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChatbotBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_chatbot) {

    private var _binding: BottomSheetChatbotBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BottomSheetChatbotBinding.bind(view)
        val adapter = ChatRecyclerAdapter(requireContext())
        binding.chatRecyclerView.adapter = adapter
        binding.floatingActionButton.setOnClickListener {
            adapter.addMsg(binding.textInputEditText.text.toString())
            binding.textInputEditText.text?.clear()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}