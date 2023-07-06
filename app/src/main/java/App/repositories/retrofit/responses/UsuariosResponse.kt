package App.repositories.retrofit.responses

import App.repositories.room.entities.Usuarios
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UsuariosResponse(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, usuarios: List<Usuarios>?) {

    @SerializedName("data")
    @Expose
    var usuarios: List<Usuarios>? = null

    init {
        this.usuarios = usuarios
    }
}