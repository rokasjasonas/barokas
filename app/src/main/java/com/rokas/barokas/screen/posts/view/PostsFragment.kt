package com.rokas.barokas.screen.posts.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.rokas.barokas.R
import com.rokas.barokas.base.BaseFragment
import com.rokas.barokas.databinding.FragmentPostsBinding
import com.rokas.barokas.screen.details.view.DetailsFragment.Companion.KEY_POST_ID
import com.rokas.barokas.screen.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class PostsFragment : BaseFragment<FragmentPostsBinding>(R.layout.fragment_posts) {
    private val viewModel by viewModels<PostsViewModel>()
    private var disposable = CompositeDisposable()
    private lateinit var postsAdapter: PostsRecyclerAdapter

    override fun bindView(view: View) = FragmentPostsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

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
        postsAdapter = PostsRecyclerAdapter { post ->
            view?.let {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_postsFragment_to_detailsFragment,
                        Bundle().apply { putInt(KEY_POST_ID, post.id) }
                    )
            }
        }
        binding.postsRecyclerView.adapter = postsAdapter
    }
}