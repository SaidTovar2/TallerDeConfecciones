package App.repositories.room.entities

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListaAux(@field:Expose @field:SerializedName("status") var status: String, @field:Expose @field:SerializedName("message") var message: String, id: String, nombre: String) {

    /** The name of the ID column.  */
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    var id = BaseColumns._ID

    @SerializedName("nombre")
    @ColumnInfo(name = "nombre")
    var nombre: String

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
    }
}