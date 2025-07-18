package dev.tiagomarcial.fitnesstracker.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.tiagomarcial.fitnesstracker.utils.helpers.DialogHelper
import dev.tiagomarcial.fitnesstracker.R
import dev.tiagomarcial.fitnesstracker.model.App
import dev.tiagomarcial.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class ListCalcActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_calc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result = mutableListOf<Calc>()
        val adapter = ListCalcAdapter(result)
        rv = findViewById(R.id.rv_list)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val type = intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        Thread {
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType(type)
            Handler(Looper.getMainLooper()).post {
            result.addAll(response)
            adapter.notifyDataSetChanged()
            }
        }.start()
    }
    private inner class ListCalcAdapter(
        private val listCalc: List<Calc>) : RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return ListCalcViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListCalcViewHolder, position: Int) {
            val itemCurrent = listCalc[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return listCalc.size
        }

        private inner class ListCalcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Calc) {
                val tv = itemView as TextView
                val res = item.res

                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
                val data = sdf.format(item.createdDate)
                val type = item.type
                tv.text = type + " " + getString(R.string.list_response, res, data)

                itemView.setOnLongClickListener {
                    DialogHelper.showOptionsDialog(
                        context = itemView.context,
                        onEdit = {
                            val intent = when (item.type) {
                                "imc" -> Intent(itemView.context, ImcActivity::class.java)
                                "tmb" -> Intent(itemView.context, TmbActivity::class.java)
                                "pgc" -> Intent(itemView.context, PgcActivity::class.java)
                                "get" -> Intent(itemView.context, GetActivity::class.java)
                                else -> null
                            }
                            intent?.let {
                                it.putExtra("calc_id", item.id)
                                itemView.context.startActivity(it)
                            }
                        },
                        onDelete = {
                            Thread {
                                val app = itemView.context.applicationContext as App
                                val dao = app.db.calcDao()
                                dao.delete(item)
                                Handler(Looper.getMainLooper()).post {
                                    (listCalc as MutableList).remove(item)
                                    notifyDataSetChanged()
                                }
                            }.start()
                        }
                    )
                    true
                }
            }
        }
    }
}
