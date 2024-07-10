package mx.edu.uts.criticografo_misa

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    overrideee fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre: EditText = findViewById(R.id.editTextName)
        val generoMasc: RadioButton = findViewById(R.id.radioButtonMale)
        val generoFem: RadioButton = findViewById(R.id.radioButtonFemale)
        val radioGroupGenero: RadioGroup = findViewById(R.id.radioGroupGenero)

        val checkBoxAlto: CheckBox = findViewById(R.id.checkBoxAlto)
        val checkBoxFeo: CheckBox = findViewById(R.id.checkBoxFeo)
        val checkBoxListo: CheckBox = findViewById(R.id.checkBoxListo)
        val checkBoxExtravagante: CheckBox = findViewById(R.id.checkBoxExtravagante)
        val checkBoxRaro: CheckBox = findViewById(R.id.checkBoxRaro)
        val checkBoxGrande: CheckBox = findViewById(R.id.checkBoxGrande)

        val btnCriticar: Button = findViewById(R.id.buttonCriticar)
        val resultado: TextView = findViewById(R.id.textViewResult)

        btnCriticar.setOnClickListener {
            val nombreTexto = nombre.text.toString().trim()
            val generoSeleccionado = radioGroupGenero.checkedRadioButtonId

            if (nombreTexto.isEmpty() || generoSeleccionado == -1) {
                resultado.text = "Por favor, complete todos los campos."
                return@setOnClickListener
            }

            val esHombre = generoMasc.isChecked
            val caracteristicas = mutableListOf<String>()

            if (checkBoxAlto.isChecked) {
                caracteristicas.add(if (esHombre) "alto" else "alta")
            }
            if (checkBoxFeo.isChecked) {
                caracteristicas.add(if (esHombre) "feo" else "fea")
            }
            if (checkBoxListo.isChecked) {
                caracteristicas.add(if (esHombre) "listo" else "lista")
            }
            if (checkBoxExtravagante.isChecked) {
                caracteristicas.add("extravagante")
            }
            if (checkBoxRaro.isChecked) {
                caracteristicas.add(if (esHombre) "raro" else "rara")
            }
            if (checkBoxGrande.isChecked) {
                caracteristicas.add("grande")
            }

            val resultadoTexto = if (caracteristicas.isNotEmpty()) {
                val joinedCaracteristicas = if (caracteristicas.size > 1) {
                    caracteristicas.dropLast(1).joinToString(", ") + " y " + caracteristicas.last()
                } else {
                    caracteristicas.first()
                }
                "$nombreTexto es $joinedCaracteristicas."
            } else {
                "$nombreTexto no tiene características seleccionadas."
            }

            resultado.text = resultadoTexto
        }
    }
}