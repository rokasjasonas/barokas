package com.rokas.barokas.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.rokas.barokas.R

abstract class BaseFragment<VBinding : ViewBinding>(layoutId: Int) : Fragment(layoutId) {
    val binding by viewBinding { view -> bindView(view) }
    private var errorDialog: AlertDialog? = null

    protected abstract fun bindView(view: View): VBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpActionBar()
        setUpViews()
        setUpObservers()
    }

    open fun setUpActionBar() = Unit

    open fun setUpViews() = Unit

    open fun setUpObservers() = Unit

    internal fun showErrorDialog(onRetry: () -> Unit) {
        errorDialog?.dismiss()
        val dialogBuilder = AlertDialog.Builder(context).apply {
            setTitle(R.string.error)
            setMessage(R.string.error_message)
            setPositiveButton(R.string.retry) { _, _ ->
                errorDialog?.dismiss()
                onRetry()
            }
            setNegativeButton(R.string.cancel) { _, _ -> }
            create()
        }
        errorDialog = dialogBuilder.show()
    }
}
