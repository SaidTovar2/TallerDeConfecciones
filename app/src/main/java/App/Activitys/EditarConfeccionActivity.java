package App.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.proyecto.tallerdeconfecciones.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import App.Activitys.ViewModels.RegistrarConfeccionViewModel;
import App.repositories.room.entities.Ventas;

public class EditarConfeccionActivity extends AppCompatActivity {

    ImageButton EC_btn_fllegada, EC_btn_fsalida;

    Button EC_btn_actualizar;

    AutoCompleteTextView autocomplete_empleado, autocomplete_tipotela, autocomplete_tipoconfeccion;
    ArrayAdapter<String> arrayAdapterEmpleado, arrayAdapterTipoTela, arrayAdapterTipoCo;

    TextInputEditText EC_txt_cliente, EC_txt_fllegada, EC_txt_fsalida, EC_txt_descripcion;

    String[][] listaempleados = {{"-1"}, {"No hay registro"}};
    String[][] listatipoconfeccion = {{"-1"}, {"No hay registro"}};
    String[][] listatipotela = {{"-1"}, {"No hay registro"}};

    String[] cliente = {"-1","No hay registro"};

    String  ventaid, descripcion, tipodetela, tipoconfeccion, empleado, fechallegada, fechasalida;

    RegistrarConfeccionViewModel registrarViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_confeccion);

        registrarViewModel = new ViewModelProvider(this).get(RegistrarConfeccionViewModel.class);

        ventaid = String.valueOf(getIntent().getStringExtra("ventasid"));
        descripcion = String.valueOf(getIntent().getStringExtra("descripcion"));
        fechallegada = String.valueOf(getIntent().getStringExtra("fecha_llegada"));
        fechasalida = String.valueOf(getIntent().getStringExtra("fecha_salida"));
        tipoconfeccion = String.valueOf(getIntent().getStringExtra("maes_tico"));
        tipodetela = String.valueOf(getIntent().getStringExtra("maes_tite"));
        empleado = String.valueOf(getIntent().getStringExtra("empl_id"));

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("listaempleados")) {

            listaempleados = (String[][]) bundle.getSerializable("listaempleados");

        }

        if (bundle != null && bundle.containsKey("listatico")) {

            listatipoconfeccion = (String[][]) bundle.getSerializable("listatico");

        }

        if (bundle != null && bundle.containsKey("listatite")) {

            listatipotela = (String[][]) bundle.getSerializable("listatite");
            Log.d("Tamaño lista tite ",""+listatipotela[1].length);

        }

        cliente = getIntent().getStringArrayExtra("cliente");

        init();

        EC_txt_descripcion.setText(descripcion);
        autocomplete_empleado.setText(llenarAuto(empleado, listaempleados));
        autocomplete_tipotela.setText(llenarAuto(tipodetela, listatipotela));
        autocomplete_tipoconfeccion.setText(llenarAuto(tipoconfeccion, listatipoconfeccion));
        EC_txt_fllegada.setText(formatoFecha(fechallegada, "yyyy-MM-dd","dd-MM-yyyy"));
        EC_txt_fsalida.setText(formatoFecha(fechasalida, "yyyy-MM-dd","dd-MM-yyyy"));

        EC_txt_cliente.setText(cliente[1]);

        EC_btn_fllegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fechaLlegada();

            }
        });

        EC_btn_fsalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fechaSalida();

            }
        });

        EC_btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Actualizarventa();

            }
        });


        arrayAdapterEmpleado = new ArrayAdapter<String>(this,R.layout.items_empleados, listaempleados[1]);
        arrayAdapterTipoTela = new ArrayAdapter<String>(this, R.layout.items_empleados, listatipotela[1]);
        arrayAdapterTipoCo = new ArrayAdapter<String>(this, R.layout.items_empleados, listatipoconfeccion[1]);

        autocomplete_empleado.setAdapter(arrayAdapterEmpleado);
        autocomplete_tipotela.setAdapter(arrayAdapterTipoTela);
        autocomplete_tipoconfeccion.setAdapter(arrayAdapterTipoCo);


        autocomplete_empleado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                empleado = ""+listaempleados[0][position];

            }
        });

        autocomplete_tipotela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tipodetela = listatipotela[0][position];

            }
        });

        autocomplete_tipoconfeccion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tipoconfeccion = listatipoconfeccion[0][position];

            }
        });



    }

    private String llenarAuto(String tipode, String[][] lista) {

        String resul = "***";

        for (int i=0; i<=lista[1].length; i++){

            if(tipode.equals(lista[0][i])){

                resul = lista[1][i];
                break;

            }

        }

        return resul;

    }

    public String formatoFecha(String fecha, String fechaO, String fechaD){

        // Crear el objeto SimpleDateFormat para el formato original
        DateFormat formatoOriginalFecha = new SimpleDateFormat(fechaO);

        // Crear el objeto SimpleDateFormat para el formato destino
        DateFormat formatoDestinoFecha = new SimpleDateFormat(fechaD);

        try {

            Date fechal = formatoOriginalFecha.parse(fecha);

            fecha = formatoDestinoFecha.format(fechal);

        }catch (Exception e){

            Log.d("Error fecha","no se pudo cambiar el formato de fecha: "+e.getMessage());
            return fecha;

        }

        return fecha;
    }



    private void Actualizarventa() {

        descripcion = EC_txt_descripcion.getText().toString();
        fechallegada = EC_txt_fllegada.getText().toString();
        fechasalida = EC_txt_fsalida.getText().toString();

        String formatoOriginal = "dd-MM-yyyy";
        String formatoDestino = "yyyy-MM-dd";

        fechallegada = formatoFecha(fechallegada, "dd-MM-yyyy", "yyyy-MM-dd");
        fechasalida = formatoFecha(fechasalida, "dd-MM-yyyy", "yyyy-MM-dd");

        registrarViewModel.setUptadeVentas(Integer.parseInt(ventaid),
                                                        24,
                                                        descripcion,
                                                        fechallegada,
                                                        fechasalida,
                                                        Integer.parseInt(tipoconfeccion),
                                                        Integer.parseInt(tipodetela),
                                                        Integer.parseInt(empleado),
                                                        Integer.parseInt(cliente[0])).observe(this, new Observer<Ventas>() {

            @Override
            public void onChanged(Ventas ventas) {

                Log.d("Persona","Bien "+ventas.getStatus());
                Toast.makeText(EditarConfeccionActivity.this,
                        EC_txt_descripcion.getText().toString()+": "+ventas.getMessage(),
                        Toast.LENGTH_LONG).show();

                finish();

            }

        });

    }

    private void fechaLlegada() {

        Calendar calendario = Calendar.getInstance();

        DatePickerDialog fechaDialogo = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                EC_txt_fllegada.setText(dayOfMonth+"-"+(month+1)+"-"+year);

            }
        },calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));

        fechaDialogo.show();
    }

    private void fechaSalida() {

        Calendar calendario = Calendar.getInstance();

        DatePickerDialog fechaDialogo = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                EC_txt_fsalida.setText(dayOfMonth+"-"+(month+1)+"-"+year);

            }
        },calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));

        fechaDialogo.show();

    }

    public void init(){

        EC_txt_cliente = findViewById(R.id.EC_txt_cliente);
        EC_txt_descripcion = findViewById(R.id.EC_txt_descripcion);

        EC_btn_fllegada = findViewById(R.id.EC_btn_fllegada);
        EC_btn_fsalida = findViewById(R.id.EC_btn_fsalida);

        EC_txt_fllegada = findViewById(R.id.EC_txt_fllegada);
        EC_txt_fsalida = findViewById(R.id.EC_txt_fsalida);

        autocomplete_empleado = findViewById(R.id.EC_autoc_empleado);
        autocomplete_tipotela = findViewById(R.id.EC_autoc_tipotela);
        autocomplete_tipoconfeccion = findViewById(R.id.EC_autoc_tipoarreglo);

        EC_btn_actualizar = findViewById(R.id.EC_btn_actualizar);

    }
}