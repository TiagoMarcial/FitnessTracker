package dev.tiagomarcial.fitnesstracker

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import dev.tiagomarcial.fitnesstracker.model.App
import dev.tiagomarcial.fitnesstracker.model.Calc

object SaveHelper {
    fun salvarResult(context: Context, result: Double, type: String, id: Long) {
        Thread {
            val app = context.applicationContext as App
            val dao = app.db.calcDao()
            if (id != -1L) {

                dao.update(Calc(id = id, type = type, res = result))
            } else {

                dao.insert(Calc(type = type, res = result))
            }
            Handler(Looper.getMainLooper()).post {
                val intent = Intent(context, ListCalcActivity::class.java)
                intent.putExtra("type", type)
                context.startActivity(intent)
            }
        }.start()
    }
}