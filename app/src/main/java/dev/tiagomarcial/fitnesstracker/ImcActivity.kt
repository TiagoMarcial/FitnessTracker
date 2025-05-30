package dev.tiagomarcial.fitnesstracker

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)
        btnSend.setOnClickListener {
            if (!validate()){
                Toast.makeText(this,R.string.field_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val result = calculateImc(weight,height)
            Log.d("teste", "resultado: $result")

            val imcResponseId = imcResponse(result)
            val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.imc_response, result))
            .setMessage(imcResponseId)
            .setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, wich: Int) {

                }
            })

            .create()
            .show()
        val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    private fun validate(): Boolean {
        return (editHeight.text.toString().isNotEmpty()
            && editWeight.text.toString().isNotEmpty()
            && !editWeight.text.toString().startsWith("0")
            && !editHeight.text.toString().startsWith("0"))
    }

    private fun calculateImc(weight: Int, height: Int): Double {
        return weight / ( (height / 100.0) * (height / 100.0)  )
    }

    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.imc_normal
            imc < 30.0 -> R.string.imc_hight_weight
            imc < 35.0 -> R.string.imc_so_hight_weight
            imc < 40.0 -> R.string.imc_severely_hight_weight
            else -> R.string.imc_extreme_weight
        }
    }

        /*if (imc < 15.0) {
            return R.string.imc_severely_low_weight
        }
        else if (imc < 16.0) {
            return R.string.imc_very_low_weight
        }
        else if (imc < 18.5) {
            return R.string.imc_low_weight
        }
        else if (imc < 25.0) {
            return R.string.imc_normal
        }
        else if (imc < 30.0) {
            return R.string.imc_hight_weight
        }
        else if (imc < 35.0) {
            return R.string.imc_so_hight_weight
        }
        else if (imc < 40.0) {
            return R.string.imc_severely_hight_weight
        }
        else {
            return R.string.imc_extreme_weight
        }*/
}