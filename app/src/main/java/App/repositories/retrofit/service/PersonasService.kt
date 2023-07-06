package App.repositories.retrofit.service

import App.repositories.retrofit.apiclient.RetrofitService
import App.repositories.retrofit.responses.PersonaResponse
import App.repositories.retrofit.responses.PersonasResponse
import App.repositories.retrofit.responses.UsuariosResponse
import App.repositories.room.entities.Personas
import App.repositories.room.entities.Usuarios
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PersonasService {
    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOS)
    fun getUsuariosService(@Body params: Map<String?, String?>?): Call<UsuariosResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_USUARIOS_DELETE)
    fun deleteUsuariosService(@Body params: Map<String?, String?>?): Call<UsuariosResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_USUARIOS_UPDATE)
    fun updateUsuariosService(@Body params: Map<String?, String?>?): Call<UsuariosResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_PERSONA)
    fun setPersonaService(@Body params: Map<String, String>?): Call<Personas?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONA)
    fun getPersonaService(@Body params: Map<String, String>?): Call<Personas?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOEP)
    fun getUsuarioEPService(@Body params: Map<String?, String?>?): Call<Usuarios?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONAEP)
    fun getPersonaEPService(@Body params: Map<String, String>?): Call<PersonaResponse?>?

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONAS)
    fun getPersonasService(@Body params: Map<String, String>?): Call<PersonasResponse?>?
}