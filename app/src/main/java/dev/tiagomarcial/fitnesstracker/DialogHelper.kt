package dev.tiagomarcial.fitnesstracker

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog

object DialogHelper {
    fun showSimpleDialog(context: Context, title: String, message: String,
    positiveText: String = "Ok", negativeText: String? = null,
    onPositiveClick: (() -> Unit)? = null, onNegativeClick: (() -> Unit)? = null ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()

    }
    fun hideKeyboard(context: Context, view: View?) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}