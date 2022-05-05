package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentCreatePostBinding
import com.sample.khadamatfani.model.Post
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.fragment.bottomsheet.ItemsListDialog
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper
import kotlinx.android.synthetic.main.fragment_create_post.*

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding>() {
    override fun getLayout() = R.layout.fragment_create_post

    override fun main(savedInstanceState: Bundle?) {
        txtTitle.text = requireArguments().getString("title")
        val itemListDialog = ItemsListDialog()
        lnrCategory.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.CATEGORY)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }
        lnrLocation.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.LOCATION)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }
        lnrCooperation.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.COOPERATION)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }
        lnrPmethod.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.P_METHOD)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }
        lnrWorkingHours.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.WORKING_HOURS)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }
        lnrWorkingExperiences.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.WORKING_EXPERIENCES)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }

        imgSubmit.setOnClickListener {
            if (requireArguments().containsKey("post_id"))
                updatePost(requireArguments().getInt("post_id"))
            else
                createPost()
        }
        init()
    }

    private fun init() {
        if (requireArguments().containsKey("post_id")) {
            mainViewModel.getSinglePost(requireArguments().getInt("post_id")).observe(viewLifecycleOwner, { resources ->
                when(resources.status) {
                    SUCCESS -> {
                        val data = resources.data?.data!!.single()
                        edtPostTitle.setText(data.title)
                        edtPostDescription.setText(data.description)
                        txtCategory.text = data.category
                        txtLocation.text = data.location
                        txtCooperation.text = data.cooperation
                        txtPMthod.text = data.pmethod
                        chckInsurance.isChecked = data.insurance == "دارد"
                        txtWorkingHours.text = data.workinghours
                        chckRemote.isChecked = data.remote == "دارد"
                        txtWorkingExperiences.text = data.working_experience
                        chckmilitary.isChecked = data.military_service == "دارد"
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
        sharedViewModel.categoryData.observe(viewLifecycleOwner, {
            binding.category = it.title
        })
        sharedViewModel.locationData.observe(viewLifecycleOwner, {
            binding.location = it
        })
        sharedViewModel.cooperation.observe(viewLifecycleOwner, {
            binding.cooperation = it.title
        })
        sharedViewModel.pMethodData.observe(viewLifecycleOwner, {
            binding.pMethod = it.title
        })
        sharedViewModel.workinghoursData.observe(viewLifecycleOwner, {
            binding.workingHours = it.title
        })
        sharedViewModel.workingeExperiencesData.observe(viewLifecycleOwner, {
            binding.workingExperiences = it.title
        })

    }

    private fun createPost() {
        if (edtPostTitle.text.toString().isNotEmpty() && edtPostDescription.text.toString()
                .isNotEmpty() &&
            sharedViewModel.categoryData.value != null && !sharedViewModel.locationData.value.isNullOrEmpty()
            && sharedViewModel.cooperation.value != null && sharedViewModel.pMethodData.value != null &&
            sharedViewModel.workinghoursData.value != null && sharedViewModel.workingeExperiencesData.value != null
        ) {
            mainViewModel.createPost(
                sharedViewModel.categoryData.value!!.id,
                edtPostTitle.text.toString(),
                edtPostDescription.text.toString(),
                sharedViewModel.locationData.value!!,
                sharedViewModel.workingeExperiencesData.value!!.id,
                sharedViewModel.cooperation.value!!.id,
                sharedViewModel.pMethodData.value!!.id,
                sharedViewModel.workinghoursData.value!!.id,
                chckInsurance.isChecked,
                chckRemote.isChecked,
                chckmilitary.isChecked,
            ).observe(viewLifecycleOwner, { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        Toast.makeText(
                            requireContext(),
                            resources.data!!.body()!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                        requireActivity().onBackPressed()
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
//                Toast.makeText(requireContext(), "posted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "not posted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updatePost(postId: Int) {
        if (edtPostTitle.text.toString().isNotEmpty() && edtPostDescription.text.toString().isNotEmpty()) {
            mainViewModel.updatePost(
                postId,
                txtCategory.text.toString(),
                edtPostTitle.text.toString(),
                edtPostDescription.text.toString(),
                txtLocation.text.toString(),
                txtWorkingExperiences.text.toString(),
                txtCooperation.text.toString(),
                txtPMthod.text.toString(),
                txtWorkingHours.text.toString(),
                chckInsurance.isChecked,
                chckRemote.isChecked,
                chckmilitary.isChecked,
            ).observe(viewLifecycleOwner, { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        Toast.makeText(
                            requireContext(),
                            resources.data!!.body()!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                        requireActivity().onBackPressed()
                        progressBar.visibility = View.GONE
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_LONG)
                            .show()
                        progressBar.visibility = View.VISIBLE
                    }
                    LOADING -> {
//                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_LONG)
//                            .show()
                        progressBar.visibility = View.VISIBLE
                    }
                }
            })
//                Toast.makeText(requireContext(), "posted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "not posted", Toast.LENGTH_SHORT).show()
        }
    }

}