package dev.tiagomarcial.fitnesstracker.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.tiagomarcial.fitnesstracker.utils.calculators.Calculator
import dev.tiagomarcial.fitnesstracker.utils.helpers.DialogHelper
import dev.tiagomarcial.fitnesstracker.model.Gender
import dev.tiagomarcial.fitnesstracker.utils.calculators.HealthEvaluator
import dev.tiagomarcial.fitnesstracker.utils.validators.InputValidator
import dev.tiagomarcial.fitnesstracker.utils.calculators.PhysicalActivityLevel
import dev.tiagomarcial.fitnesstracker.R
import dev.tiagomarcial.fitnesstracker.utils.helpers.SaveHelper
import dev.tiagomarcial.fitnesstracker.utils.helpers.navigationHelper


class GetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        var editWeight: EditText = findViewById(R.id.edit_get_weight)
        var editHeight: EditText = findViewById(R.id.edit_get_height)
        var editAge: EditText = findViewById(R.id.edit_get_age)
        var editGender: RadioGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val calcId = intent.getLongExtra("calc_id", -1L)

        val spinner = findViewById<Spinner>(R.id.spinnerPhysicalActivity)
        val activityLevels = listOf(
            getString(R.string.get_sedentary),
            getString(R.string.get_light),
            getString(R.string.get_moderate),
            getString(R.string.get_active),
            getString(R.string.get_extreme)
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activityLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val btnSend: Button = findViewById(R.id.btn_get_send)
        btnSend.setOnClickListener {
            if (!InputValidator.validate(editHeight.text.toString()) || !InputValidator.validate(
                    editWeight.text.toString()
                ) || !InputValidator.validate(editAge.text.toString())
            ) {
                Toast.makeText(this, R.string.field_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val age = editAge.text.toString().toInt()
            val gender = editGender.checkedRadioButtonId

            val selectedRadio = findViewById<RadioButton>(gender)
            val stringRadio = selectedRadio.text.toString()
            val selectedGender = Gender.selectedGender(stringRadio)

            val resultTMB = Calculator.calculateTmb(weight, height, age, selectedGender)
            Log.d("teste", "resultado: $resultTMB")

            val level = spinner.selectedItem.toString()
            val levelSelected = PhysicalActivityLevel.fromLabel(level)

            val getResult = Calculator.calculateGet(resultTMB, levelSelected)

            val getResponse = HealthEvaluator.getResponse(getResult)

            DialogHelper.showSimpleDialog(
                this,
                getString(R.string.get_response, getResult),
                getString(getResponse),
                negativeText = "Salvar",
                onNegativeClick = { SaveHelper.salvarResult(this, getResult, "get", calcId) }
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
                navigationHelper.abrirResultados(this, "get")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
