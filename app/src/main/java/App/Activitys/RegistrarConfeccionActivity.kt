package App.Activitys

import App.Activitys.ViewModels.RegistrarConfeccionViewModel
import App.repositories.room.entities.Ventas
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.proyecto.tallerdeconfecciones.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

class RegistrarConfeccionActivity : AppCompatActivity() {
    var RC_btn_fllegada: ImageButton? = null
    var RC_btn_fsalida: ImageButton? = null
    var RC_btn_registrar: Button? = null
    var autocomplete_empleado: AutoCompleteTextView? = null
    var autocomplete_tipotela: AutoCompleteTextView? = null
    var autocomplete_tipoconfeccion: AutoCompleteTextView? = null
    var arrayAdapterEmpleado: ArrayAdapter<String>? = null
    var arrayAdapterTipoTela: ArrayAdapter<String>? = null
    var arrayAdapterTipoCo: ArrayAdapter<String>? = null
    var RC_txt_cliente: TextInputEditText? = null
    var RC_txt_fllegada: TextInputEditText? = null
    var RC_txt_fsalida: TextInputEditText? = null
    var RC_txt_descripcion: TextInputEditText? = null
    var listaempleados: Array<Array<String>>? = arrayOf(arrayOf("-1"), arrayOf("No hay registro"))
    var listatipoconfeccion: Array<Array<String>>? = arrayOf(arrayOf("-1"), arrayOf("No hay registro"))
    var listatipotela: Array<Array<String>>? = arrayOf(arrayOf("-1"), arrayOf("No hay registro"))
    var cliente: Array<String>? = arrayOf("-1", "No hay registro")
    var descripcion = ""
    var tipodetela = ""
    var tipoconfeccion = ""
    var empleado = ""
    var fechallegada = ""
    var fechasalida = ""
    var registrarViewModel: RegistrarConfeccionViewModel? = null
    var RC_ani_paper: LottieAnimationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_confeccion)
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey("listaempleados")) {
            listaempleados = bundle.getSerializable("listaempleados") as Array<Array<String>>?


            //Log.d("listaVentas ","tamaño del vector "+ listaempleados.length);

            //Log.d("listaVentas ",""+listaempleados[1]);
        }
        if (bundle != null && bundle.containsKey("listatico")) {
            listatipoconfeccion = bundle.getSerializable("listatico") as Array<Array<String>>?
            //Log.d("Tamaño lista empleados ",""+listaempleados[1].length);
        }
        if (bundle != null && bundle.containsKey("listatite")) {
            listatipotela = bundle.getSerializable("listatite") as Array<Array<String>>?
            Log.d("Tamaño lista tite ", "" + listatipotela!![1].size)
        }
        cliente = intent.getStringArrayExtra("cliente")
        init()
        RC_txt_cliente!!.setText(cliente!![1])
        RC_ani_paper!!.visibility = View.GONE
        RC_ani_paper!!.pauseAnimation()
        RC_btn_fllegada!!.setOnClickListener { fechaLlegada() }
        RC_btn_fsalida!!.setOnClickListener { fechaSalida() }
        arrayAdapterEmpleado = ArrayAdapter(this, R.layout.items_empleados, listaempleados!![1])
        arrayAdapterTipoTela = ArrayAdapter(this, R.layout.items_empleados, listatipotela!![1])
        arrayAdapterTipoCo = ArrayAdapter(this, R.layout.items_empleados, listatipoconfeccion!![1])
        autocomplete_empleado!!.setAdapter(arrayAdapterEmpleado)
        autocomplete_tipotela!!.setAdapter(arrayAdapterTipoTela)
        autocomplete_tipoconfeccion!!.setAdapter(arrayAdapterTipoCo)
        autocomplete_empleado!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Log.d("Persona", "Elemento: " + listaempleados!![0][position] + " " + listaempleados!![1][position])
            empleado = "" + listaempleados!![0][position]
            //Toast.makeText(getApplicationContext(), "Elemento: "+listaempleados[0][position]+" "+ listaempleados[1][position], Toast.LENGTH_LONG).show();
        }
        autocomplete_tipotela!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Log.d("Persona", "Elemento: " + listatipotela!![0][position] + " " + listatipotela!![1][position])
            tipodetela = listatipotela!![0][position]

            //Toast.makeText(getApplicationContext(), "Click Se selecciono el elemento: "+ position, Toast.LENGTH_LONG).show();
        }
        autocomplete_tipoconfeccion!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Log.d("Persona", "Elemento: " + listatipoconfeccion!![0][position] + " " + listatipoconfeccion!![1][position])
            tipoconfeccion = listatipoconfeccion!![0][position]

            //Toast.makeText(getApplicationContext(), "Click Se selecciono el elemento: "+ position, Toast.LENGTH_LONG).show();
        }
        registrarViewModel = ViewModelProvider(this).get(RegistrarConfeccionViewModel::class.java)
        RC_btn_registrar!!.setOnClickListener { registrarconfeccion() }
    }

    fun init() {
        RC_txt_cliente = findViewById(R.id.RC_txt_cliente)
        RC_txt_descripcion = findViewById(R.id.RC_txt_descripcion)
        RC_btn_fllegada = findViewById(R.id.RC_btn_fllegada)
        RC_btn_fsalida = findViewById(R.id.RC_btn_fsalida)
        RC_txt_fllegada = findViewById(R.id.RC_txt_fllegada)
        RC_txt_fsalida = findViewById(R.id.RC_txt_fsalida)
        autocomplete_empleado = findViewById(R.id.RC_autoc_empleado)
        autocomplete_tipotela = findViewById(R.id.RC_autoc_tipotela)
        autocomplete_tipoconfeccion = findViewById(R.id.RC_autoc_tipoarreglo)
        RC_btn_registrar = findViewById(R.id.RC_btn_registrar)
        RC_ani_paper = findViewById(R.id.RC_ani_paper)
    }

    private fun registrarconfeccion() {
        RC_ani_paper!!.visibility = View.VISIBLE
        RC_ani_paper!!.playAnimation()
        descripcion = RC_txt_descripcion!!.text.toString()
        fechallegada = RC_txt_fllegada!!.text.toString()
        fechasalida = RC_txt_fsalida!!.text.toString()
        val fechaOriginall = fechallegada
        val fechaOriginals = fechasalida
        val formatoOriginal = "dd-MM-yyyy"
        val formatoDestino = "yyyy-MM-dd"

        // Crear el objeto SimpleDateFormat para el formato original
        val formatoOriginalFecha: DateFormat = SimpleDateFormat(formatoOriginal)

        // Crear el objeto SimpleDateFormat para el formato destino
        val formatoDestinoFecha: DateFormat = SimpleDateFormat(formatoDestino)
        try {
            val fechal = formatoOriginalFecha.parse(fechaOriginall)
            val fechas = formatoOriginalFecha.parse(fechaOriginals)

            // Formatear la fecha en el formato destino
            fechallegada = formatoDestinoFecha.format(fechal)
            fechasalida = formatoDestinoFecha.format(fechas)
        } catch (e: Exception) {
        }
        // Parsear la cadena de fecha en el formato original
        if (descripcion == "" || tipoconfeccion == "" || tipodetela == "" || empleado == "") {
            Toast.makeText(this@RegistrarConfeccionActivity, "Rellene todos los campos...", Toast.LENGTH_LONG).show()
            RC_ani_paper!!.visibility = View.GONE
            RC_ani_paper!!.pauseAnimation()
        } else {
            registrarViewModel!!.setVetas(24, descripcion, fechallegada, fechasalida, tipoconfeccion.toInt(), tipodetela.toInt(), empleado.toInt(), cliente!![0].toInt())!!.observe(this, object : Observer<Ventas?> {
                override fun onChanged(ventas: Ventas?) {
                    try {
                        Log.d("Persona", "Bien " + ventas?.status)
                        Toast.makeText(this@RegistrarConfeccionActivity,
                                RC_txt_descripcion!!.text.toString() + ": " + ventas?.message,
                                Toast.LENGTH_LONG).show()
                        finish()
                    } catch (e: Exception) {
                        Toast.makeText(this@RegistrarConfeccionActivity,
                                "Verifique todos los datos", Toast.LENGTH_LONG).show()
                    }
                    RC_ani_paper!!.visibility = View.GONE
                    RC_ani_paper!!.pauseAnimation()
                }
            })
        }
    }

    private fun fechaLlegada() {
        val calendario = Calendar.getInstance()
        val fechaDialogo = DatePickerDialog(this, { view, year, month, dayOfMonth -> RC_txt_fllegada!!.setText(dayOfMonth.toString() + "-" + (month + 1) + "-" + year) }, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH])
        fechaDialogo.show()
    }

    private fun fechaSalida() {
        val calendario = Calendar.getInstance()
        val fechaDialogo = DatePickerDialog(this, { view, year, month, dayOfMonth -> RC_txt_fsalida!!.setText(dayOfMonth.toString() + "-" + (month + 1) + "-" + year) }, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH])
        fechaDialogo.show()
    }
}