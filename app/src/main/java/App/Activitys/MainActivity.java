package App.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.tallerdeconfecciones.R;

import App.Activitys.ViewModels.MainViewModel;
import App.repositories.room.entities.Personas;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    TextView txt_registrar;
    EditText txt_email,txt_pass;
    Button btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);



        txt_email = findViewById(R.id.l_txt_email);
        txt_pass = findViewById(R.id.l_txt_pass);
        txt_registrar = findViewById(R.id.txt_registrar);
        btn_buscar = findViewById(R.id.btn_buscar);


        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Cargando...",Toast.LENGTH_LONG).show();

                ingresar();


            }
        });


        txt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, RegistrarActivity.class));

            }
        });


        mainViewModel.id.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt_email.setText(""+s);
            }
        });

    }

    private void buscarPersonaId() {

        mainViewModel.getPersona(Integer.parseInt(txt_email.getText().toString())).observe(this, new Observer<Personas>() {
            @Override
            public void onChanged(Personas personas) {
                try {

                    txt_registrar.setText(personas.getMessage());

                }catch (Exception e){
                    Log.d("Persona","Error"+e.getMessage());
                }

            }
        });

    }

    private void ingresar(){

        mainViewModel.getPersonaEP(txt_email.getText().toString(), txt_pass.getText().toString()).observe(this, new Observer<Personas>() {
            @Override
            public void onChanged(Personas personas) {
                try {

                    Intent i = new Intent(MainActivity.this, ConfeccionesActivity.class);

                    i.putExtra("cliente", new String[]{personas.id+"",personas.nombre+" "+personas.apellido});

                    startActivity(i);

                }catch (Exception e){

                    Log.d("Persona","Error"+e.getMessage());
                    Toast.makeText(MainActivity.this,"Verifique sus datos",Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        //mainViewModel.id.setValue(txt_id.getText().toString());

    }
}