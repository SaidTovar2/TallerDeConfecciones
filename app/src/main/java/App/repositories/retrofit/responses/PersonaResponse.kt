package App.repositories.retrofit.responses

import App.repositories.room.entities.Personas
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonaResponse(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, @field:Expose @field:SerializedName("data") var personas: Personas)