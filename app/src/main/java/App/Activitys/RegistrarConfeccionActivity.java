package App.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.proyecto.tallerdeconfecciones.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import App.Activitys.ViewModels.RegistrarConfeccionViewModel;
import App.repositories.room.entities.Ventas;

public class RegistrarConfeccionActivity extends AppCompatActivity {


    ImageButton RC_btn_fllegada, RC_btn_fsalida;

    Button RC_btn_registrar;

    AutoCompleteTextView autocomplete_empleado, autocomplete_tipotela, autocomplete_tipoconfeccion;
    ArrayAdapter<String> arrayAdapterEmpleado, arrayAdapterTipoTela, arrayAdapterTipoCo;

    TextInputEditText RC_txt_cliente, RC_txt_fllegada, RC_txt_fsalida, RC_txt_descripcion;

    String[][] listaempleados = {{"-1"}, {"No hay registro"}};
    String[][] listatipoconfeccion = {{"-1"}, {"No hay registro"}};
    String[][] listatipotela = {{"-1"}, {"No hay registro"}};

    String[] cliente = {"-1","No hay registro"};

    String descripcion = "", tipodetela = "", tipoconfeccion = "", empleado = "", fechallegada = "", fechasalida = "";

    RegistrarConfeccionViewModel registrarViewModel;

    LottieAnimationView RC_ani_paper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_confeccion);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("listaempleados")) {

            listaempleados = (String[][]) bundle.getSerializable("listaempleados");



            //Log.d("listaVentas ","tamaño del vector "+ listaempleados.length);

            //Log.d("listaVentas ",""+listaempleados[1]);

        }

        if (bundle != null && bundle.containsKey("listatico")) {

            listatipoconfeccion = (String[][]) bundle.getSerializable("listatico");
            //Log.d("Tamaño lista empleados ",""+listaempleados[1].length);

        }

        if (bundle != null && bundle.containsKey("listatite")) {

            listatipotela = (String[][]) bundle.getSerializable("listatite");
            Log.d("Tamaño lista tite ",""+listatipotela[1].length);

        }

        cliente = getIntent().getStringArrayExtra("cliente");

        init();

        RC_txt_cliente.setText(cliente[1]);

        RC_ani_paper.setVisibility(View.GONE);
        RC_ani_paper.pauseAnimation();


        RC_btn_fllegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fechaLlegada();

            }
        });

        RC_btn_fsalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fechaSalida();

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
                Log.d("Persona","Elemento: "+listaempleados[0][position]+" "+ listaempleados[1][position]);
                empleado = ""+listaempleados[0][position];
                //Toast.makeText(getApplicationContext(), "Elemento: "+listaempleados[0][position]+" "+ listaempleados[1][position], Toast.LENGTH_LONG).show();
            }
        });

        autocomplete_tipotela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Persona","Elemento: "+listatipotela[0][position]+" "+ listatipotela[1][position]);

                tipodetela = listatipotela[0][position];

                //Toast.makeText(getApplicationContext(), "Click Se selecciono el elemento: "+ position, Toast.LENGTH_LONG).show();

            }
        });

        autocomplete_tipoconfeccion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Persona","Elemento: "+listatipoconfeccion[0][position]+" "+ listatipoconfeccion[1][position]);

                tipoconfeccion = listatipoconfeccion[0][position];

                //Toast.makeText(getApplicationContext(), "Click Se selecciono el elemento: "+ position, Toast.LENGTH_LONG).show();

            }
        });



        registrarViewModel = new ViewModelProvider(this).get(RegistrarConfeccionViewModel.class);



        RC_btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarconfeccion();

            }
        });



    }

    public void init(){

        RC_txt_cliente = findViewById(R.id.RC_txt_cliente);
        RC_txt_descripcion = findViewById(R.id.RC_txt_descripcion);

        RC_btn_fllegada = findViewById(R.id.RC_btn_fllegada);
        RC_btn_fsalida = findViewById(R.id.RC_btn_fsalida);

        RC_txt_fllegada = findViewById(R.id.RC_txt_fllegada);
        RC_txt_fsalida = findViewById(R.id.RC_txt_fsalida);

        autocomplete_empleado = findViewById(R.id.RC_autoc_empleado);
        autocomplete_tipotela = findViewById(R.id.RC_autoc_tipotela);
        autocomplete_tipoconfeccion = findViewById(R.id.RC_autoc_tipoarreglo);

        RC_btn_registrar = findViewById(R.id.RC_btn_registrar);

        RC_ani_paper = findViewById(R.id.RC_ani_paper);

    }

    private void registrarconfeccion() {

        RC_ani_paper.setVisibility(View.VISIBLE);
        RC_ani_paper.playAnimation();


        descripcion = RC_txt_descripcion.getText().toString();
        fechallegada = RC_txt_fllegada.getText().toString();
        fechasalida = RC_txt_fsalida.getText().toString();



        String fechaOriginall = fechallegada;
        String fechaOriginals = fechasalida;
        String formatoOriginal = "dd-MM-yyyy";
        String formatoDestino = "yyyy-MM-dd";

        // Crear el objeto SimpleDateFormat para el formato original
        DateFormat formatoOriginalFecha = new SimpleDateFormat(formatoOriginal);

        // Crear el objeto SimpleDateFormat para el formato destino
        DateFormat formatoDestinoFecha = new SimpleDateFormat(formatoDestino);

        try {

            Date fechal = formatoOriginalFecha.parse(fechaOriginall);
            Date fechas = formatoOriginalFecha.parse(fechaOriginals);

            // Formatear la fecha en el formato destino

            fechallegada = formatoDestinoFecha.format(fechal);
            fechasalida  = formatoDestinoFecha.format(fechas);

        }catch (Exception e){

        }
        // Parsear la cadena de fecha en el formato original

        if(descripcion.equals("") || tipoconfeccion.equals("") || tipodetela.equals("") || empleado.equals("")){

            Toast.makeText(RegistrarConfeccionActivity.this, "Rellene todos los campos...", Toast.LENGTH_LONG).show();
            RC_ani_paper.setVisibility(View.GONE);
            RC_ani_paper.pauseAnimation();

        }else {

            registrarViewModel.setVetas(24, descripcion, fechallegada, fechasalida,
                    Integer.parseInt(tipoconfeccion), Integer.parseInt(tipodetela), Integer.parseInt(empleado),
                    Integer.parseInt(cliente[0])).observe(this, new Observer<Ventas>() {

                @Override
                public void onChanged(Ventas ventas) {

                    try {

                        Log.d("Persona","Bien "+ventas.getStatus());
                        Toast.makeText(RegistrarConfeccionActivity.this,
                                RC_txt_descripcion.getText().toString()+": "+ventas.getMessage(),
                                Toast.LENGTH_LONG).show();

                        finish();

                    } catch (Exception e){

                        Toast.makeText(RegistrarConfeccionActivity.this,
                                "Verifique todos los datos", Toast.LENGTH_LONG).show();

                    }

                    RC_ani_paper.setVisibility(View.GONE);
                    RC_ani_paper.pauseAnimation();



                }
            });

        }



    }



    private void fechaLlegada() {

        Calendar calendario = Calendar.getInstance();

        DatePickerDialog fechaDialogo = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                RC_txt_fllegada.setText(dayOfMonth+"-"+(month+1)+"-"+year);

            }
        },calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));

        fechaDialogo.show();
    }

    private void fechaSalida() {

        Calendar calendario = Calendar.getInstance();

        DatePickerDialog fechaDialogo = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                RC_txt_fsalida.setText(dayOfMonth+"-"+(month+1)+"-"+year);

            }
        },calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));

        fechaDialogo.show();

    }

}











