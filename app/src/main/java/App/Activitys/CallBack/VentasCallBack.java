package App.Activitys.CallBack;

import App.repositories.room.entities.Ventas;

public interface VentasCallBack {

    void onClick(Ventas ventas);

    void onUpdate(Ventas ventas);

    void onDelete(Ventas ventas);

}
