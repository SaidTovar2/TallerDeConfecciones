package App.Activitys.ViewModels

import App.repositories.retrofit.datasource.PersonasRepository
import App.repositories.room.entities.Personas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var user: MutableLiveData<String>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
        private set
    var password: MutableLiveData<String>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
        private set
    var id = MutableLiveData<String?>()

    //public LiveData<String> id = _id;
    var persona: MutableLiveData<Personas?>? = MutableLiveData()
    private var personasRepository: PersonasRepository? = null
    fun getPersona(personaId: Int): LiveData<Personas?>? {
        personasRepository = PersonasRepository.Companion.getInstance()
        persona = personasRepository!!.getPersonaListRepository(personaId)
        return persona
    }

    fun getPersonaEP(email: String, pass: String): LiveData<Personas?>? {
        personasRepository = PersonasRepository.Companion.getInstance()
        persona = personasRepository!!.getPersonaEPListRepository(email, pass)
        return persona
    }
}