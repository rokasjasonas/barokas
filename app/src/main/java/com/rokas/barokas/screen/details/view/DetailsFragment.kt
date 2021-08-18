package com.rokas.barokas.screen.details.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rokas.barokas.BuildConfig
import com.rokas.barokas.R
import com.rokas.barokas.base.BaseFragment
import com.rokas.barokas.component.imageloader.ImageLoader
import com.rokas.barokas.databinding.FragmentDetailsBinding
import com.rokas.barokas.screen.details.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    private val viewModel by viewModels<DetailsViewModel>()
    private var disposable = CompositeDisposable()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun bindView(view: View) = FragmentDetailsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postId = getPostId()
        getScreenDetails(postId)
    }

    private fun getScreenDetails(postId: Int?) {
        postId?.let { disposable.add(viewModel.getScreenDetails(postId)) }
    }

    private fun getPostId() = arguments?.getInt(KEY_POST_ID)

    override fun setUpObservers() {
        viewModel.getPostLiveData().observe(
            viewLifecycleOwner,
            { post ->
                binding.titleTextView.text = post.title
                binding.bodyTextView.text = post.body
                imageLoader.loadRoundedImage(
                    binding.imageView,
                    BuildConfig.IMAGES_URL + post.userId
                )
            }
        )
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    companion object {
        const val KEY_POST_ID = "key.postId"
    }
}