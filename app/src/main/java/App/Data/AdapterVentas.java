package App.Data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tallerdeconfecciones.R;

import java.util.List;

import App.Activitys.CallBack.VentasCallBack;
import App.repositories.room.entities.Ventas;

public class AdapterVentas extends RecyclerView.Adapter<AdapterVentas.ViewHolderVentas> {

    List<? extends Ventas> listaventa;

    @NonNull
    VentasCallBack ventasCallBack;

    public AdapterVentas(@NonNull VentasCallBack ventasCallBack) {
        this.ventasCallBack = ventasCallBack;
    }


    public void setVentasList(final List<? extends Ventas> ventas) {
        if (this.listaventa == null) {

            this.listaventa = ventas;

            notifyItemRangeInserted(0, ventas.size());
        } else {
            this.listaventa = ventas;
        }

    }

    @NonNull
    @Override
    public ViewHolderVentas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ventas, null, false);


        return new ViewHolderVentas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVentas holder, int position) {

        holder.asignarDatos(listaventa.get(position), ventasCallBack);


    }

    @Override
    public int getItemCount() {
        return listaventa == null ? 0 : listaventa.size();
    }

    TextView txt_numerodeventa;

    LinearLayout linearventas;
    TextView txt_descripcion;
    TextView txt_fecha_llegada;
    TextView txt_fecha_salida;
    TextView txt_tipoc;
    TextView txt_tipot;
    TextView txt_empleado;
    ImageButton C_btn_eliminar, C_btn_editar;


    public class ViewHolderVentas extends RecyclerView.ViewHolder {
        public ViewHolderVentas(@NonNull View itemView) {
            super(itemView);

            //linearventas = itemView.findViewById(R.id.lienar_item_ventas);
            txt_descripcion = itemView.findViewById(R.id.txt_descripcion);
            txt_fecha_llegada = itemView.findViewById(R.id.txt_fecha_llegada);
            txt_fecha_salida = itemView.findViewById(R.id.txt_fecha_salida);
            txt_tipoc = itemView.findViewById(R.id.txt_tipoc);
            txt_tipot = itemView.findViewById(R.id.txt_tipot);
            txt_empleado = itemView.findViewById(R.id.txt_empleado);
            C_btn_eliminar = itemView.findViewById(R.id.C_btn_eliminar);
            C_btn_editar = itemView.findViewById(R.id.C_btn_editar);
            txt_numerodeventa = itemView.findViewById(R.id.txt_numerodeventa);

        }

        public void asignarDatos(Ventas ventas, VentasCallBack ventasCallBack) {


            txt_descripcion.setText("Descripcion: " + ventas.getDescripcion());
            txt_fecha_llegada.setText("Fecha de llegada: " + ventas.getFecha_llegada());
            txt_fecha_salida.setText("Fecha de salida: " + ventas.getFecha_salida());
            txt_tipoc.setText("Tipo de confeccion o arreglo: " + ventas.getMaes_tico());
            txt_tipot.setText("Tipo de tela: " + ventas.getMaes_tite());
            txt_empleado.setText("Empleado: " + ventas.getEmpl_id());
            txt_numerodeventa.setText("Venta " + ventas.id);

            C_btn_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("Elemento: ", ventas.getDescripcion() + " id:" + ventas.getId());
                    ventasCallBack.onUpdate(ventas);

                }
            });

            C_btn_eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("elemento: ", ventas.getDescripcion() + " id: " + ventas.getId());
                    ventasCallBack.onDelete(ventas);

                }
            });

        }
    }
}
