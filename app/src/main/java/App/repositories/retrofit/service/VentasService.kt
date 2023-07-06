package App.repositories.retrofit.service

import App.repositories.retrofit.apiclient.RetrofitService
import App.repositories.retrofit.responses.ListaAuxResponse
import App.repositories.retrofit.responses.VentasResponse
import App.repositories.room.entities.Ventas
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface VentasService {
    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_VENTAS)
    fun getVentasService(@Body params: Map<String?, String?>?): Call<VentasResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_VENTAS_CLIENTE)
    fun getVentasCService(@Body params: Map<String, String>?): Call<VentasResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_VENTAS_DELETE)
    fun deleteVentasService(@Body params: Map<String, String>?): Call<Ventas?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_VENTAS_UPDATE)
    fun updateVentasService(@Body params: Map<String, String>?): Call<Ventas?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_VENTA)
    fun setVentaService(@Body params: Map<String, String>?): Call<Ventas?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_LISTAE)
    fun getEmpleadosService(@Body params: Map<String, String>?): Call<ListaAuxResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_LISTATT)
    fun getTipoTService(@Body params: Map<String, String>?): Call<ListaAuxResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_LISTATC)
    fun getTipoCService(@Body params: Map<String, String>?): Call<ListaAuxResponse?>?
}