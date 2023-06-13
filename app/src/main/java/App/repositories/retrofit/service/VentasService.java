package App.repositories.retrofit.service;

import java.util.Map;

import App.repositories.retrofit.responses.ListaAuxResponse;
import App.repositories.room.entities.Ventas;
import App.repositories.retrofit.apiclient.RetrofitService;
import App.repositories.retrofit.responses.VentasResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VentasService {

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_VENTAS)
    Call<VentasResponse> getVentasService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_VENTAS_CLIENTE)
    Call<VentasResponse> getVentasCService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_VENTAS_DELETE)
    Call<Ventas> deleteVentasService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_VENTAS_UPDATE)
    Call<Ventas> updateVentasService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_VENTA)
    Call<Ventas> setVentaService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_LISTAE)
    Call<ListaAuxResponse> getEmpleadosService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_LISTATT)
    Call<ListaAuxResponse> getTipoTService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_LISTATC)
    Call<ListaAuxResponse> getTipoCService(@Body Map<String, String> params);

}
