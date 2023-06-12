package App.repositories.retrofit.service;

import java.util.Map;

import App.repositories.retrofit.apiclient.RetrofitService;
import App.repositories.retrofit.responses.UsuariosResponse;
import App.repositories.room.entities.Usuarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UsuariosService {

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOS)
    Call<UsuariosResponse> getUsuariosService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_USUARIOS_DELETE)
    Call<UsuariosResponse> deleteUsuariosService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_USUARIOS_UPDATE)
    Call<UsuariosResponse> updateUsuariosService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_USUARIO)
    Call<UsuariosResponse> setUsuarioService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIO)
    Call<Usuarios> getUsuarioService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOEP)
    Call<Usuarios> getUsuarioEPService(@Body Map<String, String> params);
    
}
