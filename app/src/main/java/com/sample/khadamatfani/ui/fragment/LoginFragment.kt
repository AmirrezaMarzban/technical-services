package com.sample.khadamatfani.ui.fragment

import android.os.Bundle
import android.widget.Toast
import com.sample.khadamatfani.databinding.FragmentLoginBinding
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.ui.BaseFragment
import com.sample.khadamatfani.ui.fragment.bottomsheet.OtpSheetDialog
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getLayout() = R.layout.fragment_login

    override fun main(savedInstanceState: Bundle?) {
        btnLogin.setOnClickListener {
            logins.login(edtPhone.text.toString()).observe(this, {resources ->
                when (resources.status) {
                    SUCCESS -> {
                        resources.data?.body()?.apply {
                            if (resources.data.body()?.status == "successful") {
                                Toast.makeText(
                                    requireContext(),
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    ERROR -> {
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT).show()
                    }
                    LOADING -> {
                    }
                }
            })
            OtpSheetDialog().show(parentFragmentManager, OtpSheetDialog().tag)
        }
    }
}
