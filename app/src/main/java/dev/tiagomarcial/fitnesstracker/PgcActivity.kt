package dev.tiagomarcial.fitnesstracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PgcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pgc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var editWeight: EditText = findViewById(R.id.edit_pgc_weight)
        var editHeight: EditText = findViewById(R.id.edit_pgc_height)
        var editAge: EditText = findViewById(R.id.edit_pgc_age)
        var editGender: RadioGroup = findViewById<RadioGroup>(R.id.genderGroupPGC)
        val btnSend: Button = findViewById(R.id.btn_pgc_send)

        btnSend.setOnClickListener {
            if (!InputValidator.validate(editHeight.text.toString()) ||
                !InputValidator.validate(editWeight.text.toString()) ||
                !InputValidator.validate(editAge.text.toString())
            ) {
                Toast.makeText(this, R.string.field_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val age = editAge.text.toString().toInt()
            val genderRadio = editGender.checkedRadioButtonId
            val genderSelected = findViewById<RadioButton>(genderRadio)
            val gender = Gender.selectedGender(genderSelected.text.toString())

            val imcResult = Calculator.calculateImc(weight, height)
            val pgcResult = Calculator.calculatePgc(imcResult, age, gender)
            val pgcResponse = HealthEvaluator.pgcResponse(pgcResult, gender)

            DialogHelper.showSimpleDialog(
                this, getString(R.string.pgc_response, pgcResult),
                getString(pgcResponse),
                negativeText = "Salvar",
                onNegativeClick = { SaveHelper.salvarResult(this, pgcResult, "pgc") }
            )
            DialogHelper.hideKeyboard(this, currentFocus)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                navigationHelper.abrirResultados(this, "pgc")
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}