package App.repositories.retrofit.datasource

import App.repositories.retrofit.apiclient.RetrofitService
import App.repositories.retrofit.responses.PersonaResponse
import App.repositories.retrofit.responses.PersonasResponse
import App.repositories.retrofit.service.PersonasService
import App.repositories.room.entities.Personas
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonasRepository {
    private val personasService: PersonasService?

    init {
        personasService = RetrofitService.createService(PersonasService::class.java)
    }

    fun getPersonaListRepository(personaId: Int): MutableLiveData<Personas?> {
        val data = MutableLiveData<Personas?>()
        val params: MutableMap<String, String> = HashMap()
        params["personaId"] = personaId.toString()
        val call = personasService!!.getPersonaService(params)
        call!!.enqueue(object : Callback<Personas?> {
            override fun onResponse(call: Call<Personas?>, response: Response<Personas?>) {
                Log.d("response:", "" + response.body()?.status.toString())
                if (response.body()?.status == "success") {
                    data.setValue(response.body())
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<Personas?>, t: Throwable) {
                data.setValue(null)
            }
        })
        return data
    }

    fun getPersonaEPListRepository(email: String, pass: String): MutableLiveData<Personas?> {
        val data = MutableLiveData<Personas?>()
        val params: MutableMap<String, String> = HashMap()
        params["email"] = email
        params["password"] = pass
        Log.d("response:", "$email $pass")
        val call = personasService!!.getPersonaEPService(params)
        call!!.enqueue(object : Callback<PersonaResponse?> {
            override fun onResponse(call: Call<PersonaResponse?>, response: Response<PersonaResponse?>) {
                Log.d("response:", "" + response.body()?.status.toString())
                if (response.body()?.status == "success") {
                    data.setValue(response.body()?.personas)
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<PersonaResponse?>, t: Throwable) {
                data.setValue(null)
            }
        })
        return data
    }

    val listaPersonasRepository: MutableLiveData<List<Personas>>
        get() {
            val data = MutableLiveData<List<Personas>>()
            val params: Map<String, String> = HashMap()
            val call = personasService!!.getPersonasService(params)
            call!!.enqueue(object : Callback<PersonasResponse?> {
                override fun onResponse(call: Call<PersonasResponse?>, response: Response<PersonasResponse?>) {
                    Log.d("response:", "" + response.body()?.status.toString())
                }

                override fun onFailure(call: Call<PersonasResponse?>, t: Throwable) {}
            })
            return data
        }//ToastUtils.shortToast("E: " + t.getMessage());//ToastUtils.shortToast("R: " + response.body().getMessage());

    //params.put("page", String.valueOf(1));
    val revistasListRepository: MutableLiveData<List<Personas?>?>
        get() {
            val data = MutableLiveData<List<Personas?>?>()
            val params: Map<String, String> = HashMap()
            //params.put("page", String.valueOf(1));
            val call = personasService!!.getPersonasService(params)
            call!!.enqueue(object : Callback<PersonasResponse?> {
                override fun onResponse(call: Call<PersonasResponse?>, response: Response<PersonasResponse?>) {
                    //ToastUtils.shortToast("R: " + response.body().getMessage());
                    if (response.body()?.status == "success") {
                        data.setValue(response.body()?.personas)
                    } else {
                        data.setValue(null)
                    }
                }

                override fun onFailure(call: Call<PersonasResponse?>, t: Throwable) {
                    data.setValue(null)
                    //ToastUtils.shortToast("E: " + t.getMessage());
                }
            })
            return data
        }

    fun setPersonaListRepository(nombre: String, apellido: String, telefono: String, direccion: String, email: String, tise: String?, pass: String): MutableLiveData<Personas?> {
        val data = MutableLiveData<Personas?>()
        val params: MutableMap<String, String> = HashMap()
        params["nombre"] = nombre
        params["apellido"] = apellido
        params["telefono"] = telefono
        params["direccion"] = direccion
        params["email"] = email
        params["tise"] = tise.toString()
        params["password"] = pass
        Log.d("response:", "$nombre $apellido")
        val call = personasService!!.setPersonaService(params)
        call!!.enqueue(object : Callback<Personas?> {
            override fun onResponse(call: Call<Personas?>, response: Response<Personas?>) {

                //Log.d("response:",""+response.body().getStatus().toString());
                Log.d("response:", "" + response.body()?.message.toString())
                if (response.body()?.status == "success") {
                    data.setValue(response.body())
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<Personas?>, t: Throwable) {
                data.setValue(null)
            }
        })
        return data
    } /*

    public MutableLiveData<Usuarios> getUsuarioListRepository(int usuarioId) {
        final MutableLiveData<Usuarios> data = new MutableLiveData<>();


        final Map<String, String> params = new HashMap<>();
        params.put("usuarioId", String.valueOf(usuarioId));

        Call<Personas> call = personasService.getPersonaService(params);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                Log.d("response:",""+response.body().getStatus().toString());

                response.body().getStatus()

                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public MutableLiveData<List<Usuarios>> getUsuariosListRepository() {
        final MutableLiveData<List<Usuarios>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        //params.put("page", String.valueOf(1));

        Call<UsuariosResponse> call = usuariosService.getUsuariosService(params);
        call.enqueue(new Callback<UsuariosResponse>() {
            @Override
            public void onResponse(Call<UsuariosResponse> call, Response<UsuariosResponse> response) {
                //ToastUtils.shortToast("R: " + response.body().getMessage());

                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body().getUsuarios());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<UsuariosResponse> call, Throwable t) {
                data.setValue(null);
                //ToastUtils.shortToast("E: " + t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<UsuariosResponse> deleteUsuariosListRepository(int usuarioId) {
        final MutableLiveData<UsuariosResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("usuarioId", String.valueOf(usuarioId));

        Call<UsuariosResponse> call = usuariosService.deleteUsuariosService(params);
        call.enqueue(new Callback<UsuariosResponse>() {
            @Override
            public void onResponse(Call<UsuariosResponse> call, Response<UsuariosResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<UsuariosResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<UsuariosResponse> updateUsuariosListRepository(String email, String password, int usuarioId) {
        final MutableLiveData<UsuariosResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("email", String.valueOf(email));
        params.put("password", String.valueOf(password));// encriptar*******************************************************************
        params.put("usuarioId", String.valueOf(usuarioId));

        Call<UsuariosResponse> call = usuariosService.updateUsuariosService(params);
        call.enqueue(new Callback<UsuariosResponse>() {
            @Override
            public void onResponse(Call<UsuariosResponse> call, Response<UsuariosResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<UsuariosResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<UsuariosResponse> setUsuariosListRepository(String email, String password) {
        final MutableLiveData<UsuariosResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("email", String.valueOf(email));
        params.put("password", String.valueOf(password));

        Call<UsuariosResponse> call = usuariosService.setUsuarioService(params);
        call.enqueue(new Callback<UsuariosResponse>() {
            @Override
            public void onResponse(Call<UsuariosResponse> call, Response<UsuariosResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<UsuariosResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Usuarios> getUsuarioEPListRepository(String email, String password) {
        final MutableLiveData<Usuarios> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("email", String.valueOf(email));
        params.put("password", String.valueOf(password));

        Call<Usuarios> call = usuariosService.getUsuarioEPService(params);
        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body());
                    Log.d("usuario:"+response.body().email,""+response.body().password);
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


     */

    companion object {
        private var personasRepository: PersonasRepository? = null
        val instance: PersonasRepository?
            get() {
                if (personasRepository == null) {
                    personasRepository = PersonasRepository()
                }
                return personasRepository
            }
    }
}