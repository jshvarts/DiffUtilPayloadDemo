package io.valueof.diffutil.ui.main.model

import androidx.annotation.DrawableRes

data class Item(
  val id: String,
  val title: String,
  val description: String,
  @DrawableRes val imageResId: Int,
  val isFavorite: Boolean
)