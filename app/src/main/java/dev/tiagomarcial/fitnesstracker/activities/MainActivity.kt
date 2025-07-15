package dev.tiagomarcial.fitnesstracker.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.tiagomarcial.fitnesstracker.utils.ui.MainItem
import dev.tiagomarcial.fitnesstracker.R

class MainActivity : AppCompatActivity() {

    //private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val corButtonImc = ContextCompat.getColor(this, R.color.corButtonImc)
        val corButtonTmb = ContextCompat.getColor(this, R.color.corButtonTmb)
        val corButtonGet = ContextCompat.getColor(this, R.color.corButtonGet)
        val corButtonPgc = ContextCompat.getColor(this, R.color.corButtonPgc)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableID = R.drawable.logo_imc,
                textStringID = R.string.label_imc,
                color = corButtonImc
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableID = R.drawable.logo_tmb,
                textStringID = R.string.label_tmb,
                color = corButtonTmb
            )
        )
        mainItems.add(
            MainItem(
                id = 3,
                drawableID = R.drawable.logo_get,
                textStringID = R.string.label_get,
                color = corButtonGet
            )
        )
        mainItems.add(
            MainItem(
                id = 4,
                drawableID = R.drawable.logo_pgc,
                textStringID = R.string.label_pgc,
                color = corButtonPgc
            )
        )
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvMain = findViewById(R.id.rv_main)
        val adapter = MainAdapter(mainItems) { id ->
            when (id) {
                1 -> {
                    val i = Intent (this@MainActivity, ImcActivity::class.java)
                    startActivity(i)
                }
                2 -> {
                    val i = Intent (this@MainActivity, TmbActivity::class.java)
                    startActivity(i)
                }
                3 -> { val i = Intent (this@MainActivity, GetActivity::class.java)
                    startActivity(i)
                }
                4 -> { val i = Intent (this@MainActivity, PgcActivity::class.java)
                    startActivity(i)
                }
            }
        }
//                val adapter = MainAdapter(mainItems, object : OnItemClickListener {
//                override fun onClick(id: Int) {
//                    when(id) {
//                        1 -> {  val i = Intent(this@MainActivity, ImcActivity::class.java)
//                            startActivity(i)}
//                    }
//                }
//
//            })
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)
//        btnImc = findViewById((R.id.btn_imc))
//
//        btnImc.setOnClickListener{
//            //Navegando pra próxima tela
//            val i = Intent(this, ImcActivity::class.java)
//            startActivity(i)
//        }
    }

        private inner class MainAdapter(
            private val mainItems: List<MainItem>,
            //private val onItemClickListener: OnItemClickListener
            private val onItemClickListener: (Int) -> Unit
        ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
                val view = layoutInflater.inflate(R.layout.main_item, parent, false)
                return MainViewHolder(view)
            }

            override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
                val itemCurrent = mainItems[position]
                holder.bind(itemCurrent)
            }

            override fun getItemCount(): Int {
                return mainItems.size
            }

            private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                fun bind(item: MainItem) {
                    val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                    val name: TextView = itemView.findViewById(R.id.item_text_name)
                    val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                    img.setImageResource(item.drawableID)
                    name.setText(item.textStringID)
                    container.setBackgroundColor(item.color)

                    container.setOnClickListener {
                        onItemClickListener.invoke(item.id)
                    }
                }
            }
        }
    }


