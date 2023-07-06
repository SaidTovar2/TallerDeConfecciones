package App.repositories.retrofit.responses

import App.repositories.room.entities.Personas
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonasResponse(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, personas: List<Personas>?) {

    @SerializedName("data")
    @Expose
    var personas: List<Personas>? = null

    init {
        this.personas = personas
    }
}