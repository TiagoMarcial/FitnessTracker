package dev.tiagomarcial.fitnesstracker

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import dev.tiagomarcial.fitnesstracker.model.App
import dev.tiagomarcial.fitnesstracker.model.Calc

object SaveHelper {
    fun salvarImc(context: Context, result: Double) {
        Thread {val app = context.applicationContext as App
            val dao = app.db.calcDao()
            dao.insert(Calc(type = "imc", res = result))
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, R.string.saved, Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}