package App.repositories.retrofit.responses

import App.repositories.room.entities.ListaAux
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListaAuxResponse(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, empleados: List<ListaAux?>?) {

    @SerializedName("data")
    @Expose
    var listaAux: List<ListaAux?>? = null

    init {
        listaAux = empleados
    }
}