package dev.tiagomarcial.fitnesstracker

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class TmbActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editAge: EditText
    private lateinit var editGender: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)
    }
}