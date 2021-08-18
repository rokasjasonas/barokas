package com.rokas.barokas.screen.details.view

import android.os.Bundle
import android.view.View
import com.rokas.barokas.R
import com.rokas.barokas.base.BaseFragment
import com.rokas.barokas.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    private var disposable = CompositeDisposable()

    override fun bindView(view: View) = FragmentDetailsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postId = arguments?.getInt(KEY_POST_ID)
        Timber.e("xx postId " + postId)
    }

    override fun setUpObservers() {
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    companion object {
        const val KEY_POST_ID = "key.postId"
    }
}