package com.epay.codingchallenge.ui.citysearch

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.dialog.BaseBottomSheetDialog
import com.epay.codingchallenge.databinding.BottomSheetDialogCitySearchBinding
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.utils.DebouncingQueryTextListener
import com.epay.codingchallenge.utils.GridItemDecoration
import com.epay.codingchallenge.utils.gone
import com.epay.codingchallenge.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created By Rafiqul Hasan
 */
@AndroidEntryPoint
class CitySearchBottomSheetDialog : BaseBottomSheetDialog<BottomSheetDialogCitySearchBinding>(),
    CitySearchAdapter.CitySearchAdapterCallBack {
    private val viewModel: CitySearchViewModel by viewModels()
    private lateinit var adapter: CitySearchAdapter

    private var itemDecoration: GridItemDecoration? = null
    private var spanCount = 2
    override val layoutResourceId: Int
        get() = R.layout.bottom_sheet_dialog_city_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCities()
        spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                3
            } else {
                2
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecyclerView()
        initObservable()
    }

    override fun onFilterComplete(isEmpty: Boolean) {
        fragmentCommunicator?.hideLoader()
        if (isEmpty) {
            dataBinding.viewEmpty.root.show()
            dataBinding.viewEmpty.btnTryAgain.gone()
            dataBinding.viewEmpty.tvTitle.text = getString(R.string.no_city_found)
        } else {
            dataBinding.viewEmpty.root.gone()
        }
    }

    private fun initView() {
        with(dataBinding) {
            searchView.setOnQueryTextListener(
                DebouncingQueryTextListener(
                    this@CitySearchBottomSheetDialog.lifecycle
                ) { newText ->
                    newText?.let {
                        fragmentCommunicator?.showLoader()
                        adapter.getFilter().filter(it)
                    }
                }
            )
            searchView.requestFocus()
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun initRecyclerView() {
        adapter = CitySearchAdapter(this)
        with(dataBinding) {
            rvCities.apply {
                layoutManager = GridLayoutManager(context, spanCount)
                itemDecoration?.let {
                    removeItemDecoration(it)
                }
                itemDecoration = GridItemDecoration(
                    requireContext().resources.getDimension(
                        R.dimen._4sdp
                    ).toInt(),
                    spanCount
                )
                addItemDecoration(itemDecoration!!)
            }
            rvCities.adapter = adapter
        }
    }

    private fun initObservable() {
        lifecycleScope.launchWhenStarted {
            viewModel.cities.collect { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        fragmentCommunicator?.showLoader()
                    }
                    is NetworkResult.Success -> {
                        fragmentCommunicator?.hideLoader()
                        adapter.setCityList(response.data)
                    }

                    is NetworkResult.Error -> {
                        fragmentCommunicator?.hideLoader()
                    }
                }
            }
        }
    }
}