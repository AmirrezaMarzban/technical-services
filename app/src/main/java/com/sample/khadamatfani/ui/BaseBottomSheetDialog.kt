package com.sample.khadamatfani.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sample.khadamatfani.R
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.ui.viewmodel.LoginViewModel
import com.sample.khadamatfani.ui.viewmodel.MainViewModel
import com.sample.khadamatfani.ui.viewmodel.SharedViewModel
import org.koin.android.ext.android.inject

abstract class BaseBottomSheetDialog<T : ViewDataBinding> : BottomSheetDialogFragment() {
    protected val logins by inject<LoginViewModel>()
    protected val mainViewModel by inject<MainViewModel>()
    protected val sharedViewModel by inject<SharedViewModel>()

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun main()

    protected lateinit var binding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    protected fun loadFragment(fragment: Fragment) {
        requireActivity().replaceFragment(
            R.id.nav_fragment,
            fragment,
            fragment.tag,
        )
    }
}