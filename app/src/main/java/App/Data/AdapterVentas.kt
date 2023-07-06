package App.Data

import App.Activitys.CallBack.VentasCallBack
import App.Data.AdapterVentas.ViewHolderVentas
import App.repositories.room.entities.Ventas
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.tallerdeconfecciones.R

class AdapterVentas(var ventasCallBack: VentasCallBack) : RecyclerView.Adapter<ViewHolderVentas>() {
    var listaventa: List<Ventas>? = null
    fun setVentasList(ventas: List<Ventas>?) {
        if (ventas != null) {
            listaventa = ventas
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return if (listaventa != null) {
                        listaventa!!.size
                    } else {
                        0
                    }
                }

                override fun getNewListSize(): Int {
                    return ventas.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return if (listaventa != null) {
                        listaventa!![oldItemPosition].getEmpl_id() === ventas[newItemPosition].getId()
                    } else {
                        false
                    }
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return if (listaventa != null) {
                        listaventa!![oldItemPosition] === ventas[newItemPosition]
                    } else {
                        false
                    }
                }
            }).dispatchUpdatesTo(this)
        } else {
            listaventa = ventas
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVentas {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ventas, null, false)
        return ViewHolderVentas(view)
    }

    override fun onBindViewHolder(holder: ViewHolderVentas, position: Int) {
        holder.asignarDatos(listaventa!![position], ventasCallBack)
    }

    override fun getItemCount(): Int {
        return if (listaventa == null) 0 else listaventa!!.size
    }

    var txt_numerodeventa: TextView? = null
    var linearventas: LinearLayout? = null
    var txt_descripcion: TextView? = null
    var txt_fecha_llegada: TextView? = null
    var txt_fecha_salida: TextView? = null
    var txt_tipoc: TextView? = null
    var txt_tipot: TextView? = null
    var txt_empleado: TextView? = null
    var C_btn_eliminar: ImageButton? = null
    var C_btn_editar: ImageButton? = null

    inner class ViewHolderVentas(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

            //linearventas = itemView.findViewById(R.id.lienar_item_ventas);
            txt_descripcion = itemView.findViewById(R.id.txt_descripcion)
            txt_fecha_llegada = itemView.findViewById(R.id.txt_fecha_llegada)
            txt_fecha_salida = itemView.findViewById(R.id.txt_fecha_salida)
            txt_tipoc = itemView.findViewById(R.id.txt_tipoc)
            txt_tipot = itemView.findViewById(R.id.txt_tipot)
            txt_empleado = itemView.findViewById(R.id.txt_empleado)
            C_btn_eliminar = itemView.findViewById(R.id.C_btn_eliminar)
            C_btn_editar = itemView.findViewById(R.id.C_btn_editar)
            txt_numerodeventa = itemView.findViewById(R.id.txt_numerodeventa)
        }

        fun asignarDatos(ventas: Ventas, ventasCallBack: VentasCallBack) {
            txt_descripcion!!.text = "Descripcion: " + ventas.getDescripcion()
            txt_fecha_llegada!!.text = "Fecha de llegada: " + ventas.getFecha_llegada()
            txt_fecha_salida!!.text = "Fecha de salida: " + ventas.getFecha_salida()
            txt_tipoc!!.text = "Tipo de confeccion o arreglo: " + ventas.getMaes_tico()
            txt_tipot!!.text = "Tipo de tela: " + ventas.getMaes_tite()
            txt_empleado!!.text = "Empleado: " + ventas.getEmpl_id()
            txt_numerodeventa!!.text = "Venta " + ventas.id
            C_btn_editar!!.setOnClickListener {
                Log.d("Elemento: ", ventas.getDescripcion() + " id:" + ventas.getId())
                ventasCallBack.onUpdate(ventas)
            }
            C_btn_eliminar!!.setOnClickListener {
                Log.d("elemento: ", ventas.getDescripcion() + " id: " + ventas.getId())
                ventasCallBack.onDelete(ventas)
            }
        }
    }
}