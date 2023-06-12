package App.repositories.room.entities;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ventas {

    /*id maes_esta_conf descripcion fecha_llegada fecha_salida maes_tico maes_tite empl_id clie_id*/

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

    @SerializedName("maes_esta_conf")
    @ColumnInfo(name = "maes_esta_conf")
    public int maes_esta_conf;

    @SerializedName("descripcion")
    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @SerializedName("fecha_llegada")
    @ColumnInfo(name = "fecha_llegada")
    public String fecha_llegada;

    @SerializedName("fecha_salida")
    @ColumnInfo(name = "fecha_salida")
    public String fecha_salida;

    @SerializedName("maes_tico")
    @ColumnInfo(name = "maes_tico")
    public int maes_tico;

    @SerializedName("maes_tite")
    @ColumnInfo(name = "maes_tite")
    public int maes_tite;

    @SerializedName("empl_id")
    @ColumnInfo(name = "empl_id")
    public int empl_id;

    @SerializedName("clie_id")
    @ColumnInfo(name = "clie_id")
    public int clie_id;

    public Ventas(int maes_esta_conf, String descripcion, String fecha_llegada, String fecha_salida, int maes_tico, int maes_tite, int empl_id, int clie_id) {
        this.maes_esta_conf = maes_esta_conf;
        this.descripcion = descripcion;
        this.fecha_llegada = fecha_llegada;
        this.fecha_salida = fecha_salida;
        this.maes_tico = maes_tico;
        this.maes_tite = maes_tite;
        this.empl_id = empl_id;
        this.clie_id = clie_id;
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

    public int getMaes_esta_conf() {
        return maes_esta_conf;
    }

    public void setMaes_esta_conf(int maes_esta_conf) {
        this.maes_esta_conf = maes_esta_conf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getMaes_tico() {
        return maes_tico;
    }

    public void setMaes_tico(int maes_tico) {
        this.maes_tico = maes_tico;
    }

    public int getMaes_tite() {
        return maes_tite;
    }

    public void setMaes_tite(int maes_tite) {
        this.maes_tite = maes_tite;
    }

    public int getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(int empl_id) {
        this.empl_id = empl_id;
    }

    public int getClie_id() {
        return clie_id;
    }

    public void setClie_id(int clie_id) {
        this.clie_id = clie_id;
    }
}
