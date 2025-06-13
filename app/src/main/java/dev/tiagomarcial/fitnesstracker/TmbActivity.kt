package dev.tiagomarcial.fitnesstracker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TmbActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editAge: EditText
    private lateinit var editGender: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        editWeight = findViewById(R.id.edit_tmb_weight)
        editHeight = findViewById(R.id.edit_tmb_height)
        editAge = findViewById(R.id.edit_tmb_age)
        editGender = findViewById<RadioGroup>(R.id.genderGroup)
        val btnSend: Button = findViewById(R.id.btn_tmb_send)

        btnSend.setOnClickListener {
            if (!InputValidator.validate(editHeight.text.toString()) || !InputValidator.validate(editWeight.text.toString()) || !InputValidator.validate(editAge.text.toString())) {
                Toast.makeText(this,R.string.field_messages, Toast.LENGTH_SHORT).show()
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

            DialogHelper.showSimpleDialog(this, getString(R.string.tmb_response, result), getString(tmbResponseId))
            DialogHelper.hideKeyboard(this, currentFocus)
        }
    }
}