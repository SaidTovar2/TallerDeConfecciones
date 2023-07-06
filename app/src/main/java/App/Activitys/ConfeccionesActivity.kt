package App.Activitys

import App.Activitys.CallBack.VentasCallBack
import App.Activitys.ViewModels.ConfeccionesViewModel
import App.Data.AdapterVentas
import App.repositories.room.entities.ListaAux
import App.repositories.room.entities.Ventas
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.proyecto.tallerdeconfecciones.R

class ConfeccionesActivity : AppCompatActivity() {
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var confeccionesViewModel: ConfeccionesViewModel? = null
    var recyclerConfecciones: RecyclerView? = null
    var btn_addConfeccion: ImageButton? = null
    var activarbtn = booleanArrayOf(false, false, false)
    var i: Intent? = null
    var listMutableLiveData: MutableLiveData<List<Ventas>>? = null
    var clienteid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confecciones)
        confeccionesViewModel = ViewModelProvider(this).get(ConfeccionesViewModel::class.java)
        swipeRefreshLayout = findViewById(R.id.c_swipe)
        recyclerConfecciones = findViewById(R.id.recycler_confecciones)
        i = Intent(this@ConfeccionesActivity, RegistrarConfeccionActivity::class.java)
        i!!.putExtra("listaempleados", null as Array<Array<String?>?>?)
        i!!.putExtra("listatico", null as Array<Array<String?>?>?)
        i!!.putExtra("listatite", null as Array<Array<String?>?>?)
        clienteid = intent.getStringArrayExtra("cliente")!![0]
        i!!.putExtra("cliente", intent.getStringArrayExtra("cliente"))
        init()
        swipeRefreshLayout?.setOnRefreshListener(OnRefreshListener {
            refrescarListaVenta()
            swipeRefreshLayout?.setRefreshing(false)
        })
        btn_addConfeccion = findViewById(R.id.btn_addConfeccion)
        btn_addConfeccion?.setVisibility(View.GONE)
        btn_addConfeccion?.setOnClickListener(View.OnClickListener {
            i!!.setClass(this@ConfeccionesActivity, RegistrarConfeccionActivity::class.java)
            startActivity(i)
        })
    }

    override fun onResume() {
        super.onResume()
        refrescarListaVenta()
        Log.d("resume", "resume")
    }

    fun init() {
        refrescarListaVenta()
        confeccionesViewModel!!.listaEmpleados!!.observe(this, object : Observer<List<ListaAux?>?> {
            override fun onChanged(value: List<ListaAux?>?) {
                matrixdeempleados()
            }
        })
        confeccionesViewModel!!.listaTico!!.observe(this, object : Observer<List<ListaAux?>?> {
            override fun onChanged(value: List<ListaAux?>?) {
                matrixdetico()
            }
        })
        confeccionesViewModel!!.listaTite!!.observe(this, object : Observer<List<ListaAux?>?> {
            override fun onChanged(value: List<ListaAux?>?) {
                matrixdetite()
            }
        })
    }

    fun refrescarListaVenta() {
        val adapterVentas = AdapterVentas(ventasCallBack)
        recyclerConfecciones!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerConfecciones!!.adapter = adapterVentas
        Log.d("Cliente ", "Clie id: $clienteid")

        confeccionesViewModel!!.getListaVentas(clienteid!!.toInt())!!.observe(this, object : Observer<List<Ventas?>?> {
            override fun onChanged(ventas: List<Ventas?>?) {

                //Log.d("Cliente ","Tamaño de la lista: "+ventas.size());
                adapterVentas.setVentasList(ventas as List<Ventas>?)
            }

        })
    }

    private val ventasCallBack: VentasCallBack = object : VentasCallBack {
        override fun onClick(ventas: Ventas?) {}
        override fun onUpdate(ventas: Ventas) {
            refrescarListaVenta()
            ventasUpdate()
            i!!.putExtra("ventasid", ventas.id + "")
            i!!.putExtra("maes_esta_conf", ventas.maes_esta_conf)
            i!!.putExtra("descripcion", ventas.descripcion)
            i!!.putExtra("fecha_llegada", ventas.fecha_llegada)
            i!!.putExtra("fecha_salida", ventas.fecha_salida)
            i!!.putExtra("maes_tico", ventas.maes_tico + "")
            i!!.putExtra("maes_tite", ventas.maes_tite + "")
            i!!.putExtra("empl_id", ventas.empl_id + "")
            i!!.putExtra("clie_id", ventas.clie_id.toString() + "")
            i!!.setClass(this@ConfeccionesActivity, EditarConfeccionActivity::class.java)
            startActivity(i)
        }

        override fun onDelete(ventas: Ventas) {
            Toast.makeText(applicationContext, ventas.descripcion + "", Toast.LENGTH_LONG).show()
            Log.d("Callback", "id: " + ventas.id)
            ventasDelete(ventas.id.toInt())
        }
    }

    fun matrixdeempleados() {


        confeccionesViewModel!!.matrixEmpleados.observe(this, Observer {valueStrings ->

            val bundle = Bundle()
            bundle.putSerializable("listaempleados", valueStrings)
            i!!.putExtras(bundle)
            activarbtn[0] = true
            if (activarbtn[0] && activarbtn[1] && activarbtn[2]) {
                btn_addConfeccion!!.visibility = View.VISIBLE
            }

        })

    }

    fun matrixdetico() {

        confeccionesViewModel!!.matrixTico.observe(this, Observer {valueStrings ->

            val bundle = Bundle()
            bundle.putSerializable("listatico", valueStrings)
            i!!.putExtras(bundle)
            activarbtn[1] = true
            if (activarbtn[0] && activarbtn[1] && activarbtn[2]) {
                btn_addConfeccion!!.visibility = View.VISIBLE
            }

        })

    }

    fun matrixdetite() {

        confeccionesViewModel!!.matrixTite.observe(this, Observer {valueStrings ->

            val bundle = Bundle()
            bundle.putSerializable("listatite", valueStrings)
            i!!.putExtras(bundle)
            activarbtn[2] = true
            if (activarbtn[0] && activarbtn[1] && activarbtn[2]) {
                btn_addConfeccion!!.visibility = View.VISIBLE
            }

        })
    }

    fun ventasUpdate() {}
    fun ventasDelete(ventasid: Int) {
        confeccionesViewModel!!.setDeleteVentas(ventasid)!!.observe(this, object : Observer<Ventas?> {
            override fun onChanged(value: Ventas?) {
                refrescarListaVenta()
            }

        })
    }
}