package com.sample.khadamatfani.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.sample.khadamatfani.R
import com.sample.khadamatfani.databinding.CategoryItemsBinding
import com.sample.khadamatfani.extensions.replaceFragment
import com.sample.khadamatfani.model.Category
import com.sample.khadamatfani.ui.fragment.PostFragment
import kotlin.collections.ArrayList

class CategoryAdapter(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList: MutableList<Category> = ArrayList()
//    private val albumViewModel by lifecycleOwner.viewModel<AlbumViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.category_items,
                null,
                false
            )
        )

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateItem(categoryList: List<Category>) {
        this.categoryList = categoryList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = categoryList[position]

    inner class CategoryViewHolder(private val binding: CategoryItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {
                this.category = category
                container.setOnClickListener {
                    (context as Activity).replaceFragment(
                        R.id.nav_fragment,
                        PostFragment(),
                        PostFragment().tag,
                        true,
                        bundleOf(
                            "category" to category.id,
                        )
                    )
                }
//                viewModel = albumViewModel
            }
        }
    }
}