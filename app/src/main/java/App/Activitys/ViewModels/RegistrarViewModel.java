package App.Activitys.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import App.repositories.retrofit.datasource.PersonasRepository;
import App.repositories.room.entities.Personas;

public class RegistrarViewModel extends ViewModel {

    MutableLiveData<String> nombre = new MutableLiveData<>();
    MutableLiveData<String> apellido = new MutableLiveData<>();
    MutableLiveData<String> telefono = new MutableLiveData<>();
    MutableLiveData<String> direccion = new MutableLiveData<>();
    MutableLiveData<String> email = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Personas> persona = new MutableLiveData<>();
    private PersonasRepository personasRepository ;

    public LiveData<Personas> setPersonaEP(String nombre, String apellido, String telefono, String direccion, String email, String tise, String pass) {
        personasRepository = PersonasRepository.getInstance();
        persona = personasRepository.setPersonaListRepository(nombre, apellido, telefono, direccion, email, tise, pass);
        //Log.d("Persona",""+persona.getValue().getMessage());
        Log.d("Persona","nombre: "+nombre);
        return persona;
    }

}
