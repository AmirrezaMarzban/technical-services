package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.FragmentProfileBinding
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.fragment.bottomsheet.ItemsListDialog
import com.sample.khadamatfani.utils.Constants
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getLayout() = R.layout.fragment_profile

    override fun main(savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        mainViewModel.apply {
            getUserProfile().observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    SUCCESS -> {
                        txtSignOutDescription.text = getString(
                            R.string.sign_out_description,
                            resources.data?.body()?.phone
                        )
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

        lnrMyPosts.setOnClickListener {
            loadFragment(SharedFragment(), true, bundleOf("title" to getString(R.string.my_posts)))
        }

        lnrRecent.setOnClickListener {
            loadFragment(SharedFragment(), true, bundleOf("title" to getString(R.string.my_recently)))
        }

        lnrUserInfo.setOnClickListener {
            loadFragment(UserInfoFragment(), true)
        }

        lnrTheme.setOnClickListener {
            val itemsListDialog = ItemsListDialog()
            itemsListDialog.arguments = bundleOf("base" to Constants.THEME)
            itemsListDialog.show(childFragmentManager, ItemsListDialog().tag)
        }
    }

}