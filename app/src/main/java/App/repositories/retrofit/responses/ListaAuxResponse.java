package App.repositories.retrofit.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import App.repositories.room.entities.ListaAux;

public class ListaAuxResponse {



    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<ListaAux> listaaux = null;

    public ListaAuxResponse(String status, String message, List<ListaAux> empleados) {
        this.status = status;
        this.message = message;
        this.listaaux = empleados;
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

    public List<ListaAux> getListaAux() {
        return listaaux;
    }

    public void setListaAux(List<ListaAux> listaaux) {
        this.listaaux = listaaux;
    }
}
