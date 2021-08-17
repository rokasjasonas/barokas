package com.rokas.barokas.screen.posts.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.rokas.barokas.R
import com.rokas.barokas.screen.details.view.DetailsFragment
import com.rokas.barokas.screen.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private val viewModel by viewModels<PostsViewModel>()

    private var disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_postsFragment_to_detailsFragment)
        }

        setUpObservers()

        disposable.add(viewModel.onScreenStart())
    }

    private fun setUpObservers() {
        viewModel.getMessageResIdLiveData().observe(
            viewLifecycleOwner,
            { response -> Log.e("xx", "" + response) }
        )
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}