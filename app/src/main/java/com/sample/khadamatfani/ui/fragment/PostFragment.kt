package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentItemsBinding
import com.sample.khadamatfani.extensions.loadImage
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.layout_recyclerview.*

class PostFragment : BaseFragment<FragmentItemsBinding>() {
    private lateinit var mAdapter: PostAdapter

    override fun getLayout() = R.layout.fragment_items

    override fun main(savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        mAdapter = PostAdapter(requireContext(), viewLifecycleOwner).apply {  }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getPostsByCategory(requireArguments().getInt("category")).observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        val cat = resources.data?.data?.single()!!
                        loadImage(imgBackground, cat.background)
                        catName.text = cat.title
                        txtTitle.text = cat.title
                        txtCount.text = getString(R.string.posts_count, cat.count.toString())
                        mAdapter.updateItem(resources.data.data.single().posts)
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                    }
                }
            })
        }

        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset >= -355)
                txtTitle.visibility = View.INVISIBLE
            else
                txtTitle.visibility = View.VISIBLE
        })
    }
}