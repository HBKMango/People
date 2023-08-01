package com.emma.pruebas.util

import android.os.Bundle
import android.view.ViewGroup
import com.emma.pruebas.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment

abstract class BaseDialog: SupportBlurDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        isCancelable = false
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                resources.displayMetrics.widthPixels,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}