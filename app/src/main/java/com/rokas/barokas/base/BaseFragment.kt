package com.rokas.barokas.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VBinding : ViewBinding>(layoutId: Int) : Fragment(layoutId) {
    val binding by viewBinding { view -> bindView(view) }

    protected abstract fun bindView(view: View): VBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpActionBar()
        setUpViews()
        setUpObservers()
    }

    open fun setUpActionBar() = Unit

    open fun setUpViews() = Unit

    open fun setUpObservers() = Unit
}
