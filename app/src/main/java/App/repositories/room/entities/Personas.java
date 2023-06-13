package App.repositories.room.entities;


import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Personas {

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

    @SerializedName("apellido")
    @ColumnInfo(name = "apellido")
    public String apellido;

    @SerializedName("telefono")
    @ColumnInfo(name = "telefono")
    public Long telefono;

    @SerializedName("direccion")
    @ColumnInfo(name = "direccion")
    public String direccion;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    public String email;

    @SerializedName("tise")
    @ColumnInfo(name = "tise")
    public int tise;

    @SerializedName("password")
    @ColumnInfo(name = "password")
    public String password;

    /*          String nombre;
    String apellido;
    Long telefono;
    String direccion;
    String email;
    int tise;
    String password;      */

    public Personas(String status, String message, String id, String nombre, String apellido, Long telefono, String direccion, String email, int tise, String password) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.tise = tise;
        this.password = password;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTise() {
        return tise;
    }

    public void setTise(int tise) {
        this.tise = tise;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
