package com.mobile.itmi.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mobile.itmi.extension.instanceOf
import com.mobile.itmi.widget.DialogProgressFragment
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun contentView(): View
    abstract fun setupViews()
    abstract fun setupViewEvents()
    abstract fun bindViewModel()

    private var progressbarDialog: DialogProgressFragment? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        setContentView(contentView())
        setupViews()
        setupViewEvents()
        bindViewModel()
    }

    @Synchronized
    fun showLoading(isLoading: Boolean) {
        if (!isLoading) {
            hideProgress()
            return
        }
        showDialogProgress()
    }

    @Synchronized
    private fun showDialogProgress() {
        if (progressbarDialog != null) return
        progressbarDialog = instanceOf(DialogProgressFragment.withData(false))
        progressbarDialog?.show(supportFragmentManager, progressbarDialog?.tag)
    }

    @Synchronized
    private fun hideProgress() {
        progressbarDialog?.dismiss()
        progressbarDialog?.apply {
            supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }
        progressbarDialog = null
    }

    fun showNetworkError(exception: Exception?, view: View) {
        var message = ""
        when (exception) {
            is SocketTimeoutException -> {
                message = "Mohon cek sinyal internet atau koneksi wifi Anda"
            }
            is UnknownHostException -> {
                message = "Sepertinya perangkat Anda tidak terhubung ke koneksi internet"
            }
        }
        val snackbar = Snackbar
            .make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    fun showError(message: String, view: View) {
        val snackbar = Snackbar
            .make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}