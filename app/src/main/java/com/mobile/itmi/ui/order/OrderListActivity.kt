package com.mobile.itmi.ui.order

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.itmi.base.BaseActivity
import com.mobile.itmi.databinding.ActivityOrderListBinding
import com.mobile.itmi.extension.startActivity
import com.mobile.itmi.extension.subscribeSingleLiveEvent
import com.mobile.itmi.ui.order.detail.OrderDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class OrderListActivity : BaseActivity() {
    private val _viewModel: OrderListViewModel by viewModel()
    private lateinit var _binding: ActivityOrderListBinding
    private val _orderListAdapter by lazy {
        OrderListAdapter {
            startActivity<OrderDetailActivity>(
                OrderDetailActivity.withData(it)
            )
        }
    }

    override fun contentView(): View {
        return ActivityOrderListBinding.inflate(layoutInflater).also {
            _binding = it
        }.root
    }

    override fun setupViews() {
        _binding.apply {
            listOrder.layoutManager =
                LinearLayoutManager(this@OrderListActivity, RecyclerView.VERTICAL, false)

            listOrder.adapter = _orderListAdapter
        }
        _viewModel.onEvent(OrderListViewModel.Event.OnCreated)
    }

    override fun setupViewEvents() {

    }

    override fun bindViewModel() {
        subscribeSingleLiveEvent(_viewModel.state) {
            when (it) {
                is OrderListViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is OrderListViewModel.State.ShowNetworkError -> showNetworkError(
                    it.exception,
                    _binding.root
                )
                is OrderListViewModel.State.ShowError -> showError(it.message, _binding.root)
                is OrderListViewModel.State.ShowList -> _orderListAdapter.medicationList = it.list
            }
        }
    }

}