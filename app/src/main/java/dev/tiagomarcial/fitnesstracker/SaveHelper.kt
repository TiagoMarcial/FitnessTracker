package dev.tiagomarcial.fitnesstracker

import android.content.Context
import dev.tiagomarcial.fitnesstracker.model.App
import dev.tiagomarcial.fitnesstracker.model.Calc

object SaveHelper {
    fun salvarImc(context: Context, result: Double) {
        val app = context.applicationContext as App
        val dao = app.db.calcDao()
        dao.insert(Calc(type = "imc", res = result))
    }
}