package App.repositories.retrofit.service;

import java.util.List;
import java.util.Map;

import App.repositories.retrofit.apiclient.RetrofitService;
import App.repositories.retrofit.responses.PersonaResponse;
import App.repositories.retrofit.responses.PersonasResponse;
import App.repositories.retrofit.responses.UsuariosResponse;
import App.repositories.room.entities.Personas;
import App.repositories.room.entities.Usuarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PersonasService {

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
    @POST(RetrofitService.SET_PERSONA)
    Call<Personas> setPersonaService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONA)
    Call<Personas> getPersonaService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOEP)
    Call<Usuarios> getUsuarioEPService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONAEP)
    Call<PersonaResponse> getPersonaEPService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONAS)
    Call<PersonasResponse> getPersonasService(@Body Map<String, String> params);

}
