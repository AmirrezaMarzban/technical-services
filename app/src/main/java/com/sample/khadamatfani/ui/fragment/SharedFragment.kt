package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentSharedBinding
import com.sample.khadamatfani.model.Post
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.progressBar
import kotlinx.android.synthetic.main.fragment_shared.*
import kotlinx.android.synthetic.main.items.*
import kotlinx.android.synthetic.main.layout_recyclerview.*

class SharedFragment : BaseFragment<FragmentSharedBinding>() {
    private lateinit var mAdapter: PostAdapter

    override fun getLayout() = R.layout.fragment_shared

    override fun main(savedInstanceState: Bundle?) {
        binding.title = requireArguments().getString("title")
        if (requireArguments().getString("title") == getString(R.string.my_posts)) {
            initMyPost()
        } else {
            initRecentPosts()
        }
    }

    private fun initMyPost() {
        mAdapter = PostAdapter(requireContext(), this).apply {
            removeButton = true
            editButton = true
            isVerified = true
            clickListener(object: PostAdapter.ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Post>) {
                    alertDialog = MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialogTheme)
                        .setTitle(getString(R.string.warning))
                        .setMessage(getString(R.string.sure_delete))
                        .setPositiveButton(getString(R.string.ok)) { p0, p1 ->
                            mainViewModel.deletePost(item[position].id).observe(viewLifecycleOwner, { resources ->
                                when(resources.status) {
                                    SUCCESS -> {
                                        Toast.makeText(context, resources.data?.body()?.message, Toast.LENGTH_SHORT).show()
                                        item.removeAt(position)
                                        notifyDataSetChanged()
                                    }
                                }
                            })
                        }
                        .setNegativeButton(getString(R.string.cancel)) { p0, p1 ->
                            alertDialog?.dismiss()
                        }.show()
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getPostsByCategory().observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        mAdapter.updateItem(resources.data?.data!!)
                        progressBar.visibility = View.GONE
                        if (!resources.data.data.isNullOrEmpty())
                            txtResult.visibility = View.GONE
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT)
                            .show()
                        progressBar.visibility = View.VISIBLE
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            })
        }

    }

    private fun initRecentPosts() {
        mAdapter = PostAdapter(requireContext(), viewLifecycleOwner)
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getRecents().observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        mAdapter.updateItem(resources.data?.data!!)
                        progressBar.visibility = View.GONE
                        if (!resources.data.data.isNullOrEmpty())
                            txtResult.visibility = View.GONE
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT)
                            .show()
                        progressBar.visibility = View.VISIBLE
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}