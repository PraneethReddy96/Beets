package com.tejeet.beets.ui.BottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tejeet.beets.R
import com.tejeet.beets.databinding.CommentsBottomSheetBinding
import com.tejeet.beets.databinding.FragmentHomeBinding

class CommentsBottomSheet : BottomSheetDialogFragment() {

    val nothing = null
    private var _binding: CommentsBottomSheetBinding? = nothing
    private val binding get() = _binding!!

    companion object {

        const val TAG = "tag"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CommentsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.firstButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "First Button Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.secondButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Second Button Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.thirdButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Third Button Clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}