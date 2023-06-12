package App.repositories.retrofit.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import App.repositories.retrofit.apiclient.RetrofitService;
import App.repositories.retrofit.responses.UsuariosResponse;
import App.repositories.retrofit.service.UsuariosService;
import App.repositories.room.entities.Usuarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosRepository {

    private static UsuariosRepository usuariosRepository;
    private UsuariosService usuariosService;

    public UsuariosRepository(){
        usuariosService = RetrofitService.createService(UsuariosService.class);
    }

    public static UsuariosRepository getInstance(){
        if (usuariosRepository == null){
            usuariosRepository = new UsuariosRepository();
        }
        return usuariosRepository;
    }

    /*
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

    public MutableLiveData<Usuarios> getUsuarioListRepository(int usuarioId) {
        final MutableLiveData<Usuarios> data = new MutableLiveData<>();


        final Map<String, String> params = new HashMap<>();
        params.put("usuarioId", String.valueOf(usuarioId));

        Call<Usuarios> call = usuariosService.getUsuarioService(params);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                Log.d("response:",""+response.body().getStatus().toString());
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
