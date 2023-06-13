package App.repositories.retrofit.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import App.repositories.retrofit.apiclient.RetrofitService;
import App.repositories.retrofit.responses.ListaAuxResponse;
import App.repositories.retrofit.responses.VentasResponse;
import App.repositories.retrofit.service.VentasService;
import App.repositories.room.entities.ListaAux;
import App.repositories.room.entities.Ventas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VentasRepository {

    private static VentasRepository ventasRepository;
    private VentasService ventasService;

    public VentasRepository() {
        ventasService = RetrofitService.createService(VentasService.class);
    }

    public static VentasRepository getInstance() {
        if (ventasRepository == null) {
            ventasRepository = new VentasRepository();
        }
        return ventasRepository;
    }


    public MutableLiveData<List<Ventas>> getListaVentasRepository(int clienteId) {
        final MutableLiveData<List<Ventas>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        params.put("clienteId", String.valueOf(clienteId));

        Call<VentasResponse> call = ventasService.getVentasCService(params);

        Log.d("response:", "Bien response ventas++");


        call.enqueue(new Callback<VentasResponse>() {
            @Override
            public void onResponse(Call<VentasResponse> call, Response<VentasResponse> response) {

                Log.d("response:", "Bien response ventas");
                Log.d("response:", "" + response.body().getStatus().toString());

                if (response.body().getStatus().equals("success")) {
                    data.setValue(response.body().getVentas());
                } else {
                    data.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<VentasResponse> call, Throwable t) {

                data.setValue(null);

                Log.d("response:", "Mal response ventas");
                Log.d("response:", "" + t.getMessage());


            }
        });

        return data;

    }

    public MutableLiveData<List<ListaAux>> getListaEmpleadosRepository() {
        final MutableLiveData<List<ListaAux>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        Call<ListaAuxResponse> call = ventasService.getEmpleadosService(params);

        //Log.d("empleado:","Bien response empleado++");


        call.enqueue(new Callback<ListaAuxResponse>() {
            @Override
            public void onResponse(Call<ListaAuxResponse> call, Response<ListaAuxResponse> response) {

                if (response.body().getStatus().equals("success")) {

                    data.setValue(response.body().getListaAux());
                    Log.d("response:", "Bien response ventas");

                    Log.d("response:", "" + response.body().getStatus().toString());
                    Log.d("empleado:", "" + response.body().getListaAux().get(1).id);


                } else {
                    data.setValue(null);
                    Log.d("empleado:", "Mal response ventas");
                }

            }

            @Override
            public void onFailure(Call<ListaAuxResponse> call, Throwable t) {

                data.setValue(null);

                Log.d("empleado:", "Mal response ventas");
                Log.d("response:", "" + t.getMessage());


            }
        });

        return data;

    }

    public MutableLiveData<List<ListaAux>> getListaTicoRepository() {
        final MutableLiveData<List<ListaAux>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        Call<ListaAuxResponse> call = ventasService.getTipoCService(params);

        //Log.d("empleado:","Bien response empleado++");


        call.enqueue(new Callback<ListaAuxResponse>() {
            @Override
            public void onResponse(Call<ListaAuxResponse> call, Response<ListaAuxResponse> response) {

                if (response.body().getStatus().equals("success")) {

                    data.setValue(response.body().getListaAux());
                    //Log.d("response:","Bien response ventas");

                    //Log.d("response:",""+response.body().getStatus().toString());
                    //Log.d("tico:",""+response.body().getListaAux().get(1).id);


                } else {
                    data.setValue(null);
                    Log.d("tico:", "Mal response ventas");
                }

            }

            @Override
            public void onFailure(Call<ListaAuxResponse> call, Throwable t) {

                data.setValue(null);

                Log.d("tico:", "Mal response ventas");
                Log.d("response:", "" + t.getMessage());


            }
        });

        return data;

    }

    public MutableLiveData<Ventas> setVentasRepository(int maes_esta_conf,
                                                       String descripcion,
                                                       String fecha_llegada,
                                                       String fecha_salida,
                                                       int maes_tico,
                                                       int maes_tite,
                                                       int empl_id,
                                                       int clie_id) {

        final MutableLiveData<Ventas> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        params.put("maes_esta_conf", String.valueOf(maes_esta_conf));
        params.put("descripcion", String.valueOf(descripcion));
        params.put("fecha_llegada", String.valueOf(fecha_llegada));
        params.put("fecha_salida", String.valueOf(fecha_salida));
        params.put("maes_tico", String.valueOf(maes_tico));
        params.put("maes_tite", String.valueOf(maes_tite));
        params.put("empl_id", String.valueOf(empl_id));
        params.put("clie_id", String.valueOf(clie_id));

        Log.d("response:", "Registro confeccion: " + descripcion);

        Call<Ventas> call = ventasService.setVentaService(params);


        call.enqueue(new Callback<Ventas>() {
            @Override
            public void onResponse(Call<Ventas> call, Response<Ventas> response) {

                Log.d("response:", "Bien response ventas");
                Log.d("response:", "" + response.body().getStatus().toString());
                Log.d("response:", "" + response.body().getMessage().toString());

                if (response.body().getStatus().equals("success")) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<Ventas> call, Throwable t) {

                data.setValue(null);

                Log.d("response:", "Mal response ventas");
                Log.d("response:", "" + t.getMessage());


            }
        });

        return data;

    }

    public MutableLiveData<List<ListaAux>> getListaTiteRepository() {
        final MutableLiveData<List<ListaAux>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        Call<ListaAuxResponse> call = ventasService.getTipoTService(params);

        //Log.d("empleado:","Bien response empleado++");


        call.enqueue(new Callback<ListaAuxResponse>() {
            @Override
            public void onResponse(Call<ListaAuxResponse> call, Response<ListaAuxResponse> response) {

                if (response.body().getStatus().equals("success")) {

                    data.setValue(response.body().getListaAux());
                    //Log.d("response:","Bien response ventas");

                    //Log.d("response:",""+response.body().getStatus().toString());
                    //Log.d("tico:",""+response.body().getListaAux().get(1).id);


                } else {
                    data.setValue(null);
                    Log.d("tite:", "Mal response tite");
                }

            }

            @Override
            public void onFailure(Call<ListaAuxResponse> call, Throwable t) {

                data.setValue(null);

                Log.d("tite:", "Mal response tite");
                Log.d("response:", "" + t.getMessage());


            }
        });

        return data;

    }

    public MutableLiveData<Ventas> setDeleteVentasRepository(int ventasId) {
        final MutableLiveData<Ventas> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        params.put("ventasId", String.valueOf(ventasId));
        Log.d("deleteM", "Id" + ventasId);

        Call<Ventas> call = ventasService.deleteVentasService(params);

        call.enqueue(new Callback<Ventas>() {
            @Override
            public void onResponse(Call<Ventas> call, Response<Ventas> response) {

                Log.d("response:", "Bien response ventas eliminar");
                Log.d("response:","Status: "+response.body().getStatus().toString());

                data.setValue(response.body());



            }

            @Override
            public void onFailure(Call<Ventas> call, Throwable t) {

                data.setValue(null);

                Log.d("response:", "Mal response ventas eliminar");
                Log.d("response:", "Error: " + t.getMessage());
                Log.d("response:", "Error: " + t.getLocalizedMessage());


            }
        });

        return data;

    }


    public MutableLiveData<Ventas> setUpdateVentasRepository(int id, int maes_esta_conf, String descripcion, String fecha_llegada, String fecha_salida, int maes_tico, int maes_tite, int empl_id, int clie_id) {

        final MutableLiveData<Ventas> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();

        params.put("ventasId", String.valueOf(id));
        params.put("maes_esta_conf", String.valueOf(maes_esta_conf));
        params.put("descripcion", String.valueOf(descripcion));
        params.put("fecha_llegada", String.valueOf(fecha_llegada));
        params.put("fecha_salida", String.valueOf(fecha_salida));
        params.put("maes_tico", String.valueOf(maes_tico));
        params.put("maes_tite", String.valueOf(maes_tite));
        params.put("empl_id", String.valueOf(empl_id));
        params.put("clie_id", String.valueOf(clie_id));

        Log.d("Update", "Id" + descripcion);

        Call<Ventas> call = ventasService.updateVentasService(params);

        call.enqueue(new Callback<Ventas>() {
            @Override
            public void onResponse(Call<Ventas> call, Response<Ventas> response) {

                Log.d("response:","Status: "+response.body().getStatus().toString());

                if (response.body().getStatus().equals("success")) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<Ventas> call, Throwable t) {

                data.setValue(null);
                Log.d("response:", "Error: " + t.getMessage());

            }
        });

        return data;

    }
}
