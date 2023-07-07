package App.Activitys.ViewModels

import App.repositories.retrofit.datasource.PersonasRepository
import App.repositories.room.entities.Personas
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrarViewModel : ViewModel() {
    var nombre = MutableLiveData<String>()
    var apellido = MutableLiveData<String>()
    var telefono = MutableLiveData<String>()
    var direccion = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private var persona: MutableLiveData<Personas?>? = MutableLiveData()
    private var personasRepository: PersonasRepository? = null
    fun setPersonaEP(nombre: String, apellido: String, telefono: String, direccion: String, email: String, tise: String?, pass: String): LiveData<Personas?>? {
        personasRepository = PersonasRepository.Companion.instance
        persona = personasRepository!!.setPersonaListRepository(nombre, apellido, telefono, direccion, email, tise, pass)
        //Log.d("Persona",""+persona.getValue().getMessage());
        Log.d("Persona", "nombre: $nombre")
        return persona
    }
}