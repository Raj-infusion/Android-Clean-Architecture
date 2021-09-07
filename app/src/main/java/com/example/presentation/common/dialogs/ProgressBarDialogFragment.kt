package com.example.presentation.common.dialogs

import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentManager
import com.example.presentation.common.ViewMvcFactory
import com.example.presentation.screens.common.controllers.BaseDialogFragment
import javax.inject.Inject


class ProgressBarDialogFragment : BaseDialogFragment() {

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    companion object {
        private const val tag = "ProgressBarFullScreen"

        fun show(fm: FragmentManager) {
            hide(fm)
            val frag = ProgressBarDialogFragment()
            fm.beginTransaction().add(frag, tag).commitAllowingStateLoss()
        }

        fun hide(fm: FragmentManager) {
            val frag = fm.findFragmentByTag(tag)
            if (frag != null) {
                fm.beginTransaction().remove(frag).commitAllowingStateLoss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getPresentationComponent().inject(this)
        isCancelable = false
        return mViewMvcFactory.getProgressDialogViewMvc(null).getRootView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

        // creating the fullscreen dialog
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return dialog
    }

}
