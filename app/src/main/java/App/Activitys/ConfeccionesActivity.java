package App.Activitys;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.proyecto.tallerdeconfecciones.R;

import java.util.List;

import App.Activitys.CallBack.VentasCallBack;
import App.Data.AdapterVentas;
import App.Activitys.ViewModels.ConfeccionesViewModel;
import App.repositories.room.entities.ListaAux;
import App.repositories.room.entities.Ventas;

public class ConfeccionesActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ConfeccionesViewModel confeccionesViewModel;

    RecyclerView recyclerConfecciones;

    ImageButton btn_addConfeccion;

    boolean[] activarbtn = {false, false, false};

    Intent i;

    MutableLiveData<List<Ventas>> listMutableLiveData;

    String clienteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confecciones);

        confeccionesViewModel = new ViewModelProvider(this).get(ConfeccionesViewModel.class);

        swipeRefreshLayout = findViewById(R.id.c_swipe);
        recyclerConfecciones = findViewById(R.id.recycler_confecciones);

        i = new Intent(ConfeccionesActivity.this, RegistrarConfeccionActivity.class);
        i.putExtra("listaempleados", (String[][]) null);
        i.putExtra("listatico", (String[][]) null);
        i.putExtra("listatite", (String[][]) null);
        clienteid = getIntent().getStringArrayExtra("cliente")[0];
        i.putExtra("cliente", getIntent().getStringArrayExtra("cliente"));

        init();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refrescarListaVenta();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        btn_addConfeccion = findViewById(R.id.btn_addConfeccion);

        btn_addConfeccion.setVisibility(View.GONE);

        btn_addConfeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i.setClass(ConfeccionesActivity.this, RegistrarConfeccionActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        refrescarListaVenta();
        Log.d("resume","resume");
    }

    public void init(){

        refrescarListaVenta();

        confeccionesViewModel.getListaEmpleados().observe(this, new Observer<List<ListaAux>>() {
            @Override
            public void onChanged(List<ListaAux> listaAuxes) {

                matrixdeempleados();

            }
        });

        confeccionesViewModel.getListaTico().observe(this, new Observer<List<ListaAux>>() {
            @Override
            public void onChanged(List<ListaAux> listaAuxes) {

                matrixdetico();

            }
        });

        confeccionesViewModel.getListaTite().observe(this, new Observer<List<ListaAux>>() {
            @Override
            public void onChanged(List<ListaAux> listaAuxes) {

                matrixdetite();

            }
        });



    }


    public void refrescarListaVenta(){

        AdapterVentas adapterVentas = new AdapterVentas(ventasCallBack);

        recyclerConfecciones.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerConfecciones.setAdapter(adapterVentas);

        confeccionesViewModel.getListaVentas(Integer.parseInt(clienteid)).observe(this, new Observer<List<Ventas>>() {
            @Override
            public void onChanged(List<Ventas> ventas) {

                adapterVentas.setVentasList(ventas);

            }
        });
    }

    private final VentasCallBack ventasCallBack = new VentasCallBack() {
        @Override
        public void onClick(Ventas ventas) {

        }

        @Override
        public void onUpdate(Ventas ventas) {

            refrescarListaVenta();
            ventasUpdate();

            i.putExtra("ventasid", ventas.id+"");
            i.putExtra("maes_esta_conf", ventas.maes_esta_conf);
            i.putExtra("descripcion", ventas.descripcion);
            i.putExtra("fecha_llegada", ventas.fecha_llegada);
            i.putExtra("fecha_salida", ventas.fecha_salida);
            i.putExtra("maes_tico", ventas.maes_tico+"");
            i.putExtra("maes_tite", ventas.maes_tite+"");
            i.putExtra("empl_id", ventas.empl_id+"");
            i.putExtra("clie_id", ventas.clie_id+"");

            i.setClass(ConfeccionesActivity.this, EditarConfeccionActivity.class);
            startActivity(i);

        }

        @Override
        public void onDelete(Ventas ventas) {

            Toast.makeText(getApplicationContext(),ventas.getDescripcion()+"",Toast.LENGTH_LONG).show();
            Log.d("Callback", "id: "+ventas.id);
            ventasDelete(Integer.parseInt(ventas.id));

        }
    };

    public void matrixdeempleados(){

        confeccionesViewModel.getMatrixEmpleados().observe(this, new Observer<String[][]>() {
            @Override
            public void onChanged(String[][] strings) {


                Bundle bundle = new Bundle();
                bundle.putSerializable("listaempleados", strings);
                i.putExtras(bundle);

                activarbtn[0] = true;

                if (activarbtn[0] && activarbtn[1] && activarbtn[2]){

                    btn_addConfeccion.setVisibility(View.VISIBLE);

                }

            }
        });

    }

    public void matrixdetico(){

        confeccionesViewModel.getMatrixTico().observe(this, new Observer<String[][]>() {
            @Override
            public void onChanged(String[][] strings) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("listatico", strings);
                i.putExtras(bundle);

                activarbtn[1] = true;

                if (activarbtn[0] && activarbtn[1] && activarbtn[2]){

                    btn_addConfeccion.setVisibility(View.VISIBLE);

                }


            }
        });

    }

    public void matrixdetite(){

        confeccionesViewModel.getMatrixTite().observe(this, new Observer<String[][]>() {
            @Override
            public void onChanged(String[][] strings) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("listatite", strings);
                i.putExtras(bundle);

                activarbtn[2] = true;

                if (activarbtn[0] && activarbtn[1] && activarbtn[2]){

                    btn_addConfeccion.setVisibility(View.VISIBLE);

                }

            }
        });

    }

    public void ventasUpdate(){

    }

    public void ventasDelete(int ventasid){

        confeccionesViewModel.setDeleteVentas(ventasid).observe(this, new Observer<Ventas>() {
            @Override
            public void onChanged(Ventas ventas) {

                refrescarListaVenta();

            }
        });

    }


}