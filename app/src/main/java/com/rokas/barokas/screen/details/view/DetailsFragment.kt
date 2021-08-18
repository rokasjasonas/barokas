package com.rokas.barokas.screen.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rokas.barokas.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val postId = arguments?.getInt(KEY_POST_ID)
        Timber.e("xx postId " + postId)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    companion object {
        const val KEY_POST_ID = "key.postId"
    }
}