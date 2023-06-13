package App.repositories.retrofit.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static final String IP = "https://tallerdeconfecciones.000webhostapp.com";
    //public static final String IP = "http://172.20.10.2";

    public static final String BACKEND = "/movil-app/";
    public static final String BASE_URL = IP + BACKEND;

    public static final String GET_USUARIOS = BASE_URL + "views/app_get_all_usuarios.php";
    public static final String GET_USUARIO = BASE_URL + "views/app_get_usuarios.php";
    public static final String SET_USUARIOS_DELETE = BASE_URL + "views/app_delete_usuarios.php";
    public static final String SET_USUARIOS_UPDATE = BASE_URL + "views/app_update_usuarios.php";
    public static final String SET_USUARIO = BASE_URL + "views/app_set_usuarios.php";
    public static final String GET_USUARIOEP = BASE_URL + "views/app_get_usuariosemailpss.php";

    public static final String GET_PERSONAS = BASE_URL + "views/app_get_all_usuarios.php";

   /*****************************************************************************************************/

    public static final String GET_VENTAS = BASE_URL + "views/app_get_all_ventas.php";

    public static final String GET_VENTAS_CLIENTE = BASE_URL + "views/app_get_ventas_cliente.php";
    public static final String GET_VENTA = BASE_URL + "views/app_get_usuarios.php";
    public static final String SET_VENTAS_DELETE = BASE_URL + "views/app_delete_ventas.php";
    public static final String SET_VENTAS_UPDATE = BASE_URL + "views/app_update_ventas.php";
    public static final String SET_VENTA = BASE_URL + "views/app_set_ventas.php";

    /*****************************************************************************************************/

    public static final String GET_PERSONA = BASE_URL + "views/app_get_personas.php";
    public static final String SET_PERSONAS_DELETE = BASE_URL + "views/app_delete_usuarios.php";
    public static final String SET_PERSONAS_UPDATE = BASE_URL + "views/app_update_usuarios.php";
    public static final String SET_PERSONA = BASE_URL + "views/app_set_clientes.php";
    public static final String GET_PERSONAEP = BASE_URL + "views/app_get_personasemailpss.php";


    /*****************************************************************************************************/

    public static final String GET_LISTAE = BASE_URL + "views/app_get_all_empleados.php";
    public static final String GET_LISTATT = BASE_URL + "views/app_get_all_tite.php";
    public static final String GET_LISTATC = BASE_URL + "views/app_get_all_tico.php";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
