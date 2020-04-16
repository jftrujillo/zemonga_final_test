package com.example.zemongatest.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.zemongatest.R
import com.example.zemongatest.databinding.FavoritePostCellBinding
import com.example.zemongatest.databinding.FragmentFavoritePostBinding
import com.example.zemongatest.presentation.adapter.FavoritePostAdapter
import com.example.zemongatest.presentation.di.ViewModelInjector
import com.example.zemongatest.presentation.viewmodel.FavoritePostViewModel

class FavoritePostsFragment : Fragment() {

    lateinit var binding: FragmentFavoritePostBinding

    private val viewModel: FavoritePostViewModel by viewModels {
        ViewModelInjector.provideFavoritePostViewModel(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritePostBinding.inflate(inflater, container, false)
        val favoritePostAdapter = FavoritePostAdapter()
        binding.favoritePost.apply {
            adapter = favoritePostAdapter
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
        }
        viewModel.allFavoritePostLiveData.observe(viewLifecycleOwner) {
            favoritePostAdapter.submitList(it)
        }

        return binding.root
    }

}