package App.repositories.room.entities

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Ventas(@field:ColumnInfo(name = "maes_esta_conf") @field:SerializedName("maes_esta_conf") var maes_esta_conf: Int, @field:ColumnInfo(name = "descripcion") @field:SerializedName("descripcion") var descripcion: String, @field:ColumnInfo(name = "fecha_llegada") @field:SerializedName("fecha_llegada") var fecha_llegada: String, @field:ColumnInfo(name = "fecha_salida") @field:SerializedName("fecha_salida") var fecha_salida: String, @field:ColumnInfo(name = "maes_tico") @field:SerializedName("maes_tico") var maes_tico: String, @field:ColumnInfo(name = "maes_tite") @field:SerializedName("maes_tite") var maes_tite: String, @field:ColumnInfo(name = "empl_id") @field:SerializedName("empl_id") var empl_id: String, @field:ColumnInfo(name = "clie_id") @field:SerializedName("clie_id") var clie_id: Int) {
    /*id maes_esta_conf descripcion fecha_llegada fecha_salida maes_tico maes_tite empl_id clie_id*/
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    /** The name of the ID column.  */
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    var id = BaseColumns._ID

}