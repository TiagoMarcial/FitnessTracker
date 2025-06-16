package dev.tiagomarcial.fitnesstracker


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ImcActivity : AppCompatActivity() {

//    private lateinit var editWeight: EditText
//    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

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
                getString(imcResponseId)
            )
            DialogHelper.hideKeyboard(this, currentFocus)
        }
    }
}