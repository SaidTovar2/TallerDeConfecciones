package App.Activitys

import App.Activitys.ViewModels.RegistrarConfeccionViewModel
import App.repositories.room.entities.Ventas
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.proyecto.tallerdeconfecciones.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

class EditarConfeccionActivity : AppCompatActivity() {
    var EC_btn_fllegada: ImageButton? = null
    var EC_btn_fsalida: ImageButton? = null
    var EC_btn_actualizar: Button? = null
    var autocomplete_empleado: AutoCompleteTextView? = null
    var autocomplete_tipotela: AutoCompleteTextView? = null
    var autocomplete_tipoconfeccion: AutoCompleteTextView? = null
    var arrayAdapterEmpleado: ArrayAdapter<String>? = null
    var arrayAdapterTipoTela: ArrayAdapter<String>? = null
    var arrayAdapterTipoCo: ArrayAdapter<String>? = null
    var EC_txt_cliente: TextInputEditText? = null
    var EC_txt_fllegada: TextInputEditText? = null
    var EC_txt_fsalida: TextInputEditText? = null
    var EC_txt_descripcion: TextInputEditText? = null
    var listaempleados: Array<Array<String>>? = arrayOf(arrayOf("-1"), arrayOf("No hay registro"))
    var listatipoconfeccion: Array<Array<String>>? = arrayOf(arrayOf("-1"), arrayOf("No hay registro"))
    var listatipotela: Array<Array<String>>? = arrayOf(arrayOf("-1"), arrayOf("No hay registro"))
    var cliente: Array<String>? = arrayOf("-1", "No hay registro")
    var ventaid: String? = null
    var descripcion: String? = null
    var tipodetela: String? = null
    var tipoconfeccion: String? = null
    var empleado: String? = null
    var fechallegada: String? = null
    var fechasalida: String? = null
    var registrarViewModel: RegistrarConfeccionViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_confeccion)
        registrarViewModel = ViewModelProvider(this).get(RegistrarConfeccionViewModel::class.java)
        ventaid = intent.getStringExtra("ventasid").toString()
        descripcion = intent.getStringExtra("descripcion").toString()
        fechallegada = intent.getStringExtra("fecha_llegada").toString()
        fechasalida = intent.getStringExtra("fecha_salida").toString()
        tipoconfeccion = intent.getStringExtra("maes_tico").toString()
        tipodetela = intent.getStringExtra("maes_tite").toString()
        empleado = intent.getStringExtra("empl_id").toString()
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey("listaempleados")) {
            listaempleados = bundle.getSerializable("listaempleados") as Array<Array<String>>?
        }
        if (bundle != null && bundle.containsKey("listatico")) {
            listatipoconfeccion = bundle.getSerializable("listatico") as Array<Array<String>>?
        }
        if (bundle != null && bundle.containsKey("listatite")) {
            listatipotela = bundle.getSerializable("listatite") as Array<Array<String>>?
            Log.d("Tamaño lista tite ", "" + listatipotela!![1].size)
        }
        cliente = intent.getStringArrayExtra("cliente")
        init()
        EC_txt_descripcion!!.setText(descripcion)
        autocomplete_empleado!!.setText(empleado)
        autocomplete_tipotela!!.setText(tipodetela)
        autocomplete_tipoconfeccion!!.setText(tipoconfeccion)
        EC_txt_fllegada!!.setText(formatoFecha(fechallegada!!, "yyyy-MM-dd", "dd-MM-yyyy"))
        EC_txt_fsalida!!.setText(formatoFecha(fechasalida!!, "yyyy-MM-dd", "dd-MM-yyyy"))
        EC_txt_cliente!!.setText(cliente!![1])
        EC_btn_fllegada!!.setOnClickListener { fechaLlegada() }
        EC_btn_fsalida!!.setOnClickListener { fechaSalida() }
        EC_btn_actualizar!!.setOnClickListener { Actualizarventa() }
        arrayAdapterEmpleado = ArrayAdapter(this, R.layout.items_empleados, listaempleados!![1])
        arrayAdapterTipoTela = ArrayAdapter(this, R.layout.items_empleados, listatipotela!![1])
        arrayAdapterTipoCo = ArrayAdapter(this, R.layout.items_empleados, listatipoconfeccion!![1])
        autocomplete_empleado!!.setAdapter(arrayAdapterEmpleado)
        autocomplete_tipotela!!.setAdapter(arrayAdapterTipoTela)
        autocomplete_tipoconfeccion!!.setAdapter(arrayAdapterTipoCo)
        autocomplete_empleado!!.onItemClickListener = OnItemClickListener { parent, view, position, id -> empleado = "" + listaempleados!![0][position] }
        autocomplete_tipotela!!.onItemClickListener = OnItemClickListener { parent, view, position, id -> tipodetela = listatipotela!![0][position] }
        autocomplete_tipoconfeccion!!.onItemClickListener = OnItemClickListener { parent, view, position, id -> tipoconfeccion = listatipoconfeccion!![0][position] }
    }

    private fun valorAuto(tipode: String?, lista: Array<Array<String>>?): Int {
        var resul = -1
        for (i in 0..lista!![1].size) {
            Log.d("vector", "tamaño:" + lista[1].size + " Buscar: " + tipode + "->" + lista[1][i])
            if (tipode == lista[1][i]) {
                /** */
                resul = lista[0][i].toInt()
                break
            }
        }
        return resul
    }

    fun formatoFecha(fecha: String, fechaO: String?, fechaD: String?): String {

        // Crear el objeto SimpleDateFormat para el formato original
        var fecha = fecha
        val formatoOriginalFecha: DateFormat = SimpleDateFormat(fechaO)

        // Crear el objeto SimpleDateFormat para el formato destino
        val formatoDestinoFecha: DateFormat = SimpleDateFormat(fechaD)
        fecha = try {
            val fechal = formatoOriginalFecha.parse(fecha)
            formatoDestinoFecha.format(fechal)
        } catch (e: Exception) {
            Log.d("Error fecha", "no se pudo cambiar el formato de fecha: " + e.message)
            return fecha
        }
        return fecha
    }

    private fun Actualizarventa() {
        descripcion = EC_txt_descripcion!!.text.toString()
        fechallegada = EC_txt_fllegada!!.text.toString()
        fechasalida = EC_txt_fsalida!!.text.toString()
        val formatoOriginal = "dd-MM-yyyy"
        val formatoDestino = "yyyy-MM-dd"
        fechallegada = formatoFecha(fechallegada!!, "dd-MM-yyyy", "yyyy-MM-dd")
        fechasalida = formatoFecha(fechasalida!!, "dd-MM-yyyy", "yyyy-MM-dd")
        if (descripcion == "" || tipoconfeccion == "" || tipodetela == "" || empleado == "") {
            Toast.makeText(this@EditarConfeccionActivity, "Rellene todos los campos...", Toast.LENGTH_LONG).show()
        } else {
            registrarViewModel!!.setUptadeVentas(ventaid!!.toInt(),
                    24,
                    descripcion!!,
                    fechallegada,
                    fechasalida,
                    valorAuto(tipoconfeccion, listatipoconfeccion),
                    valorAuto(tipodetela, listatipotela),
                    valorAuto(empleado, listaempleados), cliente!![0].toInt())!!.observe(this, object : Observer<Ventas?> {
                override fun onChanged(ventas: Ventas?) {
                    Log.d("Persona", "Bien " + ventas?.status)
                    Toast.makeText(this@EditarConfeccionActivity,
                            EC_txt_descripcion!!.text.toString() + ": " + ventas?.message,
                            Toast.LENGTH_LONG).show()
                    finish()
                }
            })
        }
    }

    private fun fechaLlegada() {
        val calendario = Calendar.getInstance()
        val fechaDialogo = DatePickerDialog(this, { view, year, month, dayOfMonth -> EC_txt_fllegada!!.setText(dayOfMonth.toString() + "-" + (month + 1) + "-" + year) }, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH])
        fechaDialogo.show()
    }

    private fun fechaSalida() {
        val calendario = Calendar.getInstance()
        val fechaDialogo = DatePickerDialog(this, { view, year, month, dayOfMonth -> EC_txt_fsalida!!.setText(dayOfMonth.toString() + "-" + (month + 1) + "-" + year) }, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH])
        fechaDialogo.show()
    }

    fun init() {
        EC_txt_cliente = findViewById(R.id.EC_txt_cliente)
        EC_txt_descripcion = findViewById(R.id.EC_txt_descripcion)
        EC_btn_fllegada = findViewById(R.id.EC_btn_fllegada)
        EC_btn_fsalida = findViewById(R.id.EC_btn_fsalida)
        EC_txt_fllegada = findViewById(R.id.EC_txt_fllegada)
        EC_txt_fsalida = findViewById(R.id.EC_txt_fsalida)
        autocomplete_empleado = findViewById(R.id.EC_autoc_empleado)
        autocomplete_tipotela = findViewById(R.id.EC_autoc_tipotela)
        autocomplete_tipoconfeccion = findViewById(R.id.EC_autoc_tipoarreglo)
        EC_btn_actualizar = findViewById(R.id.EC_btn_actualizar)
    }
}