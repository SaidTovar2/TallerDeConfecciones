package App.repositories.retrofit.responses

import App.repositories.room.entities.Ventas
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VentasResponse(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, ventas: List<Ventas>?) {

    @SerializedName("data")
    @Expose
    var ventas: List<Ventas>? = null

    init {
        this.ventas = ventas
    }
}