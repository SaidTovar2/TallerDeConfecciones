package App.repositories.retrofit.apiclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    const val IP = "https://tallerdeconfecciones.000webhostapp.com"

    //public static final String IP = "http://172.20.10.2";
    const val BACKEND = "/movil-app/"
    const val BASE_URL = IP + BACKEND
    const val GET_USUARIOS = BASE_URL + "views/app_get_all_usuarios.php"
    const val GET_USUARIO = BASE_URL + "views/app_get_usuarios.php"
    const val SET_USUARIOS_DELETE = BASE_URL + "views/app_delete_usuarios.php"
    const val SET_USUARIOS_UPDATE = BASE_URL + "views/app_update_usuarios.php"
    const val SET_USUARIO = BASE_URL + "views/app_set_usuarios.php"
    const val GET_USUARIOEP = BASE_URL + "views/app_get_usuariosemailpss.php"
    const val GET_PERSONAS = BASE_URL + "views/app_get_all_usuarios.php"

    /** */
    const val GET_VENTAS = BASE_URL + "views/app_get_all_ventas.php"
    const val GET_VENTAS_CLIENTE = BASE_URL + "views/app_get_ventas_cliente.php"
    const val GET_VENTA = BASE_URL + "views/app_get_usuarios.php"
    const val SET_VENTAS_DELETE = BASE_URL + "views/app_delete_ventas.php"
    const val SET_VENTAS_UPDATE = BASE_URL + "views/app_update_ventas.php"
    const val SET_VENTA = BASE_URL + "views/app_set_ventas.php"

    /** */
    const val GET_PERSONA = BASE_URL + "views/app_get_personas.php"
    const val SET_PERSONAS_DELETE = BASE_URL + "views/app_delete_usuarios.php"
    const val SET_PERSONAS_UPDATE = BASE_URL + "views/app_update_usuarios.php"
    const val SET_PERSONA = BASE_URL + "views/app_set_clientes.php"
    const val GET_PERSONAEP = BASE_URL + "views/app_get_personasemailpss.php"

    /** */
    const val GET_LISTAE = BASE_URL + "views/app_get_all_empleados.php"
    const val GET_LISTATT = BASE_URL + "views/app_get_all_tite.php"
    const val GET_LISTATC = BASE_URL + "views/app_get_all_tico.php"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}