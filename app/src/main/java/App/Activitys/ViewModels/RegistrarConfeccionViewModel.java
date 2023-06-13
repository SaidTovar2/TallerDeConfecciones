package App.Activitys.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import App.repositories.retrofit.datasource.VentasRepository;
import App.repositories.retrofit.responses.VentasResponse;
import App.repositories.room.entities.Ventas;

public class RegistrarConfeccionViewModel extends ViewModel {

    private VentasRepository ventasRepository ;
    private MutableLiveData<Ventas> venta = new MutableLiveData<>();

    public LiveData<Ventas> setVetas(int maes_esta_conf, String descripcion, String fecha_llegada, String fecha_salida, int maes_tico, int maes_tite, int empl_id, int clie_id){

        ventasRepository = VentasRepository.getInstance();
        venta = ventasRepository.setVentasRepository(maes_esta_conf, descripcion, fecha_llegada, fecha_salida, maes_tico, maes_tite, empl_id, clie_id);

        return venta;

    }

    public LiveData<Ventas> setUptadeVentas(int id,
                                                    int maes_esta_conf,
                                                    String descripcion,
                                                    String fecha_llegada,
                                                    String fecha_salida,
                                                    int maes_tico,
                                                    int maes_tite,
                                                    int empl_id,
                                                    int clie_id){

        ventasRepository = VentasRepository.getInstance();
        venta = ventasRepository.setUpdateVentasRepository(id, maes_esta_conf, descripcion, fecha_llegada, fecha_salida, maes_tico, maes_tite, empl_id, clie_id);

        return venta;

    }

/*
    public LiveData<Personas> setPersonaEP(String nombre, String apellido, String telefono, String direccion, String email, String tise, String pass) {
        personasRepository = PersonasRepository.getInstance();
        persona = personasRepository.setPersonaListRepository(nombre, apellido, telefono, direccion, email, tise, pass);
        //Log.d("Persona",""+persona.getValue().getMessage());
        Log.d("Persona","nombre: "+nombre);
        return persona;
    }
*/
}
