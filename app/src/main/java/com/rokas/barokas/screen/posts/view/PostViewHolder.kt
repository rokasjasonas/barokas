package com.rokas.barokas.screen.posts.view

import androidx.recyclerview.widget.RecyclerView
import com.rokas.barokas.databinding.ViewHolderPostBinding
import com.rokas.barokas.screen.posts.model.PostDomain

class PostViewHolder constructor(
    private val binding: ViewHolderPostBinding,
    private val onItemClick: (PostDomain) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var post: PostDomain? = null

    init {
        binding.root.setOnClickListener {
            post?.let { onItemClick(it) }
        }
    }

    fun bindItem(postToBind: PostDomain) {
        post = postToBind

        binding.textView.text = post?.title
    }
}