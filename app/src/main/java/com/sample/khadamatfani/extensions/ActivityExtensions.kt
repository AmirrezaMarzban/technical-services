package com.sample.khadamatfani.extensions

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sample.khadamatfani.R

fun Activity?.replaceFragment(
    @IdRes id: Int = R.id.nav_fragment,
    fragment: Fragment,
    tag: String? = null,
    addToBackStack: Boolean = false,
    extras: Bundle? = null
) {
    val compatActivity = this as? AppCompatActivity ?: return
    if (extras != null) fragment.arguments = extras
    compatActivity.supportFragmentManager.beginTransaction().
    setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
        .apply {
            replace(id, fragment, tag)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
}