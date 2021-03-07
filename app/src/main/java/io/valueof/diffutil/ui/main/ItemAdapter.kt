package io.valueof.diffutil.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.valueof.diffutil.databinding.ItemCardBinding
import io.valueof.diffutil.ui.main.model.Item

class ItemAdapter(
  private val favoriteListener: (String, Boolean) -> Unit
) : ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val binding = ItemCardBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    )
    return ItemViewHolder(binding, favoriteListener)
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int, payloads: MutableList<Any>) {
    if (payloads.isEmpty()) {
      super.onBindViewHolder(holder, position, payloads)
    } else {
      if (payloads[0] == true) {
        holder.bindFavoriteState(getItem(position).isFavorite)
      }
    }
  }
}

class ItemViewHolder(
  private val binding: ItemCardBinding,
  private val favoriteListener: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

  lateinit var item: Item

  init {
    binding.favoriteIcon.setOnClickListener {
      favoriteListener(item.id, !it.isSelected)
    }
  }

  fun bind(item: Item) {
    this.item = item

    binding.image.load(item.imageResId) {
      crossfade(true)
    }
    binding.title.text = item.title
    binding.description.text = item.description
    binding.favoriteIcon.isSelected = item.isFavorite
  }

  fun bindFavoriteState(isFavorite: Boolean) {
    binding.favoriteIcon.isSelected = isFavorite
  }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
  override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem == newItem
  }

  override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
    return if (oldItem.isFavorite != newItem.isFavorite) true else null
  }
}

