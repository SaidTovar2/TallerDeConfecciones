package App.repositories.retrofit.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import App.repositories.retrofit.apiclient.RetrofitService;
import App.repositories.retrofit.responses.PersonaResponse;
import App.repositories.retrofit.responses.PersonasResponse;
import App.repositories.retrofit.service.PersonasService;
import App.repositories.room.entities.Personas;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonasRepository {

    private static PersonasRepository personasRepository;
    private PersonasService personasService;

    public PersonasRepository(){
        personasService = RetrofitService.createService(PersonasService.class);
    }

    public static PersonasRepository getInstance(){
        if (personasRepository == null){
            personasRepository = new PersonasRepository();
        }
        return personasRepository;
    }


    public MutableLiveData<Personas> getPersonaListRepository(int personaId) {
        final MutableLiveData<Personas> data = new MutableLiveData<>();


        final Map<String, String> params = new HashMap<>();
        params.put("personaId", String.valueOf(personaId));

        Call<Personas> call = personasService.getPersonaService(params);

        call.enqueue(new Callback<Personas>() {
            @Override
            public void onResponse(Call<Personas> call, Response<Personas> response) {

                Log.d("response:",""+response.body().getStatus().toString());



                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Personas> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public MutableLiveData<Personas> getPersonaEPListRepository(String email, String pass) {
        final MutableLiveData<Personas> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("email", String.valueOf(email));
        params.put("password", String.valueOf(pass));

        Log.d("response:",""+email+" "+pass);

        Call<PersonaResponse> call = personasService.getPersonaEPService(params);

        call.enqueue(new Callback<PersonaResponse>() {
            @Override
            public void onResponse(Call<PersonaResponse> call, Response<PersonaResponse> response) {

                Log.d("response:",""+response.body().getStatus().toString());

                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body().getPersonas());

                }else {
                    data.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<PersonaResponse> call, Throwable t) {

                data.setValue(null);

            }
        });


        return data;

    }

    public MutableLiveData<List<Personas>> getListaPersonasRepository() {
        final MutableLiveData<List<Personas>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        Call<PersonasResponse> call = personasService.getPersonasService(params);

        call.enqueue(new Callback<PersonasResponse>() {
            @Override
            public void onResponse(Call<PersonasResponse> call, Response<PersonasResponse> response) {

                Log.d("response:",""+response.body().getStatus().toString());

            }

            @Override
            public void onFailure(Call<PersonasResponse> call, Throwable t) {

            }
        });

        return data;

    }


    public MutableLiveData<List<Personas>> getRevistasListRepository() {
        final MutableLiveData<List<Personas>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        //params.put("page", String.valueOf(1));

        Call<PersonasResponse> call = personasService.getPersonasService(params);
        call.enqueue(new Callback<PersonasResponse>() {
            @Override
            public void onResponse(Call<PersonasResponse> call, Response<PersonasResponse> response) {
                //ToastUtils.shortToast("R: " + response.body().getMessage());

                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body().getPersonas());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<PersonasResponse> call, Throwable t) {
                data.setValue(null);
                //ToastUtils.shortToast("E: " + t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<Personas> setPersonaListRepository(String nombre, String apellido, String telefono, String direccion, String email, String tise, String pass) {

        final MutableLiveData<Personas> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("nombre", String.valueOf(nombre));
        params.put("apellido", String.valueOf(apellido));
        params.put("telefono", String.valueOf(telefono));
        params.put("direccion", String.valueOf(direccion));
        params.put("email", String.valueOf(email));
        params.put("tise", String.valueOf(tise));
        params.put("password", String.valueOf(pass));

        Log.d("response:",""+nombre+" "+apellido);

        Call<Personas> call = personasService.setPersonaService(params);

        call.enqueue(new Callback<Personas>() {
            @Override
            public void onResponse(Call<Personas> call, Response<Personas> response) {

                //Log.d("response:",""+response.body().getStatus().toString());
                Log.d("response:",""+response.body().getMessage().toString());



                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Personas> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;

    }



    /*

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


}
