package dev.tiagomarcial.fitnesstracker.utils.helpers

import android.content.Context
import android.content.Intent
import dev.tiagomarcial.fitnesstracker.activities.ListCalcActivity

object navigationHelper {
    fun abrirResultados(context: Context, tipo: String) {
        val intent = Intent(context, ListCalcActivity::class.java)
        intent.putExtra("type", tipo)
        context.startActivity(intent)
    }
}