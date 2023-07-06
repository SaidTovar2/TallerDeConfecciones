package App.repositories.room.entities

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Usuarios(id: String, email: String, password: String) {
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

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String

    @SerializedName("password")
    @ColumnInfo(name = "password")
    var password: String

    init {
        this.id = id
        this.email = email
        this.password = password
    }
}