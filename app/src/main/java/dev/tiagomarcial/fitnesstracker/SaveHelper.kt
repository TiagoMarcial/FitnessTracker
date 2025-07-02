package dev.tiagomarcial.fitnesstracker

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import dev.tiagomarcial.fitnesstracker.model.App
import dev.tiagomarcial.fitnesstracker.model.Calc

object SaveHelper {
    fun salvarResult(context: Context, result: Double, type: String) {
        Thread {
            val app = context.applicationContext as App
            val dao = app.db.calcDao()
            dao.insert(Calc(type = type, res = result))
            Handler(Looper.getMainLooper()).post {
                val intent = Intent(context, ListCalcActivity::class.java)
                intent.putExtra("type", type)
                context.startActivity(intent)
            }
        }.start()
    }
}