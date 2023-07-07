package App.Activitys

import App.Activitys.ViewModels.RegistrarViewModel
import App.repositories.room.entities.Personas
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.proyecto.tallerdeconfecciones.R

class RegistrarActivity : AppCompatActivity() {
    var r_txt_nombre: EditText? = null
    var r_txt_apellido: EditText? = null
    var r_txt_telefono: EditText? = null
    var r_txt_direccion: EditText? = null
    var r_txt_email: EditText? = null
    var r_txt_password: EditText? = null
    var btn_registrar: Button? = null
    var registrarViewModel: RegistrarViewModel? = null
    var radioGroup: RadioGroup? = null
    var r_radio_hombre: RadioButton? = null
    var r_radio_mujer: RadioButton? = null
    var genero: String? = null
    var RC_ani_paper: LottieAnimationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarp)
        registrarViewModel = ViewModelProvider(this).get(RegistrarViewModel::class.java)
        r_txt_nombre = findViewById(R.id.r_txt_nombre)
        r_txt_apellido = findViewById(R.id.r_txt_apellido)
        r_txt_telefono = findViewById(R.id.r_txt_telefono)
        r_txt_direccion = findViewById(R.id.r_txt_direccion)
        r_txt_email = findViewById(R.id.r_txt_email)
        r_txt_password = findViewById(R.id.r_txt_password)
        radioGroup = findViewById(R.id.r_gruprb)
        r_radio_hombre = findViewById(R.id.r_radio_hombre)
        r_radio_mujer = findViewById(R.id.r_radio_mujer)
        RC_ani_paper = findViewById(R.id.RC_ani_paper)
        RC_ani_paper?.pauseAnimation()
        //RC_ani_paper.setVisibility(View.GONE);
        RC_ani_paper?.setVisibility(View.INVISIBLE)
        btn_registrar = findViewById(R.id.btn_registrar)
        btn_registrar?.setOnClickListener(View.OnClickListener { registrar() })

        //genero = "5";
        r_radio_hombre?.setOnClickListener(View.OnClickListener {
            r_radio_mujer?.setChecked(false)
            genero = "5"
        })
        r_radio_mujer?.setOnClickListener(View.OnClickListener {
            r_radio_hombre?.setChecked(false)
            genero = "6"
        })
    }

    private fun registrar() {
        RC_ani_paper!!.playAnimation()
        RC_ani_paper!!.visibility = View.VISIBLE
        if (r_txt_nombre!!.text.toString() == "" || r_txt_apellido!!.text.toString() == "" || r_txt_telefono!!.text.toString() == "" || r_txt_direccion!!.text.toString() == "" || r_txt_email!!.text.toString() == "" || r_txt_password!!.text.toString() == "" || genero == "") {
            Toast.makeText(this@RegistrarActivity, "Rellene todos los campos...", Toast.LENGTH_LONG).show()
            RC_ani_paper!!.visibility = View.GONE
            RC_ani_paper!!.pauseAnimation()
        } else {
            registrarViewModel!!.setPersonaEP(
                    r_txt_nombre!!.text.toString(),
                    r_txt_apellido!!.text.toString(),
                    r_txt_telefono!!.text.toString(),
                    r_txt_direccion!!.text.toString(),
                    r_txt_email!!.text.toString(),
                    genero,
                    r_txt_password!!.text.toString())!!.observe(this, object : Observer<Personas?> {
                override fun onChanged(personas: Personas?) {
                    RC_ani_paper!!.visibility = View.GONE
                    RC_ani_paper!!.pauseAnimation()
                    try {
                        Log.d("Persona", "Bien " + personas?.status)
                        Toast.makeText(this@RegistrarActivity, r_txt_nombre!!.text.toString() + ": " + personas?.message, Toast.LENGTH_LONG).show()
                        finish()
                    } catch (e: Exception) {
                        Log.d("Persona", "Error" + e.message)
                    }
                }
            })
        }
    }
}