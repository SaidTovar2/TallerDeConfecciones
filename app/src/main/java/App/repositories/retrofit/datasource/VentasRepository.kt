package App.repositories.retrofit.datasource

import App.repositories.retrofit.apiclient.RetrofitService
import App.repositories.retrofit.responses.ListaAuxResponse
import App.repositories.retrofit.responses.VentasResponse
import App.repositories.retrofit.service.VentasService
import App.repositories.room.entities.ListaAux
import App.repositories.room.entities.Ventas
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VentasRepository {
    private val ventasService: VentasService?

    init {
        ventasService = RetrofitService.createService(VentasService::class.java)
    }

    fun getListaVentasRepository(clienteId: Int): MutableLiveData<List<Ventas?>?> {
        val data = MutableLiveData<List<Ventas?>?>()
        val params: MutableMap<String, String> = HashMap()
        params["clienteId"] = clienteId.toString()
        val call = ventasService!!.getVentasCService(params)
        Log.d("response:", "Bien response ventas++")

        call!!.enqueue(object : Callback<VentasResponse?> {
            override fun onResponse(
                call: Call<VentasResponse?>,
                response: Response<VentasResponse?>
            ) {
                Log.d("response:", "Bien response ventas")
                Log.d("response:", "" + response.body()!!.status.toString())
                if (response.body()!!.status == "success") {
                    data.setValue(response.body()!!.ventas)
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<VentasResponse?>, t: Throwable) {
                data.setValue(null)
                Log.d("response:", "Mal response ventas")
                Log.d("response:", "" + t.message)
            }
        })
        return data
    }


    //Log.d("empleado:","Bien response empleado++");
    val listaEmpleadosRepository: MutableLiveData<List<ListaAux?>?>
        get() {
            val data = MutableLiveData<List<ListaAux?>?>()
            val params: Map<String, String> = HashMap()
            val call = ventasService!!.getEmpleadosService(params)

            //Log.d("empleado:","Bien response empleado++");
            call!!.enqueue(object : Callback<ListaAuxResponse?> {
                override fun onResponse(call: Call<ListaAuxResponse?>, response: Response<ListaAuxResponse?>) {

                    if (response.body()?.status == "success") {
                        data.setValue(response.body()!!.listaAux)
                        Log.d("response:", "Bien response ventas")
                        Log.d("response:", "" + response.body()!!.status.toString())
                        Log.d("empleado:", "" + response.body()!!.listaAux!!.get(1)!!.id)

                    } else {

                        data.setValue(null)
                        Log.d("empleado:", "Mal response ventas")

                    }
                }

                override fun onFailure(call: Call<ListaAuxResponse?>, t: Throwable) {
                    data.setValue(null)
                    Log.d("empleado:", "Mal response ventas")
                    Log.d("response:", "" + t.message)
                }
            })
            return data
        }


    val listaTicoRepository: MutableLiveData<List<ListaAux?>?>
        get() {
            val data = MutableLiveData<List<ListaAux?>?>()
            val params: Map<String, String> = HashMap()
            val call = ventasService!!.getTipoCService(params)

            //Log.d("empleado:","Bien response empleado++");
            call!!.enqueue(object : Callback<ListaAuxResponse?> {
                override fun onResponse(call: Call<ListaAuxResponse?>, response: Response<ListaAuxResponse?>) {
                    if (response.body()?.status == "success") {
                        data.setValue(response.body()?.listaAux)
                        //Log.d("response:","Bien response ventas");

                        //Log.d("response:",""+response.body().getStatus().toString());
                        //Log.d("tico:",""+response.body().getListaAux().get(1).id);
                    } else {
                        data.setValue(null)
                        Log.d("tico:", "Mal response ventas")
                    }
                }

                override fun onFailure(call: Call<ListaAuxResponse?>, t: Throwable) {
                    data.setValue(null)
                    Log.d("tico:", "Mal response ventas")
                    Log.d("response:", "" + t.message)
                }
            })
            return data
        }

    fun setVentasRepository(maes_esta_conf: Int,
                            descripcion: String,
                            fecha_llegada: String,
                            fecha_salida: String,
                            maes_tico: Int,
                            maes_tite: Int,
                            empl_id: Int,
                            clie_id: Int): MutableLiveData<Ventas?> {
        val data = MutableLiveData<Ventas?>()
        val params: MutableMap<String, String> = HashMap()
        params["maes_esta_conf"] = maes_esta_conf.toString()
        params["descripcion"] = descripcion
        params["fecha_llegada"] = fecha_llegada
        params["fecha_salida"] = fecha_salida
        params["maes_tico"] = maes_tico.toString()
        params["maes_tite"] = maes_tite.toString()
        params["empl_id"] = empl_id.toString()
        params["clie_id"] = clie_id.toString()
        Log.d("response:", "Registro confeccion: $descripcion")
        val call = ventasService!!.setVentaService(params)
        call!!.enqueue(object : Callback<Ventas?> {
            override fun onResponse(call: Call<Ventas?>, response: Response<Ventas?>) {
                Log.d("response:", "Bien response ventas")
                Log.d("response:", "" + response.body()?.status.toString())
                Log.d("response:", "" + response.body()?.message.toString())
                if (response.body()?.status == "success") {
                    data.setValue(response.body())
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<Ventas?>, t: Throwable) {
                data.setValue(null)
                Log.d("response:", "Mal response ventas")
                Log.d("response:", "" + t.message)
            }
        })
        return data
    }


    val listaTiteRepository: MutableLiveData<List<ListaAux?>?>
        get() {
            val data = MutableLiveData<List<ListaAux?>?>()
            val params: Map<String, String> = HashMap()
            val call = ventasService!!.getTipoTService(params)

            //Log.d("empleado:","Bien response empleado++");
            call!!.enqueue(object : Callback<ListaAuxResponse?> {
                override fun onResponse(call: Call<ListaAuxResponse?>, response: Response<ListaAuxResponse?>) {
                    if (response.body()?.status == "success") {
                        data.setValue(response.body()?.listaAux)
                        //Log.d("response:","Bien response ventas");

                        //Log.d("response:",""+response.body().getStatus().toString());
                        //Log.d("tico:",""+response.body().getListaAux().get(1).id);
                    } else {
                        data.setValue(null)
                        Log.d("tite:", "Mal response tite")
                    }
                }

                override fun onFailure(call: Call<ListaAuxResponse?>, t: Throwable) {
                    data.setValue(null)
                    Log.d("tite:", "Mal response tite")
                    Log.d("response:", "" + t.message)
                }
            })
            return data
        }

    fun setDeleteVentasRepository(ventasId: Int): MutableLiveData<Ventas?> {
        val data = MutableLiveData<Ventas?>()
        val params: MutableMap<String, String> = HashMap()
        params["ventasId"] = ventasId.toString()
        Log.d("deleteM", "Id$ventasId")
        val call = ventasService!!.deleteVentasService(params)
        call!!.enqueue(object : Callback<Ventas?> {
            override fun onResponse(call: Call<Ventas?>, response: Response<Ventas?>) {
                Log.d("response:", "Bien response ventas eliminar")
                Log.d("response:", "Status: " + response.body()?.status.toString())
                data.setValue(response.body())
            }

            override fun onFailure(call: Call<Ventas?>, t: Throwable) {
                data.setValue(null)
                Log.d("response:", "Mal response ventas eliminar")
                Log.d("response:", "Error: " + t.message)
                Log.d("response:", "Error: " + t.localizedMessage)
            }
        })
        return data
    }

    fun setUpdateVentasRepository(id: Int, maes_esta_conf: Int, descripcion: String, fecha_llegada: String?, fecha_salida: String?, maes_tico: Int, maes_tite: Int, empl_id: Int, clie_id: Int): MutableLiveData<Ventas?> {
        val data = MutableLiveData<Ventas?>()
        val params: MutableMap<String, String> = HashMap()
        params["ventasId"] = id.toString()
        params["maes_esta_conf"] = maes_esta_conf.toString()
        params["descripcion"] = descripcion
        params["fecha_llegada"] = fecha_llegada.toString()
        params["fecha_salida"] = fecha_salida.toString()
        params["maes_tico"] = maes_tico.toString()
        params["maes_tite"] = maes_tite.toString()
        params["empl_id"] = empl_id.toString()
        params["clie_id"] = clie_id.toString()
        Log.d("Update", "Id$descripcion")

        val call = ventasService!!.updateVentasService(params)

        call!!.enqueue(object : Callback<Ventas?> {
            override fun onResponse(call: Call<Ventas?>, response: Response<Ventas?>) {
                Log.d("response:", "Status: " + response.body()?.status.toString())
                if (response.body()?.status == "success") {
                    data.setValue(response.body())
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<Ventas?>, t: Throwable) {
                data.setValue(null)
                Log.d("response:", "Error: " + t.message)
            }
        })
        return data
    }

    companion object {
        private var ventasRepository: VentasRepository? = null
        val instance: VentasRepository?
            get() {
                if (ventasRepository == null) {
                    ventasRepository = VentasRepository()
                }
                return ventasRepository
            }
    }
}