package App.Activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.proyecto.tallerdeconfecciones.R;

import App.Activitys.ViewModels.RegistrarViewModel;
import App.repositories.room.entities.Personas;

public class RegistrarActivity extends AppCompatActivity {

    EditText r_txt_nombre, r_txt_apellido, r_txt_telefono, r_txt_direccion, r_txt_email, r_txt_password;
    Button btn_registrar;

    RegistrarViewModel registrarViewModel;

    RadioGroup radioGroup;
    RadioButton r_radio_hombre, r_radio_mujer;
    String genero;

    LottieAnimationView RC_ani_paper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarp);

        registrarViewModel = new ViewModelProvider(this).get(RegistrarViewModel.class);


        r_txt_nombre = findViewById(R.id.r_txt_nombre);
        r_txt_apellido = findViewById(R.id.r_txt_apellido);
        r_txt_telefono = findViewById(R.id.r_txt_telefono);
        r_txt_direccion = findViewById(R.id.r_txt_direccion);
        r_txt_email = findViewById(R.id.r_txt_email);
        r_txt_password = findViewById(R.id.r_txt_password);
        radioGroup = findViewById(R.id.r_gruprb);
        r_radio_hombre = findViewById(R.id.r_radio_hombre);
        r_radio_mujer = findViewById(R.id.r_radio_mujer);

        RC_ani_paper = findViewById(R.id.RC_ani_paper);

        RC_ani_paper.pauseAnimation();
        //RC_ani_paper.setVisibility(View.GONE);
        RC_ani_paper.setVisibility(View.INVISIBLE);


        btn_registrar = findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { registrar(); }
        });

        //genero = "5";

        r_radio_hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r_radio_mujer.setChecked(false);
                genero = "5";

            }
        });

        r_radio_mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r_radio_hombre.setChecked(false);
                genero = "6";

            }
        });


    }

    private void registrar() {

        RC_ani_paper.playAnimation();
        RC_ani_paper.setVisibility(View.VISIBLE);

        if(r_txt_nombre.getText().toString().equals("") || r_txt_apellido.getText().toString().equals("") || r_txt_telefono.getText().toString().equals("") || r_txt_direccion.getText().toString().equals("") || r_txt_email.getText().toString().equals("") || r_txt_password.getText().toString().equals("") || genero.equals("")){

            Toast.makeText(RegistrarActivity.this, "Rellene todos los campos...", Toast.LENGTH_LONG).show();

            RC_ani_paper.setVisibility(View.GONE);
            RC_ani_paper.pauseAnimation();

        }else {

            registrarViewModel.setPersonaEP(
                    r_txt_nombre.getText().toString(),
                    r_txt_apellido.getText().toString(),
                    r_txt_telefono.getText().toString(),
                    r_txt_direccion.getText().toString(),
                    r_txt_email.getText().toString(),
                    genero,
                    r_txt_password.getText().toString()).observe(this, new Observer<Personas>() {
                @Override
                public void onChanged(Personas personas) {

                    RC_ani_paper.setVisibility(View.GONE);
                    RC_ani_paper.pauseAnimation();


                    try {

                        Log.d("Persona", "Bien " + personas.getStatus());
                        Toast.makeText(RegistrarActivity.this, r_txt_nombre.getText().toString() + ": " + personas.getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                    } catch (Exception e) {

                        Log.d("Persona", "Error" + e.getMessage());

                    }

                }
            });



        }


    }
}