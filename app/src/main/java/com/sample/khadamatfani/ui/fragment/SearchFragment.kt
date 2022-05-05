package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status
import com.sample.khadamatfani.databinding.FragmentSearchBinding
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.fragment_home.progressBar
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_recyclerview.*

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private lateinit var mAdapter: PostAdapter

    override fun getLayout() = R.layout.fragment_search

    override fun main(savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        mAdapter = PostAdapter(requireContext(), this)
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            edtSearch.doOnTextChanged { text, start, before, count ->
                search(text.toString()).observe(viewLifecycleOwner, Observer { resources ->
                    when (resources.status) {
                        Status.SUCCESS -> {
                            mAdapter.updateItem(resources.data?.data!!)
                            if(resources.data.data.isEmpty())
                                txtResult.visibility = View.VISIBLE
                            else
                                txtResult.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT)
                                .show()
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                })
            }
        }
    }
}