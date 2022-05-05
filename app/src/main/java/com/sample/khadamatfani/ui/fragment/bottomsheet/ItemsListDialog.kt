package com.sample.khadamatfani.ui.fragment.bottomsheet

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.OtpVerificationDialogBinding
import com.sample.khadamatfani.interfaces.ItemClickListener
import com.sample.khadamatfani.model.Category
import com.sample.khadamatfani.model.City
import com.sample.khadamatfani.model.Province
import com.sample.khadamatfani.model.Same
import com.sample.khadamatfani.ui.BaseBottomSheetDialog
import com.sample.khadamatfani.ui.adapter.ItemsListAdapter
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper
import kotlinx.android.synthetic.main.items_list_dialog.*
import kotlinx.android.synthetic.main.layout_recyclerview.*
import java.util.*
import androidx.core.net.toUri


class ItemsListDialog : BaseBottomSheetDialog<OtpVerificationDialogBinding>() {
    private lateinit var mAdapter: ItemsListAdapter

    override fun getLayout() = R.layout.items_list_dialog

    override fun main() {
        when(requireArguments().getString(Constants.BASE)) {
            Constants.CATEGORY -> initCategory()
            Constants.LOCATION -> initLocation()
            Constants.COOPERATION -> initCooperation()
            Constants.P_METHOD -> initPaymentmethod()
            Constants.WORKING_HOURS -> initWorkingHours()
            Constants.WORKING_EXPERIENCES -> initWorkingExperiences()
            Constants.CONTACT -> initContact()
            Constants.THEME -> initTheme()
        }
    }

    private fun initContact() {
        progressBar.visibility = View.GONE
            mAdapter = ItemsListAdapter(requireContext()).apply {
                sharedViewModel.currentPost.observe(viewLifecycleOwner, { post->
              updateItem(listOf(getString(R.string.contact), getString(R.string.contact_whatsapp)))
              onClickListener(object : ItemClickListener {
                  override fun onItemClick(position: Int, item: MutableList<Any>) {
                      when (item[position]) {
                          getString(R.string.contact) -> {
                              val intent = Intent(Intent.ACTION_DIAL)
                              intent.data = Uri.parse("tel:${post.user_phone}")
                              startActivity(intent)
                          }
                          getString(R.string.contact_whatsapp) -> {
                              val url = "https://api.whatsapp.com/send?phone=${post.user_phone}"
                              val i = Intent(Intent.ACTION_VIEW)
                              i.data = Uri.parse(url)
                              startActivity(i)
                          }
                      }
                  }
              })
            })
          }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun initTheme() {
        progressBar.visibility = View.GONE
        mAdapter = ItemsListAdapter(requireContext()).apply {
            updateItem(listOf(getString(R.string.night), getString(R.string.light), getString(R.string.system_default)))
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    when (item[position]) {
                        getString(R.string.night) -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            SharedPreferencesHelper.putString(Constants.THEME, Constants.DARK_MODE)
                            requireActivity().recreate()
                        }
                        getString(R.string.light) -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            SharedPreferencesHelper.putString(Constants.THEME, Constants.LIGHT_MODE)
                            requireActivity().recreate()
                        }
                        getString(R.string.system_default) -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            SharedPreferencesHelper.putString(Constants.THEME, Constants.DEFAULT_MODE)
                            requireActivity().recreate()
                        }
                    }
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun initLocation() {
        var province = ""
        var city = ""
        mAdapter = ItemsListAdapter(requireContext()).apply {
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    mainViewModel.apply {
                        if ((item[position] is Province)) {
                            province = (item[position] as Province).name
                            getCities((item[position] as Province).id).observe(viewLifecycleOwner, Observer { resources ->
                                    when (resources.status) {
                                        SUCCESS -> {
                                            mAdapter.updateItem(resources.data?.data?.single()!!.cities)
                                            progressBar.visibility = View.GONE
                                        }
                                        ERROR -> {
                                            Toast.makeText(
                                                requireContext(),
                                                resources.message,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            progressBar.visibility = View.VISIBLE
                                        }
                                        LOADING -> {
                                            progressBar.visibility = View.VISIBLE
                                        }
                                    }
                                })
                        } else {
                            city = (item[position] as City).name
                            dismiss()
                        }
                    }
                sharedViewModel.locationData.value = "$province / $city"
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getProvinces().observe(viewLifecycleOwner, Observer { resources ->
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

    private fun initCategory() {
        mAdapter = ItemsListAdapter(requireContext()).apply {
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    sharedViewModel.categoryData.value = (item[position] as Category)
                    dismiss()
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
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

    private fun initCooperation() {
        mAdapter = ItemsListAdapter(requireContext()).apply {
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    sharedViewModel.cooperation.value = (item[position] as Same)
                    dismiss()
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getCooperations().observe(viewLifecycleOwner, Observer { resources ->
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

    private fun initPaymentmethod() {
        mAdapter = ItemsListAdapter(requireContext()).apply {
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    sharedViewModel.pMethodData.value = (item[position] as Same)
                    dismiss()
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getPMethods().observe(viewLifecycleOwner, Observer { resources ->
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

    private fun initWorkingHours() {
        mAdapter = ItemsListAdapter(requireContext()).apply {
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    sharedViewModel.workinghoursData.value = (item[position] as Same)
                    dismiss()
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getWorkingHours().observe(viewLifecycleOwner, Observer { resources ->
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

    private fun initWorkingExperiences() {
        mAdapter = ItemsListAdapter(requireContext()).apply {
            onClickListener(object : ItemClickListener {
                override fun onItemClick(position: Int, item: MutableList<Any>) {
                    sharedViewModel.workingeExperiencesData.value = (item[position] as Same)
                    dismiss()
                }
            })
        }
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mainViewModel.apply {
            getWorkingExperiences().observe(viewLifecycleOwner, Observer { resources ->
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