package App.Activitys.ViewModels

import App.repositories.retrofit.datasource.VentasRepository
import App.repositories.room.entities.Ventas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrarConfeccionViewModel : ViewModel() {
    private var ventasRepository: VentasRepository? = null
    private var venta: MutableLiveData<Ventas?>? = MutableLiveData()
    fun setVetas(maes_esta_conf: Int, descripcion: String, fecha_llegada: String, fecha_salida: String, maes_tico: Int, maes_tite: Int, empl_id: Int, clie_id: Int): LiveData<Ventas?>? {
        ventasRepository = VentasRepository.Companion.instance
        venta = ventasRepository!!.setVentasRepository(maes_esta_conf, descripcion, fecha_llegada, fecha_salida, maes_tico, maes_tite, empl_id, clie_id)
        return venta
    }

    fun setUptadeVentas(id: Int,
                        maes_esta_conf: Int,
                        descripcion: String,
                        fecha_llegada: String?,
                        fecha_salida: String?,
                        maes_tico: Int,
                        maes_tite: Int,
                        empl_id: Int,
                        clie_id: Int): LiveData<Ventas?>? {
        ventasRepository = VentasRepository.Companion.instance
        venta = ventasRepository!!.setUpdateVentasRepository(id, maes_esta_conf, descripcion, fecha_llegada, fecha_salida, maes_tico, maes_tite, empl_id, clie_id)
        return venta
    } /*
    public LiveData<Personas> setPersonaEP(String nombre, String apellido, String telefono, String direccion, String email, String tise, String pass) {
        personasRepository = PersonasRepository.getInstance();
        persona = personasRepository.setPersonaListRepository(nombre, apellido, telefono, direccion, email, tise, pass);
        //Log.d("Persona",""+persona.getValue().getMessage());
        Log.d("Persona","nombre: "+nombre);
        return persona;
    }
*/
}