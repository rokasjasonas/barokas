package com.rokas.barokas.screen.posts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rokas.barokas.databinding.ViewHolderPostBinding
import com.rokas.barokas.screen.posts.model.PostDomain

class PostsRecyclerAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private val postsList = mutableListOf<PostDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val viewBinding = ViewHolderPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindItem()
    }

    override fun getItemCount(): Int = postsList.size

    fun updateList(newPosts: List<PostDomain>) {
        postsList.clear()
        postsList.addAll(newPosts)
        notifyDataSetChanged()
    }
}
