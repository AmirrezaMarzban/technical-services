package com.sample.khadamatfani.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.khadamatfani.R
import com.sample.khadamatfani.databinding.ItemsListBinding
import com.sample.khadamatfani.interfaces.ItemClickListener
import com.sample.khadamatfani.model.Category
import com.sample.khadamatfani.model.City
import com.sample.khadamatfani.model.Province
import com.sample.khadamatfani.model.Same
import kotlin.collections.ArrayList

class ItemsListAdapter(
    private val context: Context,
) : RecyclerView.Adapter<ItemsListAdapter.CreatePostViewHolder>() {

    private var itemList: MutableList<Any> = ArrayList()
    private var itemClickListener: ItemClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatePostViewHolder =
        CreatePostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.items_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: CreatePostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateItem(itemList: List<Any>) {
        this.itemList = itemList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = itemList[position]

    fun onClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class CreatePostViewHolder(private val binding: ItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any) {
            binding.apply {
                if (obj is Category) {
                    container.setOnClickListener {
                        itemClickListener?.onItemClick(adapterPosition, itemList)
                    }
                    txtName.text = obj.title
                }
                if (obj is Province) {
                    container.setOnClickListener {
                        itemClickListener?.onItemClick(adapterPosition, itemList)
                    }
                    txtName.text = obj.name
                }
                if (obj is City) {
                    container.setOnClickListener {
                        itemClickListener?.onItemClick(adapterPosition, itemList)
                    }
                    txtName.text = obj.name
                }
                if (obj is Same) {
                    container.setOnClickListener {
                        itemClickListener?.onItemClick(adapterPosition, itemList)
                    }
                    txtName.text = obj.title
                }
                if (obj is String) {
                    container.setOnClickListener {
                        itemClickListener?.onItemClick(adapterPosition, itemList)
                    }
                    txtName.text = obj
                }
            }
        }
    }
}