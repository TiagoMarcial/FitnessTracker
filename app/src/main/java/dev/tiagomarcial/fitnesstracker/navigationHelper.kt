package dev.tiagomarcial.fitnesstracker

import android.content.Context
import android.content.Intent

object navigationHelper {
    fun abrirResultados(context: Context, tipo: String) {
        val intent = Intent(context, ListCalcActivity::class.java)
        intent.putExtra("type", tipo)
        context.startActivity(intent)
    }
}