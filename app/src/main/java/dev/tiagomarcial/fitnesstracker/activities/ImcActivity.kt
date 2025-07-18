package dev.tiagomarcial.fitnesstracker.activities


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import dev.tiagomarcial.fitnesstracker.utils.calculators.Calculator
import dev.tiagomarcial.fitnesstracker.utils.helpers.DialogHelper
import dev.tiagomarcial.fitnesstracker.utils.calculators.HealthEvaluator
import dev.tiagomarcial.fitnesstracker.utils.validators.InputValidator
import dev.tiagomarcial.fitnesstracker.R
import dev.tiagomarcial.fitnesstracker.utils.helpers.SaveHelper
import dev.tiagomarcial.fitnesstracker.utils.helpers.navigationHelper


class ImcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val calcId = intent.getLongExtra("calc_id", -1L)

        val editWeight: EditText = findViewById(R.id.edit_imc_weight)
        val editHeight: EditText = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)
        btnSend.setOnClickListener {
            if (!InputValidator.validate(editHeight.text.toString()) || !InputValidator.validate(
                    editWeight.text.toString()
                )
            ) {
                Toast.makeText(this, R.string.field_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val result = Calculator.calculateImc(weight, height)
            Log.d("teste", "resultado: $result")

            val imcResponseId = HealthEvaluator.imcResponse(result)

            DialogHelper.showSimpleDialog(
                this,
                getString(R.string.imc_response, result),
                getString(imcResponseId),
                negativeText = "Salvar",
                onNegativeClick = { SaveHelper.salvarResult(this, result, "imc", calcId) }
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
                navigationHelper.abrirResultados(this, "imc")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


