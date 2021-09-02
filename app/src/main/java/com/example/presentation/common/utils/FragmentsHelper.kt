package com.example.presentation.common.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.R
import com.example.presentation.screens.common.controllers.BaseActivity
import com.example.presentation.screens.common.controllers.BaseFragment

/**
 * A Helper class to handle Fragments transactions
 */
class FragmentsHelper {

    fun <T> replaceFragment(
        activity: BaseActivity,
        fragment: BaseFragment,
        keys: T?
    ) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        if (keys != null && keys is Bundle) {
            fragment.arguments = keys
        }
        fragmentTransaction.replace(R.id.container, fragment, fragment::class.java.name)
            .commitAllowingStateLoss()
    }

    fun <T> addFragment(
        activity: BaseActivity,
        fragment: BaseFragment,
        keys: T?,
        tag: String? = null
    ) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        if (keys != null && keys is Bundle) {
            fragment.arguments = keys
        }
        fragmentTransaction.add(R.id.container, fragment, fragment::class.java.name)
            .addToBackStack(tag).commitAllowingStateLoss()
    }

    fun <T> addFragmentWithFadeAnim(
        activity: BaseActivity,
        fragment: BaseFragment,
        keys: T?,
        tag: String? = null
    ) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        if (keys != null && keys is Bundle) {
            fragment.arguments = keys
        }
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        fragmentTransaction.add(R.id.container, fragment, fragment::class.java.name)
            .addToBackStack(tag).commitAllowingStateLoss()
    }

    /**
     * Return the currently displayed Fragment in the container
     */
    fun getFrontFragment(activity: BaseActivity?): Fragment? {
        return try {
            activity?.supportFragmentManager?.findFragmentById(R.id.container)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Removes the given fragment from the container.
     */
    fun removeFragment(activity: BaseActivity?, frag: Fragment?) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        if (frag != null) {
            fragmentTransaction?.remove(frag)?.commit()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    /**
     * Removes the given fragment from the container.
     */
    fun removeFragmentTillFragment(activity: BaseActivity?) {
        var size = activity?.supportFragmentManager?.backStackEntryCount ?: 0
        while (size > 1) {
            activity?.supportFragmentManager?.popBackStackImmediate()
            size = activity?.supportFragmentManager?.backStackEntryCount ?: 0
        }
    }


    /**
     * Get Fragment by tag name
     */
    fun getFragmentByTag(activity: BaseActivity, tag: String): Fragment? {
        return activity.supportFragmentManager.findFragmentByTag(tag)
    }
}
