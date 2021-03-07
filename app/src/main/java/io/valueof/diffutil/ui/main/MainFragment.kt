package io.valueof.diffutil.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import io.valueof.diffutil.R
import io.valueof.diffutil.databinding.MainFragmentBinding
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class MainFragment : Fragment(R.layout.main_fragment) {

  companion object {
    fun newInstance() = MainFragment()
  }

  private val binding by viewBinding(MainFragmentBinding::bind)

  private lateinit var viewModel: MainViewModel

  private val itemAdapter = ItemAdapter(this::toggleFavoriteStatus)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    binding.recyclerView.apply {
      setHasFixedSize(true)
      adapter = itemAdapter
    }

    lifecycleScope.launchWhenResumed {
      viewModel.itemList.collect { itemList ->
        Timber.d("current item list $itemList")
        itemAdapter.submitList(itemList)
      }
    }
  }

  private fun toggleFavoriteStatus(id: String, isFavorite: Boolean) {
    viewModel.toggleFavoriteStatus(id, isFavorite)
  }
}