package App.repositories.room.entities;


import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaAux {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    /** The name of the ID column. */
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    public String id = BaseColumns._ID;

    @SerializedName("nombre")
    @ColumnInfo(name = "nombre")
    public String nombre;


    /*          String nombre;
    String apellido;
    Long telefono;
    String direccion;
    String email;
    int tise;
    String password;      */

    public ListaAux(String status, String message, String id, String nombre) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.nombre = nombre;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
