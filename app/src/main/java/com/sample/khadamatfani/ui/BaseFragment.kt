package com.sample.khadamatfani.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sample.khadamatfani.R
import com.sample.khadamatfani.databinding.FragmentCreatePostBinding
import com.sample.khadamatfani.databinding.FragmentShowPostBinding
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.ui.fragment.CreatePostFragment
import com.sample.khadamatfani.ui.viewmodel.LoginViewModel
import com.sample.khadamatfani.ui.viewmodel.MainViewModel
import com.sample.khadamatfani.ui.viewmodel.SharedViewModel
import org.koin.android.ext.android.inject

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected val logins by inject<LoginViewModel>()
    protected val mainViewModel by inject<MainViewModel>()
    protected val sharedViewModel by inject<SharedViewModel>()
    protected lateinit var binding: T
    protected var alertDialog: AlertDialog? = null

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun main(savedInstanceState: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main(savedInstanceState)
        //set FBM state
        sharedViewModel.fbmIsAdd.value = binding !is FragmentShowPostBinding
        if(binding is FragmentCreatePostBinding) {
            sharedViewModel.fbmVisibility.value = View.GONE
        } else {
            sharedViewModel.fbmVisibility.value = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.let {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    protected fun loadFragment(fragment: Fragment, addToBackStack: Boolean = false, extras: Bundle? = null) {
        requireActivity().replaceFragment(
            R.id.nav_fragment,
            fragment,
            fragment.tag,
            addToBackStack,
            extras
        )
    }
}