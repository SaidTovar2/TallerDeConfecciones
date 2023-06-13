package App.Activitys.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import App.repositories.retrofit.datasource.PersonasRepository;
import App.repositories.retrofit.datasource.UsuariosRepository;
import App.repositories.room.entities.Personas;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> user;
    private MutableLiveData<String> password;

    public MutableLiveData<String> id = new MutableLiveData<>();
    //public LiveData<String> id = _id;

    public MutableLiveData<Personas> persona = new MutableLiveData<>();

    private PersonasRepository personasRepository;

    public LiveData<Personas> getPersona(int personaId) {
        personasRepository = PersonasRepository.getInstance();
        persona = personasRepository.getPersonaListRepository(personaId);

        return persona;
    }

    public LiveData<Personas> getPersonaEP(String email, String pass) {
        personasRepository = PersonasRepository.getInstance();
        persona = personasRepository.getPersonaEPListRepository(email, pass);

        return persona;
    }


    public MutableLiveData<String> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
        }
        return user;
    }

    public MutableLiveData<String> getPassword() {
        if (password == null) {
            password = new MutableLiveData<>();
        }
        return password;
    }

}

