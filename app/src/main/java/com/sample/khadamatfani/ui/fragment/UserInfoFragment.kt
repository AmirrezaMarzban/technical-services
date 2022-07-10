package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentUserInfoBinding
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.fragment.bottomsheet.ItemsListDialog
import com.sample.khadamatfani.ui.fragment.bottomsheet.OtpSheetDialog
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {
    override fun getLayout() = R.layout.fragment_user_info

    override fun main(savedInstanceState: Bundle?) {
        val itemListDialog = ItemsListDialog()
        
        lnrLocation.setOnClickListener {
            itemListDialog.arguments = bundleOf("base" to Constants.LOCATION)
            itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }

        imgSubmit.setOnClickListener {
           editUserInfo()
        }

        init()
    }

    private fun editUserInfo() {
        mainViewModel.updateUserProfile(edtName.text.toString(), edtPhone.text.toString(),
            txtLocation.text.toString()).observe(viewLifecycleOwner) { resources ->
            when (resources.status) {
                SUCCESS -> {
                    resources.data?.body()?.apply {
                        if (resources.data.body()?.status == "successful") {
                            Toast.makeText(
                                requireContext(),
                                message,
                                Toast.LENGTH_SHORT
                            ).show()
                            requireActivity().onBackPressed()
                        } else if (status == "sent") {
                            OtpSheetDialog().show(childFragmentManager, OtpSheetDialog().tag)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    progressBar.visibility = View.GONE
                }
                ERROR -> {
                    Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT).show()
                }
                LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun init() {
        mainViewModel.getUserProfile().observe(viewLifecycleOwner) { resources ->
            when (resources.status) {
                SUCCESS -> {
                    edtName.setText(resources.data?.body()!!.name)
                    edtPhone.setText(resources.data.body()!!.phone)
                    txtLocation.text = resources.data.body()!!.location
                    txtRegisterDate.text =
                        getString(R.string.user_register_date, resources.data.body()!!.created_at)
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
        }

        sharedViewModel.locationData.observe(viewLifecycleOwner) {
            txtLocation.text = it
        }
    }

}