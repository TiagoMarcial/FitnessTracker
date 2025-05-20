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

class MainActivity : AppCompatActivity(), OnItemClickListener {

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
                    drawableID = R.drawable.ic_launcher_foreground,
                    textStringID = R.string.label_imc,
                    color = Color.BLUE
                )
            )
            mainItems.add(
                MainItem(
                    id = 2,
                    drawableID = R.drawable.outline_4k_24,
                    textStringID = R.string.imc_normal,
                    color = Color.GREEN
                )
            )
            mainItems.add(
                MainItem(
                    id = 3,
                    drawableID = R.drawable.outline_account_balance_24,
                    textStringID = R.string.imc_normal,
                    color = Color.GRAY
                )
            )
            mainItems.add(
                MainItem(
                    id = 4,
                    drawableID = R.drawable.outline_account_balance_24,
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
            val adapter = MainAdapter(mainItems, this)
            rvMain.adapter = adapter
            rvMain.layoutManager = GridLayoutManager(this, 2)
//        btnImc = findViewById((R.id.btn_imc))
//
//        btnImc.setOnClickListener{
//            //Navegando pra próxima tela, cumpadre
//            val i = Intent(this, ImcActivity::class.java)
//            startActivity(i)
//        }
    }
    private inner class MainAdapter(private val mainItems: List<MainItem>, private val onItemClickListener: OnItemClickListener)
        : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
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
                onItemClickListener.onClick(item.id)
                }
            }
        }
    }


    override fun onClick(id: Int) {
        when(id) {
            1 -> {  val i = Intent(this, ImcActivity::class.java)
            startActivity(i)}
        }
    }
}

/* PASSOS PARA O RECYCLEVIEW
1 - Desenhar o XML
2 - Dizer onde as células (os botões com o layout) vão aparecer (no componente recyclerview, na tela principal)
3 - unidade lógica: dizer para o sistema que as células criadas no XML precisam estar dentro da Recycleview + sua quantidade
3.1 -> para isso precisa de 2 componentes especificos:
3.1.1 -> o ADAPTADOR para informar para o SDK qual o layout que vai inflar e quantos itens vai ter e como vai
renderizar a parte dinamica (precisa do adaptador porque os devs do Android nunca vão saber qual layout criaremos)
3.1.2-> o LAYOUT MANAGER para dizer a posição que será colocado


*/