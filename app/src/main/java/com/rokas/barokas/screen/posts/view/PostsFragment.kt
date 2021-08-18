package com.rokas.barokas.screen.posts.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.rokas.barokas.R
import com.rokas.barokas.base.BaseFragment
import com.rokas.barokas.databinding.FragmentPostsBinding
import com.rokas.barokas.screen.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

@AndroidEntryPoint
class PostsFragment : BaseFragment<FragmentPostsBinding>(R.layout.fragment_posts) {
    private val viewModel by viewModels<PostsViewModel>()
    private var disposable = CompositeDisposable()
    private lateinit var postsAdapter: PostsRecyclerAdapter

    override fun bindView(view: View) = FragmentPostsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        view.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_postsFragment_to_detailsFragment)
        }

        setUpObservers()
        disposable.add(viewModel.getPosts())
    }

    override fun setUpObservers() {
        viewModel.getPostsLiveData().observe(
            viewLifecycleOwner,
            { posts -> postsAdapter.updateList(posts) }
        )
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    private fun setUpRecyclerView() {
        postsAdapter = PostsRecyclerAdapter {
            Timber.e("xx item clicked " + it)
        }
        binding.postsRecyclerView.adapter = postsAdapter
    }
}