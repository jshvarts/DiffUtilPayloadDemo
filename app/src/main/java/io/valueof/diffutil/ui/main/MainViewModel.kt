package io.valueof.diffutil.ui.main

import androidx.lifecycle.ViewModel
import io.valueof.diffutil.R
import io.valueof.diffutil.ui.main.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class MainViewModel : ViewModel() {
  private val _itemList = MutableStateFlow<List<Item>>(emptyList())
  val itemList: StateFlow<List<Item>> = _itemList

  init {
    _itemList.value = generateItemList()
  }

  fun toggleFavoriteStatus(id: String, isFavorite: Boolean) {
    _itemList.value = _itemList.value.map {
      if (it.id == id) {
        it.copy(isFavorite = isFavorite)
      } else {
        it
      }
    }
  }

  private fun generateItemList() =
    listOf(
      Item(
        id = UUID.randomUUID().toString(),
        title = "Item 1",
        description = "Item 1 description",
        imageResId = R.drawable.image1,
        isFavorite = false
      ),
      Item(
        id = UUID.randomUUID().toString(),
        title = "Item 2",
        description = "Item 2 description",
        imageResId = R.drawable.image2,
        isFavorite = false
      ),
      Item(
        id = UUID.randomUUID().toString(),
        title = "Item 3",
        description = "Item 3 description",
        imageResId = R.drawable.image3,
        isFavorite = false
      ),
      Item(
        id = UUID.randomUUID().toString(),
        title = "Item 4",
        description = "Item 4 description",
        imageResId = R.drawable.image4,
        isFavorite = false
      ),
      Item(
        id = UUID.randomUUID().toString(),
        title = "Item 5",
        description = "Item 5 description",
        imageResId = R.drawable.image5,
        isFavorite = false
      ),
    )
}