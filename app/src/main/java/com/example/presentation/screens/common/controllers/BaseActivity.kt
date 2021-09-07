package com.example.presentation.screens.common.controllers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ExampleApplication
import com.example.di.application.ApplicationComponent
import com.example.di.presentation.PresentationComponent
import com.example.di.presentation.PresentationModule
import android.view.WindowManager
import androidx.annotation.UiThread
import com.example.presentation.common.dialogs.ProgressBarDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


abstract class BaseActivity : AppCompatActivity() {

    var mIsInjectOrUsed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    @UiThread
    private fun getApplicationComponent(): ApplicationComponent {
        return ExampleApplication.mApplicationComponent
    }

    fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectOrUsed) {
            throw RuntimeException("There is no need to inject more than once")
        }
        mIsInjectOrUsed = true
        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(this))
    }

    var jobs = listOf<Job>()

    fun launch(code: suspend CoroutineScope.() -> Unit) {
        jobs = jobs + CoroutineScope(Dispatchers.IO).launch (block = code)
    }

    private fun onCleared(){
        jobs.forEach { it.cancel() }
    }


    override fun onDestroy() {
        super.onDestroy()
        // To clear all pending job when activity destroyed.
        onCleared()

    }

    fun showProgressDialog() {
        ProgressBarDialogFragment.show(supportFragmentManager)
    }

    fun hideProgressDialog() {
        ProgressBarDialogFragment.hide(supportFragmentManager)
    }

    override fun onPause() {
        super.onPause()
        hideProgressDialog()
    }

}
