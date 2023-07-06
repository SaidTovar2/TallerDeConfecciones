package App.Activitys.ViewModels

import App.repositories.retrofit.datasource.VentasRepository
import App.repositories.room.entities.ListaAux
import App.repositories.room.entities.Ventas
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfeccionesViewModel : ViewModel() {
    var listaVentas: MutableLiveData<List<Ventas?>?>? = MutableLiveData()
    var listaempleados: MutableLiveData<List<ListaAux?>?>? = MutableLiveData()
    var mempleados = MutableLiveData<Array<Array<String?>>>()
    var listatico: MutableLiveData<List<ListaAux?>?>? = MutableLiveData()
    var mtico = MutableLiveData<Array<Array<String?>>>()
    var listatite: MutableLiveData<List<ListaAux?>?>? = MutableLiveData()
    var mtite = MutableLiveData<Array<Array<String?>>>()
    private var ventasResponse: MutableLiveData<Ventas?>? = MutableLiveData()
    private var ventasRepository: VentasRepository? = null
    fun getListaVentas(clienteId: Int): MutableLiveData<List<Ventas?>?>? {
        ventasRepository = VentasRepository.Companion.instance
        listaVentas = ventasRepository!!.getListaVentasRepository(clienteId)

        //Log.d("listaVentas ",""+lis);

        //listaVentas.setValue(ventasResponse.getValue().getVentas());
        /** Imposible usar el objeto mietras se llama en el ViewModel    */
        //Log.d("Tico",""+listaVentas.getValue().get(1).getFecha_salida());
        return listaVentas
    }

    fun setDeleteVentas(ventaId: Int): MutableLiveData<Ventas?>? {
        ventasRepository = VentasRepository.Companion.instance
        ventasResponse = ventasRepository!!.setDeleteVentasRepository(ventaId)
        return ventasResponse
    }

    val listaEmpleados: MutableLiveData<List<ListaAux?>?>?
        get() {
            ventasRepository = VentasRepository.Companion.instance
            listaempleados = ventasRepository?.listaEmpleadosRepository
            return listaempleados
        }
    val listaTico: MutableLiveData<List<ListaAux?>?>?
        get() {
            ventasRepository = VentasRepository.Companion.instance
            listatico = ventasRepository?.listaTicoRepository
            return listatico
        }
    val listaTite: MutableLiveData<List<ListaAux?>?>?
        get() {
            ventasRepository = VentasRepository.Companion.instance
            listatite = ventasRepository?.listaTiteRepository
            return listatite
        }//Log.d("empleados ",""+item.getNombre());

    //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);
    val matrixEmpleados: MutableLiveData<Array<Array<String?>>>
        get() {
            try {

                //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);
                mempleados.setValue(Array(2) { arrayOfNulls(listaempleados!!.value!!.size) })
                for (i in listaempleados!!.value!!.indices) {
                    val item = listaempleados!!.value!![i]
                    mempleados.value!![0][i] = item?.id
                    mempleados.value!![1][i] = item?.nombre
                    //Log.d("empleados ",""+item.getNombre());
                }
            } catch (e: Exception) {
                Log.d("Error empleados ", "" + e.message)
            }
            return mempleados
        }//Log.d("tico ","tico: "+item.getNombre());

    //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);
    //Log.d("listaVentas ",""+listaempleados.getValue().get(1).id);
    val matrixTico: MutableLiveData<Array<Array<String?>>>
        get() {
            try {

                //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);
                mtico.setValue(Array(2) { arrayOfNulls(listatico!!.value!!.size) })
                Log.d("listaticos ", "listaticos: " + mtico.value!!.size)
                //Log.d("listaVentas ",""+listaempleados.getValue().get(1).id);
                for (i in listatico!!.value!!.indices) {
                    val item = listatico!!.value!![i]
                    mtico.value!![0][i] = item?.id
                    mtico.value!![1][i] = item?.nombre
                    //Log.d("tico ","tico: "+item.getNombre());
                }
            } catch (e: Exception) {
                Log.d("Error tico matrix", "" + e.message)
            }
            return mtico
        }//Log.d("tite ","tite: "+item.getNombre());
    //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);

    //Log.d("listaticos ","listaticos: "+mtite.getValue().length);
    //Log.d("listaVentas ",""+listaempleados.getValue().get(1).id);
    val matrixTite: MutableLiveData<Array<Array<String?>>>
        get() {
            try {

                //mempleados.setValue(new String[getMatrixEmpleadoss().getValue().size()][2]);
                mtite.setValue(Array(2) { arrayOfNulls(listatite!!.value!!.size) })

                //Log.d("listaticos ","listaticos: "+mtite.getValue().length);
                //Log.d("listaVentas ",""+listaempleados.getValue().get(1).id);
                for (i in listatite!!.value!!.indices) {
                    val item = listatite!!.value!![i]
                    mtite.value!![0][i] = item?.id
                    mtite.value!![1][i] = item?.nombre
                    //Log.d("tite ","tite: "+item.getNombre());
                }
            } catch (e: Exception) {
                Log.d("Error tico matrix", "" + e.message)
            }
            return mtite
        }
}