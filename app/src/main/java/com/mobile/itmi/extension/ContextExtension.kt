package com.mobile.itmi.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mobile.itmi.widget.DialogImagePreview
import java.io.Serializable
import java.util.*

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
    internalStartActivity(this, T::class.java, params)


fun internalStartActivity(
    ctx: Context,
    activity: Class<out Activity>,
    params: Array<out Pair<String, Any?>>
) {
    ctx.startActivity(createIntent(ctx, activity, params))
}

inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent =
    createIntent(this, T::class.java, params)

fun <T> createIntent(
    ctx: Context,
    clazz: Class<out T>,
    params: Array<out Pair<String, Any?>>
): Intent {
    val intent = Intent(ctx, clazz)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    return intent
}


private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        val value = it.second
        when (value) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}

inline fun <reified T : Fragment> instanceOf(
    vararg params: Pair<String, *>
): T = T::class.java.newInstance().apply { arguments = bundleOf(*params) }

fun FragmentActivity.previewImageDialog(url: String) {
    val dialogPreviewImage = instanceOf<DialogImagePreview>(DialogImagePreview.withData(url))
    dialogPreviewImage.show(supportFragmentManager, dialogPreviewImage.tag)
}

