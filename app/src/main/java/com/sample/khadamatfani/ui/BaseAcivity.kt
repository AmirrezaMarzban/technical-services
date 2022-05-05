package com.sample.khadamatfani.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sample.khadamatfani.R
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.ui.fragment.LoginFragment
import com.sample.khadamatfani.ui.fragment.SearchFragment
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun main(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = SharedPreferencesHelper.getString(Constants.THEME)
        when {
            theme.isNullOrEmpty() -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            theme == Constants.DARK_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            theme == Constants.LIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.apply {
            lifecycleOwner = this@BaseActivity
        }
        main(savedInstanceState)
    }

    fun back(v: View) = onBackPressed()

    fun logout(v: View) {
        SharedPreferencesHelper.remove(Constants.TOKEN)
        replaceFragment(
            R.id.nav_host_fragment,
            LoginFragment(),
            LoginFragment().tag,
        )
    }

    fun search(v: View) {
        replaceFragment(
            R.id.nav_host_fragment,
            SearchFragment(),
            SearchFragment().tag,
            true
        )
    }
}