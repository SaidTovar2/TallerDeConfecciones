package App.Activitys.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import App.repositories.retrofit.datasource.VentasRepository;
import App.repositories.retrofit.responses.VentasResponse;
import App.repositories.room.entities.ListaAux;
import App.repositories.room.entities.Ventas;

public class ConfeccionesViewModel extends ViewModel {


    MutableLiveData<List<Ventas>> listaVentas = new MutableLiveData<>();

    MutableLiveData<List<ListaAux>> listaempleados = new MutableLiveData<>();
    MutableLiveData<String[][]> mempleados = new MutableLiveData<>();

    MutableLiveData<List<ListaAux>> listatico = new MutableLiveData<>();
    MutableLiveData<String[][]> mtico = new MutableLiveData<>();

    MutableLiveData<List<ListaAux>> listatite = new MutableLiveData<>();
    MutableLiveData<String[][]> mtite = new MutableLiveData<>();
    private MutableLiveData<Ventas> ventasResponse = new MutableLiveData<>();

    private VentasRepository ventasRepository;


    public MutableLiveData<List<Ventas>> getListaVentas(){

        ventasRepository = VentasRepository.getInstance();

        listaVentas = ventasRepository.getListaVentasRepository();

        //Log.d("listaVentas ",""+lis);

        //listaVentas.setValue(ventasResponse.getValue().getVentas());

        /** Imposible usar el objeto mietras se llama en el ViewModel   **/
        //Log.d("Tico",""+listaVentas.getValue().get(1).getFecha_salida());

        return listaVentas;

    }

    public MutableLiveData<Ventas> setDeleteVentas(int ventaId){

        ventasRepository = VentasRepository.getInstance();

        ventasResponse = ventasRepository.setDeleteVentasRepository(ventaId);



        return ventasResponse;

    }

    public MutableLiveData<List<ListaAux>> getListaEmpleados(){

        ventasRepository = VentasRepository.getInstance();

        listaempleados = ventasRepository.getListaEmpleadosRepository();

        return listaempleados;

    }

    public MutableLiveData<List<ListaAux>> getListaTico(){

        ventasRepository = ventasRepository.getInstance();

        listatico = ventasRepository.getListaTicoRepository();

        return listatico;

    }

    public MutableLiveData<List<ListaAux>> getListaTite(){

        ventasRepository = ventasRepository.getInstance();

        listatite = ventasRepository.getListaTiteRepository();

        return listatite;

    }

    public MutableLiveData<String[][]> getMatrixEmpleados() {

        try {

            //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);

            mempleados.setValue(new String[2][listaempleados.getValue().size()]);



            for (int i = 0; i < listaempleados.getValue().size(); i++) {
                ListaAux item = listaempleados.getValue().get(i);

                mempleados.getValue()[0][i] = item.getId();
                mempleados.getValue()[1][i] = item.getNombre();
                //Log.d("empleados ",""+item.getNombre());
            }



        } catch (Exception e){

            Log.d("Error empleados ",""+e.getMessage());

        }

        return mempleados;

    }

    public MutableLiveData<String[][]> getMatrixTico() {

        try {

            //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);

            mtico.setValue(new String[2][listatico.getValue().size()]);

            Log.d("listaticos ","listaticos: "+mtico.getValue().length);
            //Log.d("listaVentas ",""+listaempleados.getValue().get(1).id);

            for (int i = 0; i < listatico.getValue().size(); i++) {
                ListaAux item = listatico.getValue().get(i);

                mtico.getValue()[0][i] = item.getId();
                mtico.getValue()[1][i] = item.getNombre();
                //Log.d("tico ","tico: "+item.getNombre());
            }



        } catch (Exception e){

            Log.d("Error tico matrix",""+e.getMessage());

        }

        return mtico;

    }

    public MutableLiveData<String[][]> getMatrixTite() {

        try {

            //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);

            mtite.setValue(new String[2][listatite.getValue().size()]);

            //Log.d("listaticos ","listaticos: "+mtite.getValue().length);
            //Log.d("listaVentas ",""+listaempleados.getValue().get(1).id);

            for (int i = 0; i < listatite.getValue().size(); i++) {
                ListaAux item = listatite.getValue().get(i);

                mtite.getValue()[0][i] = item.getId();
                mtite.getValue()[1][i] = item.getNombre();
                //Log.d("tite ","tite: "+item.getNombre());
            }



        } catch (Exception e){

            Log.d("Error tico matrix",""+e.getMessage());

        }

        return mtite;

    }

}
