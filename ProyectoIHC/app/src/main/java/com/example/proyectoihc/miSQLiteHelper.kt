package com.example.proyectoihc

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper(context: Context):SQLiteOpenHelper(
    context,"datos.db",null,6) {

    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE medicamentos "+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NombreUser TEXT, NombreMed TEXT, HoraMed TEXT, DosisMed TEXT, ViaMed TEXT)"

        db!!.execSQL(ordenCreacion)

        val creacion = "CREATE TABLE perfiles "+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NombreUser TEXT, HoraDormir TEXT, HoraDespertar TEXT, PerfilActual TEXT)"

        db!!.execSQL(creacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val ordenBorrado= "DROP TABLE IF EXISTS medicamentos"

        db!!.execSQL(ordenBorrado)

        val orden= "DROP TABLE IF EXISTS perfiles"

        db!!.execSQL(orden)

        onCreate(db)
    }

    fun nuevoMed(user:String,med:String,hora:String,dosis:String,via:String){
        val datos = ContentValues()
        datos.put("NombreUser",user)
        datos.put("NombreMed",med)
        datos.put("HoraMed",hora)
        datos.put("DosisMed",dosis)
        datos.put("ViaMed",via)

        val db = this.writableDatabase
        db.insert("medicamentos",null,datos)
        db.close()
    }

    fun nuevoPerfil(user:String,hDor:String,hDes:String,actual:String){
        val datos = ContentValues()
        datos.put("NombreUser",user)
        datos.put("HoraDormir",hDor)
        datos.put("HoraDespertar",hDes)
        datos.put("PerfilActual",actual)

        val db = this.writableDatabase
        db.insert("perfiles",null,datos)
        db.close()
    }

    fun eliminarMed(med: String){
        val args = arrayOf(med.toString())

        val db =this.writableDatabase
        db.delete("medicamentos","NombreMed=?",args)
        db.close()
    }

    fun eliminarUser(user: String){
        val args = arrayOf(user.toString())

        val db =this.writableDatabase
        db.delete("perfiles","NombreUser=?",args)
        db.delete("medicamentos","NombreUser=?",args)
        db.close()
    }

    fun cambiarActual(id:Int,user:String,hDor:String,hDes:String,actual:String){
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("NombreUser",user)
        datos.put("HoraDormir",hDor)
        datos.put("HoraDespertar",hDes)
        datos.put("PerfilActual",actual)

        val db =this.writableDatabase
        db.update("perfiles",datos,"_id=?",args);
        db.close()

    }
    fun cambiar(user:String,actual:String){
        val args = arrayOf(user.toString())

        val datos = ContentValues()
        datos.put("PerfilActual",actual)

        val db =this.writableDatabase
        db.update("perfiles",datos,"NombreUser=?",args);
        db.close()
    }

    fun ActMed(clave:String,med:String,hora:String,dosis:String,via:String){
        val args = arrayOf(clave.toString())

        val datos = ContentValues()
        datos.put("NombreMed",med)
        datos.put("HoraMed",hora)
        datos.put("DosisMed",dosis)
        datos.put("ViaMed",via)

        val db =this.writableDatabase
        db.update("medicamentos",datos,"NombreMed=?",args)
        db.close()
    }
}