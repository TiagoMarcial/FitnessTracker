package dev.tiagomarcial.fitnesstracker.utils.helpers

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog

object DialogHelper {
    fun showSimpleDialog(context: Context, title: String, message: String,
    positiveText: String = "Ok", negativeText: String? = null,
    onPositiveClick: (() -> Unit)? = null, onNegativeClick: (() -> Unit)? = null ) {
        val buider = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { dialog, _ ->
                onPositiveClick?.invoke()
            }
        if (negativeText != null) {
            buider.setNegativeButton(negativeText) {dialog, _ -> onNegativeClick?.invoke()}
        }
        buider.show()
    }

    fun hideKeyboard(context: Context, view: View?) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun showOptionsDialog(
        context: Context,
        onEdit: () -> Unit,
        onDelete: () -> Unit
    ) {
        val options = arrayOf("Editar", "Excluir")

        AlertDialog.Builder(context)
            .setTitle("O que deseja fazer?")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> onEdit()
                    1 -> onDelete()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}