package com.sample.khadamatfani.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.sample.khadamatfani.R
import com.sample.khadamatfani.api.Status.*
import com.sample.khadamatfani.databinding.ItemsBinding
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.model.Post
import com.sample.khadamatfani.ui.fragment.CreatePostFragment
import com.sample.khadamatfani.ui.fragment.ShowPostFragment
import com.sample.khadamatfani.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.items.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class PostAdapter(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var postList: MutableList<Post> = ArrayList()
    private val mainViewModel by lifecycleOwner.viewModel<MainViewModel>()
    private var itemClickListener: ItemClickListener ?= null

    var removeButton = false
    var editButton = false
    var isVerified = false

    interface ItemClickListener {
        fun onItemClick(position: Int, item: MutableList<Post>)
    }

    fun clickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.items,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateItem(postList: List<Post>) {
        this.postList = postList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = postList[position]

    inner class PostViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.apply {
                this.item = post
                this.isRemoveEnable = removeButton
                this.isVerifyEnable = isVerified
                this.isEditEnable = editButton
                this.status = post.status == 1
                container.setOnClickListener {
                    (context as AppCompatActivity).replaceFragment(
                        R.id.nav_fragment,
                        ShowPostFragment(),
                        ShowPostFragment().tag,
                        true,
                        bundleOf("post" to post.id)
                    )
                }
                imgEdit.setOnClickListener {
                    (context as AppCompatActivity).replaceFragment(
                        R.id.nav_fragment,
                        CreatePostFragment(),
                        CreatePostFragment().tag,
                        true,
                        bundleOf(
                            "post_id" to post.id,
                            "title" to context.getString(R.string.edit_post)
                        )
                    )
                }
                imgDelete.setOnClickListener {
                    itemClickListener?.onItemClick(adapterPosition, postList)
                }
//                viewModel = albumViewModel
            }
        }
    }
}