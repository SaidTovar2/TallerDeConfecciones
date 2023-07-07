package App.Activitys

import App.Activitys.ViewModels.MainViewModel
import App.repositories.room.entities.Personas
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.proyecto.tallerdeconfecciones.R

class MainActivity : AppCompatActivity() {
    var mainViewModel: MainViewModel? = null
    var txt_registrar: TextView? = null
    var txt_email: EditText? = null
    var txt_pass: EditText? = null
    var btn_buscar: Button? = null
    var ani_sewing: LottieAnimationView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        txt_email = findViewById(R.id.l_txt_email)
        txt_pass = findViewById(R.id.l_txt_pass)
        txt_registrar = findViewById(R.id.txt_registrar)
        btn_buscar = findViewById(R.id.btn_buscar)
        ani_sewing = findViewById(R.id.ani_sewing)
        ani_sewing!!.pauseAnimation()
        btn_buscar!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "Cargando...", Toast.LENGTH_LONG).show()
            ingresar()
        })
        txt_registrar!!.setOnClickListener(View.OnClickListener { startActivity(Intent(this@MainActivity, RegistrarActivity::class.java)) })
        mainViewModel!!.id.observe(this, object : Observer<String?> {
            override fun onChanged(s: String?) {
                txt_email?.setText("" + s)
            }
        })
    }

    private fun buscarPersonaId() {
        mainViewModel!!.getPersona(txt_email!!.text.toString().toInt())!!.observe(this, object : Observer<Personas?> {
            override fun onChanged(personas: Personas?) {
                try {
                    txt_registrar?.setText(personas?.message)
                } catch (e: Exception) {
                    Log.d("Persona", "Error" + e.message)
                }
            }
        })
    }

    private fun ingresar() {
        ani_sewing!!.playAnimation()
        mainViewModel!!.getPersonaEP(txt_email!!.text.toString(), txt_pass!!.text.toString())!!.observe(this, object : Observer<Personas?> {
            override fun onChanged(personas: Personas?) {
                ani_sewing!!.pauseAnimation()
                try {
                    val i = Intent(this@MainActivity, ConfeccionesActivity::class.java)
                    i.putExtra("cliente", arrayOf(personas?.id + "", personas?.nombre + " " + personas?.apellido))
                    startActivity(i)
                } catch (e: Exception) {
                    Log.d("Persona", "Error" + e.message)
                    Toast.makeText(this@MainActivity, "Verifique sus datos", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()

        //mainViewModel.id.setValue(txt_id.getText().toString());
    }
}