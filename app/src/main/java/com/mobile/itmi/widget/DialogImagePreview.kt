package com.mobile.itmi.widget

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.mobile.itmi.databinding.DialogImagePreviewBinding
import com.mobile.itmi.extension.loadImageFromUrl

class DialogImagePreview : DialogFragment() {

    private var binding: DialogImagePreviewBinding? = null
    private val urlFile by lazy { arguments?.getString(URL_FILE) ?: "" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogImagePreviewBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            closeImg.setOnClickListener { dismiss() }
            previewImg.loadImageFromUrl(urlFile)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onResume() {
        dialog?.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            setGravity(Gravity.CENTER)
        }
        super.onResume()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
            .apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(true)
                setCanceledOnTouchOutside(true)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    companion object {

        private const val URL_FILE = ".fileUrl"

        fun withData(
            url: String
        ): Pair<String, *> {
            return URL_FILE to url
        }
    }


}