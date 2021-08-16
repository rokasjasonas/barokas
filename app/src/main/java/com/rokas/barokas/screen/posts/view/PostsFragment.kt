package com.rokas.barokas.screen.posts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rokas.barokas.R
import com.rokas.barokas.screen.details.view.DetailsFragment

class PostsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}