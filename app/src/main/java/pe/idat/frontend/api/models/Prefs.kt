package pe.idat.frontend.api.models

import android.content.Context

class Prefs (val context:Context){
    val SHARED_DB = "GYMDB"
    val SHARED_NOMBRE = "nombre"
    val SHARED_DIRECTION = "direction"
    val SHARED_PRICE = "price"
    val SHARED_APELLIDOS = "apellidos"
    val SHARED_DESCRIPCION = "descripcion"
    val SHARED_UUID = "uuid"
    val SHARED_ID = "id"
    val SHARED_EMAIL = "email"

    val storage = context.getSharedPreferences(SHARED_DB, 0)

    fun setNombre(nombre:String){
        storage.edit().putString(SHARED_NOMBRE, nombre).apply()
    }

    fun getNombre():String{
        return storage.getString(SHARED_NOMBRE, "")!!
    }

    fun setDirection(direction:String){
        storage.edit().putString(SHARED_DIRECTION, direction).apply()
    }

    fun getDirection():String{
        return storage.getString(SHARED_DIRECTION, "")!!
    }

    fun setPrice(price:Float){
        storage.edit().putFloat(SHARED_PRICE, price).apply()
    }

    fun getPrice(): Float {
        return storage.getFloat(SHARED_PRICE, 0F)
    }

    fun setApellidos(apellidos:String){
        storage.edit().putString(SHARED_APELLIDOS, apellidos).apply()
    }

    fun getApellidos():String{
        return storage.getString(SHARED_APELLIDOS, "")!!
    }

    fun setDescripcion(descripcion:String){
        storage.edit().putString(SHARED_DESCRIPCION, descripcion).apply()
    }

    fun getDescripcion():String{
        return storage.getString(SHARED_DESCRIPCION, "")!!
    }

    fun setUuid(uuid:String){
        storage.edit().putString(SHARED_UUID, uuid).apply()
    }

    fun getUuid():String{
        return storage.getString(SHARED_UUID, "")!!
    }

    fun setId(id:String){
        storage.edit().putString(SHARED_ID, id).apply()
    }

    fun getId():String{
        return storage.getString(SHARED_ID, "")!!
    }

    fun setEmail(email:String){
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun getEmail():String{
        return storage.getString(SHARED_EMAIL, "")!!
    }
}