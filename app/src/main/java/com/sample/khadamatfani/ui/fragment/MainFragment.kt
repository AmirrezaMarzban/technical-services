package com.sample.khadamatfani.ui.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.sample.khadamatfani.R
import com.sample.khadamatfani.databinding.FragmentMainBinding
import com.sample.khadamatfani.extensions.fadeVisibility
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.fragment.bottomsheet.ItemsListDialog
import com.sample.khadamatfani.utils.Constants
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayout() = R.layout.fragment_main

    override fun main(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bttm_nav.selectedItemId = R.id.mnu_home
            loadFragment(HomeFragment())
        }
        setUpNavigation()
    }

    private fun setUpNavigation() {
        bttm_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mnu_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.mnu_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

        fbmAdd.setOnClickListener {
            if (sharedViewModel.fbmIsAdd.value == true) {
                loadFragment(CreatePostFragment(), true, bundleOf("title" to getString(R.string.publish_post)))
            } else {
                val itemListDialog = ItemsListDialog()
                itemListDialog.arguments = bundleOf("base" to Constants.CONTACT)
                itemListDialog.show(childFragmentManager, ItemsListDialog().tag)
            }
        }

        sharedViewModel.fbmVisibility.observe(viewLifecycleOwner) { visibility ->
            fbmAdd.fadeVisibility(visibility, 700)
        }

        sharedViewModel.fbmIsAdd.observe(viewLifecycleOwner) { isAdd ->
            if (isAdd) {
                fbmAdd.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.pink))
                fbmAdd.setImageResource(R.drawable.ic_add)
            } else {
                fbmAdd.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.holo_green_light
                    )
                )
                fbmAdd.setImageResource(R.drawable.ic_call)
            }
        }
    }

}