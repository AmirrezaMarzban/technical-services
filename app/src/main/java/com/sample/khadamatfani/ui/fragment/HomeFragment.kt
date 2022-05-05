package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentHomeBinding
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.adapter.CategoryAdapter
import com.sample.khadamatfani.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_recyclerview.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var mAdapter: CategoryAdapter

    override fun getLayout() = R.layout.fragment_home

    override fun main(savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        mAdapter = CategoryAdapter(requireContext(), this)
        rv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }
        mainViewModel.apply {
            getCategories().observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        mAdapter.updateItem(resources.data?.data!!)
                        progressBar.visibility = View.GONE
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT).show()
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