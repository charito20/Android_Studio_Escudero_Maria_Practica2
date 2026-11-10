package com.uteq.software.registro_usuario_app

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var txtNombres: TextInputEditText
    private lateinit var txtApellidos: TextInputEditText
    private lateinit var txtTipoDocumento: MaterialAutoCompleteTextView
    private lateinit var txtIdentidad: TextInputEditText
    private lateinit var rgGenero: RadioGroup
    private lateinit var txtDia: MaterialAutoCompleteTextView
    private lateinit var txtMes: MaterialAutoCompleteTextView
    private lateinit var txtAnio: MaterialAutoCompleteTextView
    private lateinit var txtTelefono: TextInputEditText

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

        txtNombres = findViewById(R.id.txtNombres)
        txtApellidos = findViewById(R.id.txtApellidos)
        txtTipoDocumento = findViewById(R.id.txtTipoDocumento)
        txtIdentidad = findViewById(R.id.txtIdentidad)
        rgGenero = findViewById(R.id.rgGenero)
        txtDia = findViewById(R.id.txtDia)
        txtMes = findViewById(R.id.txtMes)
        txtAnio = findViewById(R.id.txtAnio)
        txtTelefono = findViewById(R.id.txtTelefono)

        cargarListas()

        findViewById<MaterialButton>(R.id.btnGrabar).setOnClickListener { grabar() }
    }

    /** Llena las listas desplegables (tipo de documento, día, mes y año). */
    private fun cargarListas() {
        val dias = (1..31).map { it.toString() }.toTypedArray()
        val anioActual = Calendar.getInstance().get(Calendar.YEAR)
        val anios = (anioActual downTo 1950).map { it.toString() }.toTypedArray()

        txtTipoDocumento.setSimpleItems(tiposDocumento)
        txtDia.setSimpleItems(dias)
        txtMes.setSimpleItems(meses)
        txtAnio.setSimpleItems(anios)

        // Valores por defecto
        txtTipoDocumento.setText(tiposDocumento[0], false)
    }

    private fun grabar() {
        val nombres = txtNombres.text.toString().trim()
        val apellidos = txtApellidos.text.toString().trim()
        val tipoDoc = txtTipoDocumento.text.toString()
        val identidad = txtIdentidad.text.toString().trim()
        val dia = txtDia.text.toString()
        val mes = txtMes.text.toString()
        val anio = txtAnio.text.toString()
        val telefono = txtTelefono.text.toString().trim()

        var valido = true
        valido = validar(R.id.tilNombres, nombres.isNotEmpty(), "Ingrese sus nombres") && valido
        valido = validar(R.id.tilApellidos, apellidos.isNotEmpty(), "Ingrese sus apellidos") && valido
        valido = validar(R.id.tilTipoDocumento, tipoDoc.isNotEmpty(), "Seleccione un tipo") && valido
        valido = validar(
            R.id.tilIdentidad,
            identidad.length == 10 || (tipoDoc != "Cédula" && identidad.isNotEmpty()),
            "La cédula debe tener 10 dígitos"
        ) && valido
        valido = validar(R.id.tilDia, dia.isNotEmpty(), "Requerido") && valido
        valido = validar(R.id.tilMes, mes.isNotEmpty(), "Requerido") && valido
        valido = validar(R.id.tilAnio, anio.isNotEmpty(), "Requerido") && valido
        valido = validar(R.id.tilTelefono, telefono.length >= 7, "Ingrese un teléfono válido") && valido

        if (!valido) return

        val genero = findViewById<RadioButton>(rgGenero.checkedRadioButtonId).text

        val resumen = """
            Nombres: $nombres
            Apellidos: $apellidos
            $tipoDoc: $identidad
            Género: $genero
            Fecha de nacimiento: $dia de $mes de $anio
            Teléfono: $telefono
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Usuario registrado")
            .setMessage(resumen)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun validar(idLayout: Int, condicion: Boolean, mensaje: String): Boolean {
        findViewById<TextInputLayout>(idLayout).error = if (condicion) null else mensaje
        return condicion
    }
}
