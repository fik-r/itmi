package com.mobile.itmi.ui.order.detail

import android.annotation.SuppressLint
import android.view.View
import com.mobile.itmi.base.BaseActivity
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.databinding.ActivityOrderDetailBinding
import com.mobile.itmi.extension.loadImageFromBitmapWithRounded
import com.mobile.itmi.extension.previewImageDialog
import com.mobile.itmi.extension.setStatus
import com.mobile.itmi.extension.subscribeSingleLiveEvent
import org.koin.android.viewmodel.ext.android.viewModel


class OrderDetailActivity : BaseActivity() {
    private val _id by lazy { intent.getStringExtra(ID) }
    private val _viewModel: OrderDetailViewModel by viewModel()
    private lateinit var _binding: ActivityOrderDetailBinding
    override fun contentView(): View {
        return ActivityOrderDetailBinding.inflate(layoutInflater).also {
            _binding = it
        }.root
    }

    override fun setupViews() {
        _viewModel.onEvent(OrderDetailViewModel.Event.OnCreated(_id ?: ""))
    }

    override fun setupViewEvents() {
        _binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun bindViewModel() {
        subscribeSingleLiveEvent(_viewModel.state) {
            when (it) {
                is OrderDetailViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is OrderDetailViewModel.State.ShowNetworkError -> showNetworkError(
                    it.exception,
                    _binding.root
                )
                is OrderDetailViewModel.State.ShowError -> showError(it.message, _binding.root)
                is OrderDetailViewModel.State.ShowData -> bindData(it.medication)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(medication: Medication) {
        _binding.apply {
            textStatus.setStatus(medication.status ?: "")
            textOrderNumber.text = "#${medication.id}"
            medication.address?.let {
                textAddress.text =
                    "${it.firstLine}, ${it.secondLine}, ${it.subDistrict}, ${it.district}, ${it.province}, ${it.postalCode}"
            }

            textPatientName.text = medication.patient?.fullName
            textPhoneNumber.text = medication.patient?.phoneNumber
            imgPrescription.loadImageFromBitmapWithRounded(medication.prescriptionImage ?: "", 10)
            imgPrescription.setOnClickListener {
                previewImageDialog(url = medication.prescriptionImage ?: "")
            }
        }
    }

    companion object {
        private const val ID = "id"

        fun withData(
            id: String
        ): Pair<String, *> {
            return ID to id
        }
    }

}