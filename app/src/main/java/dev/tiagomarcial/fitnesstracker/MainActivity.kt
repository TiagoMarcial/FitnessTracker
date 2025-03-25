package dev.tiagomarcial.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //private lateinit var btnImc: LinearLayout
        private lateinit var rvMain: RecyclerView
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            rvMain = findViewById(R.id.rv_main)
            val adapter = MainAdapter()
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
    private inner class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 15
        }
    }

    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
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