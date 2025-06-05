package dev.tiagomarcial.fitnesstracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableID = R.drawable.baseline_no_meals_ouline_24,
                textStringID = R.string.label_imc,
                color = Color.BLUE
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableID = R.drawable.baseline_no_meals_ouline_24,
                textStringID = R.string.label_tmb,
                color = Color.YELLOW
            )
        )
        mainItems.add(
            MainItem(
                id = 3,
                drawableID = R.drawable.ic_launcher_foreground,
                textStringID = R.string.imc_normal,
                color = Color.GRAY
            )
        )
        mainItems.add(
            MainItem(
                id = 4,
                drawableID = R.drawable.ic_launcher_foreground,
                textStringID = R.string.imc_normal,
                color = Color.MAGENTA
            )
        )
        mainItems.add(
            MainItem(
                id = 5,
                drawableID = R.drawable.ic_launcher_foreground,
                textStringID = R.string.label_imc,
                color = Color.CYAN
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
//            //Navegando pra próxima tela, cumpadre
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


