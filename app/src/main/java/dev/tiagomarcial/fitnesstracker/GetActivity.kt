package dev.tiagomarcial.fitnesstracker

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.tiagomarcial.fitnesstracker.R.id.btn_get_send

class GetActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editAge: EditText
    private lateinit var editGender: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
            val selectedGender = when (gender) {
                R.id.radioMale -> Gender.Male
                R.id.radioFemale -> Gender.Female
                else -> Gender.Male
            }

            val result = Calculator.calculateTmb(weight, height, age, selectedGender)
            Log.d("teste", "resultado: $result")
            val tmbResponseId = HealthEvaluator.tmbResponse(result)
            val level = spinner.selectedItem.toString()
            val levelMap = mapOf(
                "Sedentário" to PhysicalActivityLevel.Sedentary,
                "Leve" to PhysicalActivityLevel.Light,
                "Moderado" to PhysicalActivityLevel.Moderate,
                "Ativo" to PhysicalActivityLevel.Active,
                "Extremo" to PhysicalActivityLevel.Extreme
            )
            val selectedlevel = levelMap[level] ?: PhysicalActivityLevel.Sedentary



            DialogHelper.showSimpleDialog(
                this,
                getString(R.string.tmb_response, result),
                getString(tmbResponseId)
            )
            DialogHelper.hideKeyboard(this, currentFocus)
        }
    }
}