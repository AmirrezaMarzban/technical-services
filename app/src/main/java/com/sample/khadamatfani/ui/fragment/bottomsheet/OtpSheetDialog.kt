package com.sample.khadamatfani.ui.fragment.bottomsheet

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.sample.khadamatfani.ui.fragment.MainFragment
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.OtpVerificationDialogBinding
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.ui.BaseBottomSheetDialog
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper
import kotlinx.android.synthetic.main.otp_verification_dialog.*

class OtpSheetDialog : BaseBottomSheetDialog<OtpVerificationDialogBinding>() {

    override fun getLayout() = R.layout.otp_verification_dialog

    override fun main() {
        dialog?.setCancelable(false)
        edtPhone.doOnTextChanged { text, start, before, count ->
            if(text?.length == 4) {
                logins.sendOtpCode(text.toString().toInt()).observe(this, { resources ->
                    when (resources.status) {
                        SUCCESS -> {
                            resources.data?.body()?.apply {
                                if (status == "successful" || status == "sent") {
                                    SharedPreferencesHelper.putString(Constants.TOKEN, token)
                                    requireActivity().replaceFragment(
                                        R.id.nav_host_fragment,
                                        MainFragment(),
                                        MainFragment().tag
                                    )
                                    dismiss()
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
                            Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        LOADING -> {
                        }
                    }
                })
            }
        }
    }
}