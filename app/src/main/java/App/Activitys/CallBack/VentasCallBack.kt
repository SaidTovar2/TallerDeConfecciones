package App.Activitys.CallBack

import App.repositories.room.entities.Ventas

interface VentasCallBack {
    fun onClick(ventas: Ventas?)
    fun onUpdate(ventas: Ventas)
    fun onDelete(ventas: Ventas)
}