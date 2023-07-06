package App.repositories.room.entities

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Personas(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, id: String, nombre: String, apellido: String, telefono: Long, direccion: String, email: String, tise: Int, password: String) {

    /** The name of the ID column.  */
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    var id = BaseColumns._ID

    @SerializedName("nombre")
    @ColumnInfo(name = "nombre")
    var nombre: String

    @SerializedName("apellido")
    @ColumnInfo(name = "apellido")
    var apellido: String

    @SerializedName("telefono")
    @ColumnInfo(name = "telefono")
    var telefono: Long

    @SerializedName("direccion")
    @ColumnInfo(name = "direccion")
    var direccion: String

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String

    @SerializedName("tise")
    @ColumnInfo(name = "tise")
    var tise: Int

    @SerializedName("password")
    @ColumnInfo(name = "password")
    var password: String

    /*          String nombre;
      String apellido;
      Long telefono;
      String direccion;
      String email;
      int tise;
      String password;      */
    init {
        this.id = id
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.direccion = direccion
        this.email = email
        this.tise = tise
        this.password = password
    }
}