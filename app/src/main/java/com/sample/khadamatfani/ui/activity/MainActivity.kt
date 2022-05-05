package com.sample.khadamatfani.ui.activity

import android.os.Bundle
import com.sample.khadamatfani.ui.BaseActivity
import com.sample.khadamatfani.ui.fragment.MainFragment
import com.sample.khadamatfani.R
import com.sample.khadamatfani.databinding.ActivityMainBinding
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.ui.fragment.LoginFragment
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayout() = R.layout.activity_main

    override fun main(savedInstanceState: Bundle?) {
        if(SharedPreferencesHelper.getString(Constants.TOKEN)!!.isEmpty()) {
            replaceFragment(
                R.id.nav_host_fragment,
                LoginFragment(),
                LoginFragment().tag,
            )
        } else {
            replaceFragment(
                R.id.nav_host_fragment,
                MainFragment(),
                MainFragment().tag,
            )
        }
    }
}