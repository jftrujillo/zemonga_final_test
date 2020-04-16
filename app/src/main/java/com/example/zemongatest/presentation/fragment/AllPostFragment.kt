package com.example.zemongatest.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.zemongatest.R
import com.example.zemongatest.data.worker.SyncDataWorker
import com.example.zemongatest.databinding.FragmentAllPostBinding
import com.example.zemongatest.presentation.adapter.AllPostAdapter
import com.example.zemongatest.presentation.callback.SwipeToDelete
import com.example.zemongatest.presentation.di.ViewModelInjector
import com.example.zemongatest.presentation.viewmodel.AllPostViewModel

class AllPostFragment : Fragment(){
    private lateinit var binding: FragmentAllPostBinding

    private val viewModel: AllPostViewModel by viewModels {
        ViewModelInjector.provideAllPostViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllPostBinding.inflate(inflater, container, false)
        val allPostAdapter = AllPostAdapter()
        binding.postList.apply {
            adapter = allPostAdapter
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
        }

        binding.deleteAllPostFb.setOnClickListener {
            viewModel.deleteAll()
        }

        viewModel.allPostLiveData.observe(viewLifecycleOwner) {
            allPostAdapter.submitList(it)
            renderLoadDataState()
        }
        initSwipeListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_post, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                refreshData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshData() {
        this.context?.let {
            if (binding.postList.isEmpty()) {
                renderLoadingState()
                val request = OneTimeWorkRequestBuilder<SyncDataWorker>().build()
                WorkManager.getInstance(it).enqueue(request)
            }
        }
    }

    private fun initSwipeListener() {
        val swipeHandler = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.allPostLiveData.value?.get(viewHolder.adapterPosition)?.postId?.let {
                    viewModel.deletePost(it)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.postList)
    }


    private fun renderLoadDataState() {
        binding.progressBar.visibility = View.GONE
        binding.deleteAllPostFb.visibility = View.VISIBLE
    }

    private fun renderLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.deleteAllPostFb.visibility = View.GONE
    }


}