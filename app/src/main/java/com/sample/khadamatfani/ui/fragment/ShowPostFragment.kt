package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentShowPostBinding
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.fragment.bottomsheet.ItemsListDialog
import com.sample.khadamatfani.utils.Constants
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.progressBar
import kotlinx.android.synthetic.main.fragment_show_post.*

class ShowPostFragment : BaseFragment<FragmentShowPostBinding>() {
    override fun getLayout() = R.layout.fragment_show_post

    override fun main(savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        mainViewModel.apply {
            getSinglePost(requireArguments().getInt("post")).observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        val data = resources.data?.data!!.single()
                        sharedViewModel.currentPost.value = resources.data.data.single()
                        txtDateCategory.text = getString(R.string.date_category, data.created_at, data.location, data.category)
                        txtTitle.text = data.title
                        txtCooperation.text = data.cooperation
                        txtWorkingExperiences.text = data.working_experience
                        txtPMthod.text = data.pmethod
                        txtWorkingHours.text = data.workinghours
                        txtInsurance.text = data.insurance.toString()
                        txtMilitaryService.text = data.military_service.toString()
                        txtDescritpion.text = data.description
                        txtRemote.text = data.remote
                        progressBar.visibility = View.GONE
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_LONG).show()
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