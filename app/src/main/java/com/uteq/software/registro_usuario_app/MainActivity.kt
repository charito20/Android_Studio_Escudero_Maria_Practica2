package com.uteq.software.registro_usuario_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val tiposDocumento = arrayOf("Cédula", "Pasaporte", "RUC")
    private val meses = arrayOf(
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cargarListas()
    }

    /** Llena las listas desplegables (tipo de documento, día, mes y año). */
    private fun cargarListas() {
        val dias = (1..31).map { it.toString() }.toTypedArray()
        val anioActual = Calendar.getInstance().get(Calendar.YEAR)
        val anios = (anioActual downTo 1950).map { it.toString() }.toTypedArray()

        val txtTipoDocumento = findViewById<MaterialAutoCompleteTextView>(R.id.txtTipoDocumento)
        txtTipoDocumento.setSimpleItems(tiposDocumento)
        txtTipoDocumento.setText(tiposDocumento[0], false)

        findViewById<MaterialAutoCompleteTextView>(R.id.txtDia).setSimpleItems(dias)
        findViewById<MaterialAutoCompleteTextView>(R.id.txtMes).setSimpleItems(meses)
        findViewById<MaterialAutoCompleteTextView>(R.id.txtAnio).setSimpleItems(anios)
    }
}
