package com.example.zemongatest.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.zemongatest.R
import com.example.zemongatest.databinding.FragmentPostDetailBinding
import com.example.zemongatest.presentation.adapter.CommentsAdapter
import com.example.zemongatest.presentation.di.ViewModelInjector
import com.example.zemongatest.presentation.viewmodel.PostDetailViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
const val ADD_FAVORITE_MENU_POSITION = 1
const val REMOVE_FAVORITE_MENU_POSITION = 0

class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding

    private val args: PostDetailFragmentArgs by navArgs()

    private val viewModelPostDetail: PostDetailViewModel by viewModels {
        ViewModelInjector.providePostDetailsViewModel(requireContext(), args.postId, args.userId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(
            inflater,
            container,
            false
        )

        binding.apply {
            viewModel = viewModelPostDetail
            lifecycleOwner = viewLifecycleOwner

            val adapterComments = CommentsAdapter()
            commentsList.apply {
                adapter = adapterComments
                addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
            }

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.add_to_favorite -> {
                        viewModelPostDetail.maskAsAFavorite()
                        true
                    }
                    R.id.remove_to_favorite -> {
                        viewModelPostDetail.removeFromFavorite()
                        true
                    }
                    else -> false
                }
            }

            viewModelPostDetail.updateStatus()
            viewModelPostDetail.postInfo.observe(viewLifecycleOwner) {
                if (it.isFavorite) {
                    toolbar.menu[REMOVE_FAVORITE_MENU_POSITION].isVisible = true
                    toolbar.menu[ADD_FAVORITE_MENU_POSITION].isVisible = false
                } else {
                    toolbar.menu[ADD_FAVORITE_MENU_POSITION].isVisible = true
                    toolbar.menu[REMOVE_FAVORITE_MENU_POSITION].isVisible = false
                }
            }

            viewModelPostDetail.comments.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    viewModelPostDetail.fetchComments()
                    subscribeFetchCommentsState()
                } else {
                    adapterComments.submitList(it)
                    commentsProgressbar.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

    private fun subscribeFetchCommentsState() {
        viewModelPostDetail.commentsStateRequest.observe(viewLifecycleOwner) {
            binding.commentsProgressbar.visibility = View.GONE
            if (it.isNotEmpty()) {
                displayErrorMessage()
            }
        }
    }

    private fun displayErrorMessage() {
        activity?.let {
            Snackbar.make(
                it.findViewById(android.R.id.content),
                "Network state",
                Snackbar.LENGTH_LONG
            )
                .setAction("Retry") {
                    viewModelPostDetail.fetchComments()
                }.show()
        }
    }

}
